package com.itheima.demo03_JDBC事务;

import sun.java2d.pipe.SpanIterator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * 需求：张三给李四转账100元
 * 不使用JDBC事务完成转账
 *  转账业务功能实现：
 *      操作数据库 zs-100 ls+100
 *

 -- 账户表
 create table account(
 id int primary key auto_increment,
 name varchar(20),
 money double
 );
 insert into account values (null,'zs',1000);
 insert into account values (null,'ls',1000);
 insert into account values (null,'ww',1000);

 */
public class Test01 {
    public static void main(String[] args) {

        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            //2.获取连接对象
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/day16?useSSL=false&serverTimezone=UTC", "root", "root");

            //3.获取预编译执行者对象
            //3.1:zs钱减少
            String sql01 = "update account set money=money-? where name=?";
            PreparedStatement pst01 = connection.prepareStatement(sql01);

            pst01.setInt(1,100);
            pst01.setString(2,"zs");

            //3.2：ls钱增加
            String sql02 = "update account set money=money+? where name=?";
            PreparedStatement pst02 = connection.prepareStatement(sql02);

            pst02.setInt(1,100);
            pst02.setString(2,"ls");

            //4.执行操作
            int rows01 = pst01.executeUpdate();
            int rows02 = pst02.executeUpdate();

            //5.处理返回结果
            if(rows01>0 && rows02>0){
                System.out.println("转账成功");
            }else{
                System.out.println("转账失败！");
            }


            //6.关闭对象 释放资源
            pst01.close();
            pst02.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("转账失败！");
            e.printStackTrace();
        }
    }
}
