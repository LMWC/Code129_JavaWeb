package com.itheima.web.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookieDemo01")
public class CookieDemo01 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("cookieDemo01...");

        //1.服务器发送Cookie数据到浏览器
        //1.1：创建Cookie对象 设置cookie的名称和值
        Cookie cookie = new Cookie("username", "zhangsan");

        //1.2：使用response调用addCookie方法 将cookie写给客户端浏览器
        response.addCookie(cookie);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}