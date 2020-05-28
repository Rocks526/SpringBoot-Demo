package com.rocks.springboot.springbootmybatismysql.until;

import lombok.Data;

/**
 * @Author Rocks526
 * @ClassName Response
 * @Description 响应结果
 * @Date 2020/5/28 16:12
 **/
@Data
public class Response {

    private Integer code;

    private String msg;

    private Object data;

    public Response(Integer code,String msg,Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Response success(){
        return new Response(200,"请求成功!",null);
    }

    public static Response success(Object data){
        return new Response(200,"请求成功!",data);
    }

    public static Response success(String msg){
        return new Response(200,msg,null);
    }

    public static Response success(Integer code,String msg,Object data){
        return new Response(code,msg,data);
    }

    public static Response success(String msg,Object data){
        return new Response(200,msg,data);
    }

    public static Response error(){
        return new Response(400,"请求失败!",null);
    }

    public static Response error(Object data){
        return new Response(400,"请求失败!",data);
    }

    public static Response error(String msg){
        return new Response(400,msg,null);
    }

    public static Response error(Integer code,String msg,Object data){
        return new Response(code,msg,data);
    }

    public static Response error(String msg,Object data){
        return new Response(400,msg,data);
    }



}
