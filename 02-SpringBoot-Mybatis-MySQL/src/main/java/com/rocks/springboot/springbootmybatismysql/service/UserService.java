package com.rocks.springboot.springbootmybatismysql.service;

import com.rocks.springboot.springbootmybatismysql.po.User;

import java.util.List;

/**
 * @Author Rocks526
 * @ClassName UserService
 * @Description 用户服务接口
 * @Date 2020/5/28 15:57
 **/

public interface UserService {

    //获取所有用户
    List<User> getAllUser();

    //根据ID获取用户信息
    User getUserInfoById(Long userId);

    //根据用户名获取用户信息
    User getUserInfoByUserName(Long userName);

    //修改密码
    void updatePassWordByUserId(Long userId,String password);

    //修改密码
    void updatePassWordByUserName(String userName,String password);

    //新增用户
    void saveUser(User user);

    //删除用户
    void deleteUser(Long userId);

    //修改用户状态
    void updateUserStatus(Integer status,Long userId);



}
