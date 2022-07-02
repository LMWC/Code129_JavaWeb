package com.itheima.demo02_登录案例;

import com.itheima.bean.User;

import java.sql.*;
import java.util.Scanner;

/**
 * 使用Statement对象完成登录案例
 * 思路分析:
 *         //1.接收用户键盘输入的用户名和密码
 *
 *         //2.根据用户输入的用户名和密码查询数据库
 *         //2.1：注册驱动
 *         //2.2：获取连接对象
 *         //2.3：获取Statement执行者对象
 *         //2.4：执行查询sql语句 添加where条件
 *         //2.5：处理结果  【将查询到的结果集封装到对象中】
 *         //2.6：关闭对象 释放资源
 *
 *         //3.判断用户是否登录成功  user==null：登录失败  user！=null：登录成功
 *
 */
public class Test01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.接收用户键盘输入的用户名和密码
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();

        //2.根据用户输入的用户名和密码查询数据库
        //2.1：注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        //2.2：获取连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/day16?useSSL=false&serverTimezone=UTC", "root", "root");

        //2.3：获取Statement执行者对象
        Statement statement = connection.createStatement();

        //2.4：执行查询sql语句 添加where条件
        ResultSet rs = statement.executeQuery("select * from user where username='" + username + "' and password='" + password + "'");

        //2.5：处理结果  【将查询到的结果集封装到对象中】
        User user = null;

        //如果根据用户名和密码查询到存在的记录了就封装到User对象中
        while (rs.next()){
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setNickname(rs.getString("nickname"));
        }

        //2.6：关闭对象 释放资源
        rs.close();
        statement.close();
        connection.close();

        //3.判断用户是否登录成功  user==null：登录失败  user！=null：登录成功
        if(user==null){
            System.out.println("登录失败！");
        }else{
            System.out.println("登录成功！");
        }
    }
}
