package com.itheima.dao;

import com.itheima.bean.User;

public interface UserDao {

    //根据用户名查询用户 返回user对象 用于注册 判断用户名是否可用  user==null：用户名可用  user!=null：用户名已存在
    User selectByUsername(String username);

    //插入用户数据到表中
    int add(User user);

    //根据用户名和密码查询用户  返回user对象 用于登录 user==null：登录失败  user!=null：登录成功
    User select(User user);


}
