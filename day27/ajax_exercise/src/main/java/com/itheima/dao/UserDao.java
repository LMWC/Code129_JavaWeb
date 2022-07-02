package com.itheima.dao;

import com.itheima.bean.Brand;
import com.itheima.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    User selectByName(String username);

    int add(User user);

    List<Brand> selectAll();

    User select(@Param("username")String username, @Param("password")String password);
}
