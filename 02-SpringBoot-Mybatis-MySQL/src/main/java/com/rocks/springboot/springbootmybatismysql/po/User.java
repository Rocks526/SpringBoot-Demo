package com.rocks.springboot.springbootmybatismysql.po;

import lombok.Data;

import java.util.Date;

/**
 * @Author Rocks526
 * @ClassName User
 * @Description 用户实体
 * @Date 2020/5/28 15:45
 **/
@Data
public class User {

    //ID
    private Long id;

    //用户名
    private String userName = "";

    //密码
    private String passWord = "";

    //电话
    private String telephone = "";

    //头像URL
    private String photo = "";

    //角色
    private Integer role = 1;

    private Date createTime = new Date();

    private Date updateTime = new Date();

    //昵称
    private String nickName = "";

    //用户状态 0正常 1冻结 2删除
    private Integer status = 0;

}
