package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

//@Mapper
public interface UserMapper extends BaseMapper<User> {

//    //查询所有数据
//    @Select("select * from sys_user")
//    List<User> findAll();
//
//    //新增或者修改
//    @Select("INSERT into sys_user(username,password,nickname,email,telephone,address) " +
//            "VALUES(#{username},#{password},#{nickname},#{email},#{telephone},#{address})")
//    int insert(User user);
//
//    //删除
//    @Delete("delete from sys_user where id=#{id}")
//    Integer deleteById(@Param("id") Integer id);
//
////    @Update("update sys_user set username=#{username},password=#{password},nickname=#{nickname},email=#{email}," +
////            "telephone=#{telephone},address=#{address} where id=#{id}")这里不用了是因为xml里面写了
//    int update(User user);
//
//
//    //多个参数一定要使用@param
//    @Select("select * from sys_user where username like #{username} limit #{pageNum}, #{pageSize}")
//    List<User> selectPage(@Param("pageNum") Integer pageNum,@Param("pageSize")Integer pageSize,
//                          @Param("username") String username);
//
//    @Select("select count(*) from sys_user where username like #{username}")
//    Integer selectTotal(@Param("username") String username);



}
