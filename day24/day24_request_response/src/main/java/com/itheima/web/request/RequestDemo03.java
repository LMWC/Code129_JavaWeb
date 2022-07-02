package com.itheima.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 操作请求头：
 *  方法：getHeader(String name):获取指定名称请求头的值
 *  常用的请求头：
 *      user-agent：客户端浏览器的版本信息  方便针对不同的浏览器做兼容性处理
 *      referer：从哪里进入到当前资源的地址  如果是直接访问 则为null 防盗链
 */
@WebServlet("/requestDemo03")
public class RequestDemo03 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //user-agent：客户端浏览器的版本信息  方便针对不同的浏览器做兼容性处理
        String userAgent = request.getHeader("user-agent");
        System.out.println("userAgent = " + userAgent);
        if(userAgent.contains("Firefox")){
            response.getWriter().print("Hello Firefox");

        }else if(userAgent.contains("Chrome")){
            response.getWriter().print("Hello Chrome");

        }else{
            response.getWriter().print("Hello baibai");
        }

        //referer：从哪里进入到当前资源的地址  如果是直接访问 则为null 防盗链
        String referer = request.getHeader("referer");
        System.out.println("referer = " + referer);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}