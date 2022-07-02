package com.itheima.dao;

import com.itheima.bean.User;

import java.util.List;

public interface UserDao {

    User selectByName(String username);

    int add(User user);

    List<User> selectAll();
}
