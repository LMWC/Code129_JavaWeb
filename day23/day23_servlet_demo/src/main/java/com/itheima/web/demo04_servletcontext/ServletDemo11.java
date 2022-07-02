package com.itheima.web.demo04_servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * ServletContext作为域对象使用：在整个应用之间进行数据共享
 *  API：
 *      存：setAttribute(String name,Object value);
 *      取：getAttribute(String name);
 *      删：removeAttribute(String name);
 */
@WebServlet("/demo11")
public class ServletDemo11 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletDemo11...");

        //1.向ServletContext域对象中存储数据
        ServletContext servletContext = this.getServletContext();

        servletContext.setAttribute("name","张静");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}