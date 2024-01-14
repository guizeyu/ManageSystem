package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
//lombok有多种方法

@Data
//data表示get和set方法
@TableName(value = "sys_user")
//数据库中表的名字
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;

    //不显示密码
    @JsonIgnore
    private String password;

    private String nickname;
    private String email;
    private String telephone;
    private String address;

}
