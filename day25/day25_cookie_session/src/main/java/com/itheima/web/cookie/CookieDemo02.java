package com.itheima.web.cookie;

import com.itheima.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookieDemo02")
public class CookieDemo02 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("cookieDemo02...");

        //2.服务器接收Cookie数据
        //2.1：使用request的getCookies方法 获取浏览器携带到服务器的所有cookie对象
        Cookie[] cookies = request.getCookies();

        //2.2：遍历cookie数组，根据cookie名称获取指定cookie的值
        /*if(cookies!=null){
            for (Cookie cookie : cookies) {
                if("username".equals(cookie.getName())){
                    System.out.println("username = "+ cookie.getValue());
                }
            }
        }*/

        //使用CookieUtils工具类获取指定Cookie名称的值
        String cookieValue = CookieUtils.getCookieValue(cookies, "username");
        System.out.println("cookieValue = " + cookieValue);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}