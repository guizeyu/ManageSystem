package com.example.springboot.controller.dto;

import lombok.Data;

/**
 * 用来接受前端返回的数据
 */
@Data
public class UserDTO {
    private String username;
    private String password;
    private String nickname;
    private String img_url;
}
