package com.itheima.dao;

import com.itheima.bean.User;

import java.util.List;

public interface UserDao {

    //查询所有用户数据
    List<User> selectAll();

    //查询id为3的用户信息
    User selectById(int id);
}
