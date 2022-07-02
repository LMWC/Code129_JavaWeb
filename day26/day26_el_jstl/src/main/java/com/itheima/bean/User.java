package com.itheima.bean;

import java.io.Serializable;

/**
 * 用于封装数据的javabean【标准的java类】
 *  必须满足的条件：
 *      1.属性私有化 提供公共的get|set方法
 *      2.提供无参构造方法
 *  可选的满足条件：
 *      1.实现Serializable序列化接口 方便将对象转为流保存在硬盘文件中  后期有一些框架会做缓存处理 就会自动的将对象保存在硬盘文件中
 *      2.满参构造函数
 */
public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;

    public User() {
    }

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
