package com.itheima.dao;

import com.itheima.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    //根据用户名查询用户
    @Select("select * from tb_user where username=#{username}")
    User selectByUsername(String username);

    //添加用户
    @Insert("insert into tb_user values(null,#{username},#{password})")
    int add(User user);

    //根据用户名和密码查询用户
    @Select("select * from tb_user where username=#{username} and password=#{password}")
    User select(@Param("username") String username,@Param("password") String password);
}
