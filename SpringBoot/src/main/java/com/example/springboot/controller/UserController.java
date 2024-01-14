package com.example.springboot.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.common.Constants;
import com.example.springboot.controller.dto.UserDTO;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import com.example.springboot.service.IUserService;
import com.example.springboot.entity.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzy
 * @since 2024-01-05
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {
        String username=userDTO.getUsername();
        String password=userDTO.getPassword();
        //先判断是否为空再调函数
        if(StrUtil.isBlank(username)||StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"参数不足");
        }
        UserDTO dto=userService.login(userDTO);
        return Result.success(dto);
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO){
        String username=userDTO.getUsername();
        String password=userDTO.getPassword();
        //先判断是否为空再调函数
        if(StrUtil.isBlank(username)||StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"参数不足");
        }
        User user=userService.register(userDTO);
        return Result.success(user);
    }


    @PostMapping
    public boolean save(@RequestBody User user) {
        //新增或者更新
        return userService.saveOrUpdate(user);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return userService.removeById(id);
    }

    @GetMapping
    public List<User> findAll() {
        return userService.list();
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @GetMapping("/username/{username}")
    public Result findOne(@PathVariable String username) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("username",username);
        return Result.success(userService.getOne(queryWrapper));
    }

    @GetMapping("/page")
    public Page<User> findPage(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String username,
                               @RequestParam(defaultValue = "") String email,
                               @RequestParam(defaultValue = "") String address) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(!username.equals(""))queryWrapper.like("username", username);
        if(!email.equals(""))queryWrapper.like("email", email);//这里会自动把and加上
        if(!address.equals(""))queryWrapper.like("address", address);
        return userService.page(new Page<>(pageNum, pageSize),queryWrapper);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return userService.removeBatchByIds(ids);
    }

    //导出
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        //拿到所有数据
        List<User> list=userService.list();
        //System.out.println(list);
        //pom引入hutool
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter excelWriter= ExcelUtil.getWriter(true);
//        excelWriter.addHeaderAlias("username","用户名");
//        excelWriter.addHeaderAlias("password","密码");
//        excelWriter.addHeaderAlias("nickname","昵称");
//        excelWriter.addHeaderAlias("email","邮箱");
//        excelWriter.addHeaderAlias("telephone","手机");
//        excelWriter.addHeaderAlias("address","地址");
//        excelWriter.addHeaderAlias("create_time","创建时间");

        excelWriter.write(list,true);
        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        ServletOutputStream out = response.getOutputStream();
        excelWriter.flush(out, true);
        out.close();
        excelWriter.close();
    }

    /**
     * excel 导入
     */
    @PostMapping("/import")
    public Boolean imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
         //方式1：(推荐) 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<User> list = reader.readAll(User.class);

        // 方式2：忽略表头的中文，直接读取表的内容
//        List<List<Object>> list = reader.read(1);
//        List<User> users = CollUtil.newArrayList();
//        for (List<Object> row : list) {
//            User user = new User();
//            user.setUsername(row.get(0).toString());
//            user.setPassword(row.get(1).toString());
//            user.setNickname(row.get(2).toString());
//            user.setEmail(row.get(3).toString());
//            user.setTelephone(row.get(4).toString());
//            user.setAddress(row.get(5).toString());
//            users.add(user);
//        }
//
        userService.saveBatch(list);
        return true;
    }


}
