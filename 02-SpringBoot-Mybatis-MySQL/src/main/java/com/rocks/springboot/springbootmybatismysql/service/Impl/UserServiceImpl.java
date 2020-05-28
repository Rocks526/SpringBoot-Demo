package com.rocks.springboot.springbootmybatismysql.service.Impl;

import com.rocks.springboot.springbootmybatismysql.dao.UserDao;
import com.rocks.springboot.springbootmybatismysql.po.User;
import com.rocks.springboot.springbootmybatismysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Rocks526
 * @ClassName UserServiceImpl
 * @Description 用户服务实现类
 * @Date 2020/5/28 16:01
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAllUser() {
        return userDao.getUserList();
    }

    @Override
    public User getUserInfoById(Long userId) {
        return userDao.getUserInfoById(userId);
    }

    @Override
    public User getUserInfoByUserName(Long userName) {
        return userDao.getUserInfoByUserName(userName);
    }

    @Override
    public void updatePassWordByUserId(Long userId, String password) {
        userDao.updatePassByUserId(password,userId);
    }

    @Override
    public void updatePassWordByUserName(String userName, String password) {
        userDao.updatePassByUserName(password,userName);
    }


    @Override
    public void saveUser(User user) {
        userDao.insert(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userDao.deleteByUserId(userId);
    }

    @Override
    public void updateUserStatus(Integer status, Long userId) {
        userDao.updateUserStatusByUserId(status,userId);
    }
}
