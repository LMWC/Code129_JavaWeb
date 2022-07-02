package com.itheima.web;

import com.alibaba.fastjson.JSON;
import com.itheima.bean.User;
import com.itheima.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = JSON.parseObject(request.getInputStream(), User.class);
        System.out.println("用户注册参数：user = " + user);

        //1.获取用户登录 用户名和密码
        String username = user.getUsername();
        String password = user.getPassword();
        System.out.println(username);
        System.out.println(password);

        //2.调用业务处理
        UserService userService = new UserService();
        User user1 = userService.login(username, password);

        //3.响应
        if(user1!=null){
            //登录成功
            response.getWriter().print(true);
        }else{
            //登录失败
            response.getWriter().print(false);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}