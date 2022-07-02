package com.itheima.demo05_druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.itheima.bean.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Druid连接池使用：硬编码方式
 *  1.导入druid连接池 jar包
 *  2.创建Druid连接池对象  配置数据库连接基本参数
 *  3.从Druid连接池对象中获取连接对象使用
 */
public class Test01_硬编码 {
    public static void main(String[] args) throws Exception {
        //2.创建Druid连接池对象
        DruidDataSource dataSource = new DruidDataSource();
        //配置数据库连接基本参数
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/day16?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        //配置数据库连接池的相关参数
        dataSource.setInitialSize(5);       //初始连接个数
        dataSource.setMaxActive(10);        //最大连接个数
        dataSource.setMaxWait(3000);        //等待时间

        //3.从Druid连接池对象中获取连接对象使用
        DruidPooledConnection connection = dataSource.getConnection();

        //4.获取执行者【执行SQL语句】对象
        String sql = "select * from user where id=?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1,3);


        //5.执行SQL语句，得到返回结果
        ResultSet resultSet = pst.executeQuery("select * from user where id=3");

        //6.处理返回结果
        User user = null;
        while (resultSet.next()){
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setNickname(resultSet.getString("nickname"));
        }

        System.out.println("user = " + user);

        System.out.println("正在使用的:"+dataSource.getActiveCount());// 1
        System.out.println("正在空闲的:"+dataSource.getPoolingCount());// 4

        //7.关闭对象 释放资源
        resultSet.close();
        pst.close();

        //现在是归还连接对象到连接池中  不是关闭连接对象了
        connection.close();

        Thread.sleep(3000);

        System.out.println("正在使用的:"+dataSource.getActiveCount());// 0
        System.out.println("正在空闲的:"+dataSource.getPoolingCount());// 5

    }
}
