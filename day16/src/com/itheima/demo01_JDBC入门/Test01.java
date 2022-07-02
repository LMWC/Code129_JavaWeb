package com.itheima.demo01_JDBC入门;

import com.mysql.jdbc.Driver;

import java.sql.*;

public class Test01 {
    public static void main(String[] args) throws SQLException {
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
        DriverManager.registerDriver(new Driver());

        //3.获取数据库连接对象
        /*
            url：数据库服务器所在地址
            user：数据库用户名
            password：数据库密码
            数据库连接URL参数：
                useSSL=false&serverTimezone=UTC
         */
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/day16?useSSL=false&serverTimezone=UTC","root","root");

        //4.获取执行者【执行SQL语句】对象
        Statement statement = connection.createStatement();

        //5.执行SQL语句，得到返回结果
        ResultSet resultSet = statement.executeQuery("select * from user");

        //6.处理返回结果
        while (resultSet.next()){
            System.out.println(resultSet.getObject("id")+" "+resultSet.getObject("username")+" "+resultSet.getObject("password")+" "+resultSet.getObject("nickname"));
        }

        //7.关闭对象 释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }

}
