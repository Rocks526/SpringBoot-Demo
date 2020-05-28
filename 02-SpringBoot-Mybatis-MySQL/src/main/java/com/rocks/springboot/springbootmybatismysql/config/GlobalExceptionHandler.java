package com.rocks.springboot.springbootmybatismysql.config;

import com.rocks.springboot.springbootmybatismysql.until.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Rocks526
 * @ClassName GlobalExceptionHandler
 * @Description 异常处理器
 * @Date 2020/5/28 16:26
 **/
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response exceptionHandler(HttpServletRequest request, Exception e){
        log.error("请求[{}]发生异常:{}",request.getRequestURL(),e.toString());
        return Response.error();
    }

}
