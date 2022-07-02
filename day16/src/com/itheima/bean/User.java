package com.itheima.bean;

import java.io.Serializable;

/**
    javabean：一个标准的java类
        必须满足：
            1.类中所有属性都必须私有化，提供公共的getter|setter方法
            2.类中必须提供无参构造方法
        可选：
            3.提供满参构造函数
            4.类要实现Serializable接口 【序列化接口 方法将对象转为流可以在硬盘上以文件的形式持久化的保存下来  一般为缓存】
        注意：
            属性类型一般使用引用包装类型，
                    null --> Integer正常
                    null --> int    报错  无法完成类型转换

 */
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String nickname;

    public User() {
    }

    public User(Integer id, String username, String password, String nickname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
