package com.example.springboot.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper,User> {
    public boolean saveUser(User user) {
//        if (user.getId()==null){
//            save(user);
//        }else{
//            updateById(user);
//        }
//        return false;
        return saveOrUpdate(user);//mybatis-plus方法
    }


//    @Autowired
//    private UserMapper userMapper;
//采用继承serviceimpl的save方法
//    public int save(User user){
//        if(user.getId()==null){
//            return userMapper.insert(user);
//        }else{
//            return userMapper.update(user);
//        }
//    }

}
