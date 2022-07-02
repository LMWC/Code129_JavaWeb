package com.exercise.代码编程题.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.exercise.代码编程题.pojo.Student;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StudentDao {
    /*
-- 删除db14数据库
drop database if exists db14;

-- 创建db14数据库
CREATE DATABASE db14;

-- 使用db14数据库
USE db14;

-- 创建student表
CREATE TABLE student(
	id INT PRIMARY KEY AUTO_INCREMENT,	-- 学生id
	NAME VARCHAR(20),					-- 学生姓名
	age INT							-- 学生年龄
);

-- 添加数据
INSERT INTO student VALUES (NULL,'张三',23),(NULL,'李四',24),(NULL,'王五',25),(NULL,'赵六',26),(NULL,'小黑',27),(NULL,'小智',28);
     */

    //查询所有
    public List<Student> selectAll() throws Exception {
        FileInputStream fis = new FileInputStream("F:\\Code129_JavaWeb\\day16\\src\\druid.properties");
        Properties properties = new Properties();
        properties.load(fis);
        DruidDataSource dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        DruidPooledConnection connection = dataSource.getConnection();
        //Class.forName("com.mysql.jdbc.Driver");
        //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db14?useSSL=false&serverTimezone=UTC","root","root");
        String sql = "select * from student";
        PreparedStatement pst=connection.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        List<Student> list = new ArrayList<>();
        Student student = null;
        while (rs.next()){
            student = new Student(rs.getInt("id"),rs.getString("NAME"),rs.getInt("age"));
            list.add(student);
        }

        System.out.println("正在使用的:"+dataSource.getActiveCount());
        System.out.println("正在空闲的:"+dataSource.getPoolingCount());
        if(rs!=null){
            rs.close();
        }
        if(pst!=null){
            pst.close();
        }
        if(connection!=null){
            connection.close();
        }
        System.out.println("正在使用的:"+dataSource.getActiveCount());
        System.out.println("正在空闲的:"+dataSource.getPoolingCount());
        return list;

    }

    //分页查询
    public List<Student> selectByPage(int start, int pageSize) throws Exception {
        FileInputStream fis = new FileInputStream("F:\\Code129_JavaWeb\\day16\\src\\druid.properties");
        Properties properties = new Properties();
        properties.load(fis);
        DruidDataSource dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        DruidPooledConnection connection = dataSource.getConnection();
        //Class.forName("com.mysql.jdbc.Driver");
        //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db14?useSSL=false&serverTimezone=UTC","root","root");
        String s = String.valueOf(start);
        String p = String.valueOf(pageSize);
        String sql = "select * from student limit "+s+","+p;
        PreparedStatement pst=connection.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        List<Student> list = new ArrayList<>();
        Student student = null;
        while (rs.next()){
            student = new Student(rs.getInt("id"),rs.getString("NAME"),rs.getInt("age"));
            list.add(student);
        }

        System.out.println("正在使用的:"+dataSource.getActiveCount());
        System.out.println("正在空闲的:"+dataSource.getPoolingCount());
        if(rs!=null){
            rs.close();
        }
        if(pst!=null){
            pst.close();
        }
        if(connection!=null){
            connection.close();
        }
        System.out.println("正在使用的:"+dataSource.getActiveCount());
        System.out.println("正在空闲的:"+dataSource.getPoolingCount());
        return list;
    }

    //根据id查询
    public Student selectById(int id) throws Exception {
        FileInputStream fis = new FileInputStream("F:\\Code129_JavaWeb\\day16\\src\\druid.properties");
        Properties properties = new Properties();
        properties.load(fis);
        DruidDataSource dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        DruidPooledConnection connection = dataSource.getConnection();
        //Class.forName("com.mysql.jdbc.Driver");
        //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db14?useSSL=false&serverTimezone=UTC","root","root");
        //3.获取预编译执行者对象
        String sql = "select * from student where id=?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1,id);

        //4.执行操作
        ResultSet rs = pst.executeQuery();

        //5.处理返回结果
        Student student = null;
        while(rs.next()){
            student = new Student(rs.getInt("id"),rs.getString("NAME"),rs.getInt("age"));
        }

        System.out.println("正在使用的:"+dataSource.getActiveCount());
        System.out.println("正在空闲的:"+dataSource.getPoolingCount());
        if(rs!=null){
            rs.close();
        }
        if(pst!=null){
            pst.close();
        }
        if(connection!=null){
            connection.close();
        }
        System.out.println("正在使用的:"+dataSource.getActiveCount());
        System.out.println("正在空闲的:"+dataSource.getPoolingCount());
        return student;
    }

    //插入学生
    public int insert(Student student) throws Exception {
        FileInputStream fis = new FileInputStream("F:\\Code129_JavaWeb\\day16\\src\\druid.properties");
        Properties properties = new Properties();
        properties.load(fis);
        DruidDataSource dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        DruidPooledConnection connection = dataSource.getConnection();
        //Class.forName("com.mysql.jdbc.Driver");
        //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db14?useSSL=false&serverTimezone=UTC","root","root");
        //3.获取预编译执行者对象
        String sql = "insert into student values(?,?,?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        //设置SQL语句参数
        pst.setInt(1,student.getId());
        pst.setString(2,student.getName());
        pst.setInt(3,student.getAge());

        //4.执行操作
        int rows = pst.executeUpdate();

        System.out.println("正在使用的:"+dataSource.getActiveCount());
        System.out.println("正在空闲的:"+dataSource.getPoolingCount());
        if(pst!=null){
            pst.close();
        }
        if(connection!=null){
            connection.close();
        }
        System.out.println("正在使用的:"+dataSource.getActiveCount());
        System.out.println("正在空闲的:"+dataSource.getPoolingCount());

        //5.处理返回结果
        if(rows>0){
            System.out.println("增加成功！");
            return rows;
        }else{
            System.out.println("增加失败！");
            return -1;
        }


    }

    //更新学生
    public int update(Student student) throws Exception {
        FileInputStream fis = new FileInputStream("F:\\Code129_JavaWeb\\day16\\src\\druid.properties");
        Properties properties = new Properties();
        properties.load(fis);
        DruidDataSource dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        DruidPooledConnection connection = dataSource.getConnection();
        //Class.forName("com.mysql.jdbc.Driver");
        //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db14?useSSL=false&serverTimezone=UTC","root","root");
        //3.获取预编译执行者对象
        String sql = "update student set NAME=?,age=? where id=?";
        PreparedStatement pst = connection.prepareStatement(sql);
        //设置SQL语句参数
        pst.setString(1,student.getName());
        pst.setInt(2,student.getAge());
        pst.setInt(3,student.getId());

        //4.执行操作
        int rows = pst.executeUpdate();

        System.out.println("正在使用的:"+dataSource.getActiveCount());
        System.out.println("正在空闲的:"+dataSource.getPoolingCount());
        if(pst!=null){
            pst.close();
        }
        if(connection!=null){
            connection.close();
        }
        System.out.println("正在使用的:"+dataSource.getActiveCount());
        System.out.println("正在空闲的:"+dataSource.getPoolingCount());

        //5.处理返回结果
        if(rows>0){
            System.out.println("修改成功！");
            return rows;
        }else{
            System.out.println("修改失败！");
            return -1;
        }


    }

    //通过id删除学生
    public int delete(int id) throws Exception {
        FileInputStream fis = new FileInputStream("F:\\Code129_JavaWeb\\day16\\src\\druid.properties");
        Properties properties = new Properties();
        properties.load(fis);
        DruidDataSource dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        DruidPooledConnection connection = dataSource.getConnection();
        //Class.forName("com.mysql.jdbc.Driver");
        //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db14?useSSL=false&serverTimezone=UTC","root","root");
        //3.获取预编译执行者对象
        String sql = "delete from student where id=?";
        PreparedStatement pst = connection.prepareStatement(sql);
        //设置SQL语句参数
        pst.setInt(1,id);

        //4.执行操作
        int rows = pst.executeUpdate();

        System.out.println("正在使用的:"+dataSource.getActiveCount());
        System.out.println("正在空闲的:"+dataSource.getPoolingCount());
        if(pst!=null){
            pst.close();
        }
        if(connection!=null){
            connection.close();
        }
        System.out.println("正在使用的:"+dataSource.getActiveCount());
        System.out.println("正在空闲的:"+dataSource.getPoolingCount());

        //5.处理返回结果
        if(rows>0){
            System.out.println("删除成功！");
            return rows;
        }else{
            System.out.println("删除失败！");
            return -1;
        }


    }
}
