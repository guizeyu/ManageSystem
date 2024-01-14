package com.example.springboot.exception;

import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class ServiceException extends RuntimeException{
    private String code;
    //RuntimeException这里定义了msg，继承即可

    public ServiceException(String code,String msg){
        super(msg);
        this.code=code;
    }

}
