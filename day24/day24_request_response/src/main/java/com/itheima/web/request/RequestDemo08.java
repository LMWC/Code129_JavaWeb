package com.itheima.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求转发：
 */
@WebServlet("/requestDemo08")
public class RequestDemo08 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RequestDemo08...");

        //验证请求转发 用户发起请求携带的请求数据不会丢失
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        //获取request域对象中存储的数据
        Object name = request.getAttribute("name");
        System.out.println("name = " + name);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}