package com.itheima.demo04_c3p0;

import com.itheima.bean.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * C3P0连接池使用：配置文件方式
 *     1.导入c3p0连接池 jar包
 *     2.创建编写C3P0连接池配置文件 c3p0-config.xml【名称必须叫这个】放入到src目录下
 *     3.创建C3P0连接池对象 【自动加载配置文件】
 *     4.从C3P0连接池对象中获取连接对象使用了
 *  连接池作用：避免连接对象频繁创建和销毁，提高连接对象重复使用率，节省系统资源，提高系统性能。
 */
public class Test02_配置文件 {
    public static void main(String[] args) throws Exception {
        //2.创建C3P0连接池对象
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        //3.从C3P0连接池对象中获取连接对象使用了
        Connection connection = dataSource.getConnection();

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

        System.out.println("正在使用的:"+dataSource.getNumBusyConnections());// 正在使用连接数  1
        System.out.println("正在空闲的:"+dataSource.getNumIdleConnections());// 空闲连接数      4
        System.out.println("总的连接数:"+dataSource.getNumConnections());// 总连接数           5

        //7.关闭对象 释放资源
        resultSet.close();
        pst.close();

        //现在是归还连接对象到连接池中  不是关闭连接对象了
        connection.close();

        Thread.sleep(3000);

        System.out.println("正在使用的:"+dataSource.getNumBusyConnections());// 正在使用连接数   0
        System.out.println("正在空闲的:"+dataSource.getNumIdleConnections());// 空闲连接数      5
        System.out.println("总的连接数:"+dataSource.getNumConnections());// 总连接数            5
    }
}
