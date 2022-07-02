package com.itheima.web.cookie;

import com.itheima.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

/*
Cookie进阶：
    1.Cookie 存活时间
        默认情况下，Cookie 存储在浏览器内存中，当浏览器关闭，内存释放，则Cookie被销毁
        setMaxAge(int seconds)：设置Cookie存活时间
            正数：将 Cookie写入浏览器所在电脑的硬盘，持久化存储。到时间自动删除
            负数：默认值，Cookie在当前浏览器内存中，当浏览器关闭，则 Cookie被销毁
            零：删除对应 Cookie

 */
@WebServlet("/cookieDemo04")
public class CookieDemo04 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("cookieDemo04...");

        //获取cookie
        Cookie[] cookies = request.getCookies();

        String cookieValue = CookieUtils.getCookieValue(cookies, "address");
        String cookieValue1 = CookieUtils.getCookieValue(cookies, "sex");
        String cookieValue2 = CookieUtils.getCookieValue(cookies, "country");

        System.out.println("cookieValue = " + cookieValue);
        System.out.println("cookieValue1 = " + cookieValue1);
        System.out.println("cookieValue2 = " + URLDecoder.decode(cookieValue2,"UTF-8"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}