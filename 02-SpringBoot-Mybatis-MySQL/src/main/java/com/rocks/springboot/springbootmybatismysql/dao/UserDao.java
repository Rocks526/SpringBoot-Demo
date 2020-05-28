package com.rocks.springboot.springbootmybatismysql.dao;

import com.rocks.springboot.springbootmybatismysql.po.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Rocks526
 * @ClassName UserDao
 * @Description UserDao
 * @Date 2020/5/28 15:43
 **/
@Repository
public interface UserDao {

    @Select("select * from user")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "userName",column = "user_name"),
            @Result(property = "passWord",column = "pass_word"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "updateTime",column = "update_time"),
            @Result(property = "nickName",column = "nick_name")
    }
    )
    List<User> getUserList();

    @Select("select * from user where id = #{userId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "userName",column = "user_name"),
            @Result(property = "passWord",column = "pass_word"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "updateTime",column = "update_time"),
            @Result(property = "nickName",column = "nick_name")
    }
    )
    User getUserInfoById(Long userId);

    @Select("select * from user where user_name = #{userName}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "userName",column = "user_name"),
            @Result(property = "passWord",column = "pass_word"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "updateTime",column = "update_time"),
            @Result(property = "nickName",column = "nick_name")
    }
    )
    User getUserInfoByUserName(Long userName);

    @Insert("insert into user (user_name,pass_word,telephone,photo,role,create_time,update_time,nick_name,status)values(#{userName},#{passWord},#{telephone},#{photo},#{role},#{createTime},#{updateTime},#{nickName},#{status})")
    void insert(User user);

    @Delete("delete from user where user_name = #{userName}")
    void deleteByUserName(String userName);

    @Delete("delete from user where id = #{userId}")
    void deleteByUserId(Long userId);

    @Update("update user set pass_word = #{passWord} where user_name = #{userName}")
    void updatePassByUserName(String passWord,String userName);

    @Update("update user set pass_word = #{passWord} where id = #{userId}")
    void updatePassByUserId(String passWord,Long userId);

    @Update("update user set status = #{status} where id = #{userId}")
    void updateUserStatusByUserId(Integer status,Long userId);

}
