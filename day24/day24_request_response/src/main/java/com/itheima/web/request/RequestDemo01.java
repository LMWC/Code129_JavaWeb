package com.itheima.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Request对象概述：
 *  作用：当用户发起请求时，服务器会自动创建对应Servlet的对象以及request和response对象  同时调用该Servlet中的service方法并且传入request和response对象
 *        request对象 用于封装请求数据（请求行 请求头 请求体）
 *  request体系结构：request对象-->RequestFacade-->HttpServletRequest-->ServletRequest
 *  功能：
 *      1.操作请求行 请求头 请求体
 *      2.实现请求转发【资源跳转方式】
 *      3.作为域对象实现在一次请求间数据共享
 */
@WebServlet("/requestDemo01")
public class RequestDemo01 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RequestDemo01...");

        System.out.println("request = " + request);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}