package com.example.springboot.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 *
 * </p>
 *
 * @author gzy
 * @since 2024-01-05
 */
@Getter
@Setter
@ToString //相当于加了一个方法
  @TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * ID
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 用户名
     */
      private String username;

      /**
     * 密码
     */
      private String password;

      /**
     * 昵称
     */
      private String nickname;

      /**
     * 邮箱
     */
      private String email;

      /**
     * 电话
     */
      private String telephone;

      /**
     * 地址
     */
      private String address;

      /**
     * 创建时间
     */
      private Timestamp create_time;

      /**
       * 头像
       */
      private String img_url;


}
