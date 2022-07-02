package com.itheima.demo05_druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.itheima.bean.User;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Druid连接池使用：配置文件方式
 *  1.导入druid连接池 jar包
 *  2.在src目录下创建druid配置文件xxx.properties  【名字可以随便起】 但是properties文件中的key不能随便写
 *  3.使用Properties对象加载druid配置文件xxx.properties
 *  4.使用Druid连接池工厂类 通过Druid配置文件Properties对象  创建出Druid连接池对象
 *  5.从Druid连接池对象中获取连接对象使用
 */
public class Test02_配置文件 {
    public static void main(String[] args) throws Exception {
        //1.使用Properties对象加载druid配置文件xxx.properties
        FileInputStream fis = new FileInputStream("day16\\src\\druid.properties");
        Properties properties = new Properties();
        properties.load(fis);

        //2.使用Druid连接池工厂类 通过Druid配置文件Properties对象  创建出Druid连接池对象
        DruidDataSource dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

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
