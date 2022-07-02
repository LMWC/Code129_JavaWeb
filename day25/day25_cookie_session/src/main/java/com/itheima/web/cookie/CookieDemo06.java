package com.itheima.web.cookie;

import com.itheima.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/*
Cookie路径

 */
@WebServlet("/cc/cookieDemo06")
public class CookieDemo06 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("cookieDemo06...");

        //获取名称为address的cookie
        Cookie[] cookies = request.getCookies();
        String cookieValue = CookieUtils.getCookieValue(cookies, "address");
        System.out.println("address = " + cookieValue);


        //写入cookie
        Cookie cookie = new Cookie("email", "shuaige@qq.com");

        response.addCookie(cookie);


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}