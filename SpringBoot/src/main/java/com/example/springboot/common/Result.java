package com.example.springboot.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 所有的接口都应当统一返回同一种对象
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result {
    private String code;
    private String msg;
    private Object data;

    public static Result success(){
        return new Result(Constants.CODE_200,"success",null);
    }

    public static Result success(Object data){
        return new Result(Constants.CODE_200,"success",data);
    }

    public static Result error(String code,String msg){
        return new Result(code,"msg",null);
    }

    public static Result error(){
        return new Result(Constants.CODE_500,"系统错误",null);
    }
}
