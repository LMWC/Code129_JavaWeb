package com.itheima.demo01_JDBC入门;

import com.itheima.bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装多条记录到对象集合
 * 需求：查询所有用户信息  select * from user
 */
public class Test04_封装多条记录到对象集合 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /*
            需求：使用JDBC查询用户表中的所有记录
            JDBC操作步骤 ：
                1.导入数据库驱动jar包
                2.加载驱动
                3.获取数据库连接对象
                4.获取执行者【执行SQL语句】对象
                5.执行SQL语句，得到返回结果
                6.处理返回结果
                7.关闭对象 释放资源
         */
        //2.加载驱动
        //DriverManager.registerDriver(new Driver());
        Class.forName("com.mysql.jdbc.Driver");     //使用反射注册驱动  通过反射技术将Driver加载到JVM中，就会执行Driver类的静态代码块 完成驱动注册  注册一次

        //3.获取数据库连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/day16?useSSL=false&serverTimezone=UTC","root","root");

        //4.获取执行者【执行SQL语句】对象
        Statement statement = connection.createStatement();


        //5.执行SQL语句，得到返回结果
        ResultSet resultSet = statement.executeQuery("select * from user");

        //6.处理返回结果
        List<User> list = new ArrayList<>();
        User user = null;

        while (resultSet.next()){
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setNickname(resultSet.getString("nickname"));
            list.add(user);
        }

        //user.soutv
        System.out.println("list = " + list);
        System.out.println("用户个数："+list.size());

        //7.关闭对象 释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }

}
