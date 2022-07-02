package com.itheima.web.demo04_servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ServletContext作为域对象使用：
 */
@WebServlet("/demo12")
public class ServletDemo12 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletDemo12...");

        //2..从ServletContext域对象中取出数据
        ServletContext servletContext = this.getServletContext();

        Object name = servletContext.getAttribute("name");

        System.out.println("name = " + name);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}