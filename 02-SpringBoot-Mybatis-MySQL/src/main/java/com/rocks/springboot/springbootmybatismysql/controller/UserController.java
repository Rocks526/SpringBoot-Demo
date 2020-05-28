package com.rocks.springboot.springbootmybatismysql.controller;

import com.rocks.springboot.springbootmybatismysql.po.User;
import com.rocks.springboot.springbootmybatismysql.service.UserService;
import com.rocks.springboot.springbootmybatismysql.until.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Rocks526
 * @ClassName UserController
 * @Description 用户控制器
 * @Date 2020/5/28 16:07
 **/
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public Response getAllUser(){
        List<User> allUser = userService.getAllUser();
//        throw new RuntimeException();
        return Response.success(allUser);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Response getUserById(@PathVariable(value = "id",required = true) Long id){
        User userInfo = userService.getUserInfoById(id);
        return Response.success(userInfo);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Response deleteUserById(@PathVariable(value = "id",required = true) Long id){
        userService.deleteUser(id);
        return Response.success("删除成功!");
    }

    @RequestMapping(value = "",method = RequestMethod.PUT)
    public Response saveUser(@RequestBody User user){
        userService.saveUser(user);
        return Response.success("用户添加成功!");
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public Response updateUserStatus(@RequestParam(value = "id",required = true) Long userId,
                                     @RequestParam(value = "status",required = true)Integer status){
        userService.updateUserStatus(status,userId);
        return Response.success("用户状态更新成功!");
    }





}
