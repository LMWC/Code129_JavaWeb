package com.itheima.demo02_登录案例;

import com.itheima.bean.User;

import java.sql.*;
import java.util.Scanner;

/**
 * 使用PreparedStatement对象优化登录案例
 * 思路分析:
 *         //1.接收用户键盘输入的用户名和密码
 *
 *         //2.根据用户输入的用户名和密码查询数据库
 *         //2.1：注册驱动
 *         //2.2：获取连接对象
 *         //2.3：获取PreparedStatement 预编译执行者对象  【获取域对象执行者对象时 需要传入SQL语句  目的就是为了提前固定SQL语句格式】
 *         //2.4：执行查询 executeQuery();
 *         //2.5：处理结果  【将查询到的结果集封装到对象中】
 *         //2.6：关闭对象 释放资源
 *
 *         //3.判断用户是否登录成功  user==null：登录失败  user！=null：登录成功
 *
 *  使用PreparedStatement对象解决了SQL注入问题：
 *  PreparedStatement对象和Statement对象的区别？
 *      1.PreparedStatement继承了Statement接口  功能比Statement更加强大
 *      2.Statement对象执行SQL语句时 SQL语句需要拼接 不能固定SQL语句的格式，容易出现SQL注入问题 执行不安全
 *      3.PreparedStatement对象执行SQL语句时 SQL语句不需要拼接 使用?进行占位 提前编译，固定了SQL语句格式 可以有效防止SQL注入，更加的安全。
 *      注意：
 *          //2.3：获取PreparedStatement 预编译执行者对象  【获取域对象执行者对象时 需要传入SQL语句  目的就是为了提前固定SQL语句格式】
 *         String sql ="select * from user where username=? and password=?";
 *         PreparedStatement pst = connection.prepareStatement(sql);
 *
 *         //设置占位符参数
 *         pst.setString(1,username);
 *         pst.setString(2,password);
 *
 *         //2.4：执行查询 executeQuery();  小括号中不需要再传递SQL语句了
 *         ResultSet rs = pst.executeQuery();
 *
 */
public class Test02 {
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

        //2.3：获取PreparedStatement 预编译执行者对象  【获取域对象执行者对象时 需要传入SQL语句  目的就是为了提前固定SQL语句格式】
        String sql ="select * from user where username=? and password=?";
        PreparedStatement pst = connection.prepareStatement(sql);

        //设置占位符参数
        pst.setString(1,username);
        pst.setString(2,password);

        //2.4：执行查询 executeQuery();  小括号中不需要再传递SQL语句了
        ResultSet rs = pst.executeQuery();

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
        pst.close();
        connection.close();

        //3.判断用户是否登录成功  user==null：登录失败  user！=null：登录成功
        if(user==null){
            System.out.println("登录失败！");
        }else{
            System.out.println("登录成功！");
        }
    }
}
