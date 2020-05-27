package com.rocks.springboot.springbootstorage.exception;

import lombok.Data;

/**
 * @Author Rocks526
 * @ClassName QiNiuYunException
 * @Description 七牛云存储异常
 * @Date 2020/5/27 18:11
 **/
@Data
public class QiNiuYunException extends RuntimeException {

    private String msg;

    private Throwable cause;

    public QiNiuYunException(String msg) {
        super(msg);
        this.msg = msg;
        this.cause = null;
    }

    public QiNiuYunException(String msg, Throwable cause) {
        super(msg,cause);
        this.msg = msg;
        this.cause = cause;
    }

}
