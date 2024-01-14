package com.example.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.common.Constants;
import com.example.springboot.controller.dto.UserDTO;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzy
 * @since 2024-01-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public UserDTO login(UserDTO userDTO) {
//        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("username",userDTO.getUsername());
//        queryWrapper.eq("password",userDTO.getPassword());
//        User one;
//        User one=getOne(queryWrapper);
//        //这种方法无法应对数据库中有脏数据的情况，多个username password相同
//        return one!=null;

//        List<User> list=list(queryWrapper);
//        return list.size()!=0;
//        try {
//            one=getOne(queryWrapper);
//        }catch (Exception e){
//            throw new ServiceException(Constants.CODE_500,"系统错误");
//        }
        User one=getUserInfo(userDTO);
        if(one!=null){
            BeanUtil.copyProperties(one,userDTO,true);
            return userDTO;
        }else{
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }

    @Override
    public User register(UserDTO userDTO) {
        User one=getUserInfo(userDTO);
        if(one==null){
            one=new User();
            BeanUtil.copyProperties(userDTO,one,true);
            save(one);
        }else{
            throw new ServiceException(Constants.CODE_600,"用户名已存在");
        }
        return one;
    }

    private User getUserInfo(UserDTO userDTO){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",userDTO.getUsername());
        queryWrapper.eq("password",userDTO.getPassword());
        User one;
        try {
            one=getOne(queryWrapper);
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return one;
    }

}
