package com.itheima.web.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
   演示删除Cookie
 */
@WebServlet("/cookieDemo05")
public class CookieDemo05 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("cookieDemo05...");

        //写入cookie
        Cookie cookie = new Cookie("address", "shenzhen");

        //设置有效时间 【删除cookie  在cookie路径相同时，如果cookie名称也相同 就表示同一个Cookie】
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}