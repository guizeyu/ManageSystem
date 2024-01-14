package com.example.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")//这是加url
public class UserController {

//    //注入其他类，例如这个User Mapper
//    @Autowired
//    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    //新增，修改
    @PostMapping
    public boolean save(@RequestBody User user) {
        return userService.saveUser(user);//RequestBody必要的，把前台的json变为Java
    }

    //查询所有数据
    @GetMapping
    public List<User> findAll() {
//        return userMapper.findAll();
        return userService.list();
    }

    //分页查询
    //(pageNum-1)*pageSize   pageSize
    //多个参数一定要使用@requestparam
//    @GetMapping("/page")
//    public Map<String, Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String username
//    ) {
//        pageNum = (pageNum - 1) * pageSize;
//        username="%"+username+"%";
//
//        List<User> data = userMapper.selectPage(pageNum, pageSize,username);
//        Map<String, Object> res = new HashMap<>();
//        Integer total = userMapper.selectTotal(username);
//        res.put("data", data);
//        res.put("total", total);
//        return res;
//    }

    //mybatis-plus 分页查询
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username,
                                @RequestParam(defaultValue = "") String email,
                                @RequestParam(defaultValue = "") String address) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if(!username.equals(""))queryWrapper.like("username", username);
        if(!email.equals(""))queryWrapper.like("email", email);//这里会自动把and加上
        if(!address.equals(""))queryWrapper.like("address", address);

//        queryWrapper.and().like("email",email);这个和上面的一个作用
        return userService.page(page, queryWrapper);
    }


    //删除
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
//        return userMapper.deleteById(id);
        return userService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return userService.removeBatchByIds(ids);
    }

}
