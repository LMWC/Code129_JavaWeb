package com.itheima.web;

import com.itheima.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决请求响应中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //1.获取请求参数
        String username = request.getParameter("username");

        //2.调用业务处理  【根据用户名查询用户判断用户名是否可用   true:表示用户名存在 不可用   false：表示用户名不存在 可以使用】
        UserService userService = new UserService();
        boolean flag = userService.selectByName(username);

        //3.响应数据 "true"|"false"   【普通web请求：数据直接写到页面   异步请求：数据响应给异步请求发送对象了 没有再直接写到页面了】
        response.getWriter().print(flag);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}