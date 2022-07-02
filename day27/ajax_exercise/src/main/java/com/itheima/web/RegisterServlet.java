package com.itheima.web;

import com.alibaba.fastjson.JSON;
import com.itheima.bean.User;
import com.itheima.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取请求参数【将json格式参数封装到一个java对象中】
        //注意：使用request.getParameter()方法获取不到json格式参数  只能从请求体中拿到json格式参数的字节输入流对象 然后直接转为java对象
        User user = JSON.parseObject(request.getInputStream(), User.class);
        System.out.println("用户注册参数：user = " + user);


        // 2.调用业务处理
        UserService userService = new UserService();
        int rows = userService.add(user);


        // 3.响应普通数据
        if(rows>0){
            //注册成功
            response.getWriter().print(true);
        }else{
            //注册失败
            response.getWriter().print(false);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}