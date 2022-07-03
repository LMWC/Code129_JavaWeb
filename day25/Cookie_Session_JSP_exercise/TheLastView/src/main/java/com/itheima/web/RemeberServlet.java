package com.itheima.web;

import com.itheima.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/remember")
public class RemeberServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //处理响应中文乱码
        response.setContentType("text/html;charset=UTF-8");

        //1.获取用户上次访问时间
        Cookie[] cookies = request.getCookies();
        String lastTime = CookieUtils.getCookieValue(cookies, "lastTime");

        if (lastTime==null){
            //2.lastTime==null：第一次访问
            //2.1：将当前时间存入名称为lastTime的Cookie中  设置Cookie的有效时间
            Date date = new Date();
            String dateStr = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss").format(date);

            //创建cookie  cookie值包含空格 空格属于特殊字符 需要编码后再存入
            Cookie cookie = new Cookie("lastTime", URLEncoder.encode(dateStr, "UTF-8"));
            //设置cookie有效时间
            cookie.setMaxAge(60*60*24*30);
            //写入cookie
            response.addCookie(cookie);

            //2.2：响应浏览器 "你是第一次访问！"
            response.getWriter().print("你是第一次访问!");
        }else{
            //3.lastTime!=null：不是第一次访问
            //3.1：将当前时间存入名称为lastTime的Cookie中  设置Cookie的有效时间
            Date date = new Date();
            String dateStr = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss").format(date);
            //创建cookie  cookie值包含空格 空格属于特殊字符 需要编码后再存入
            Cookie cookie = new Cookie("lastTime", URLEncoder.encode(dateStr, "UTF-8"));
            //设置cookie有效时间
            cookie.setMaxAge(60*60*24*30);

            //写入cookie
            response.addCookie(cookie);
            //3.2：响应浏览器 "你上次访问的时间是：xxx"
            response.getWriter().print("上次访问时间："+ URLDecoder.decode(lastTime,"UTF-8"));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}