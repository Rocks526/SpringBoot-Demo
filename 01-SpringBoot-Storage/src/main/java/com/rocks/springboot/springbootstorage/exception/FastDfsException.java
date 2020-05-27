package com.rocks.springboot.springbootstorage.exception;

import lombok.Data;

/**
 * @Author Rocks526
 * @ClassName FastDfsException
 * @Description FastDfs存储异常
 * @Date 2020/5/27 18:11
 **/
@Data
public class FastDfsException extends RuntimeException {

    private String msg;

    private Throwable cause;

    public FastDfsException(String msg) {
        super(msg);
        this.msg = msg;
        this.cause = null;
    }

    public FastDfsException(String msg, Throwable cause) {
        super(msg,cause);
        this.msg = msg;
        this.cause = cause;
    }

}
