package com.itheima.web.demo04_servletcontext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  init：在初始化方法中  初始化访问次数为0
 *  service-->doPost：当有用户访问CountServlet时  需要从ServletContext对着中取出访问次数 +1  然后再存进去 页面显示"Welcome..."
 */
@WebServlet("/count")
public class CountServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ServletContext servletContext = config.getServletContext();
        //设置访问次数初始值为0
        servletContext.setAttribute("count",0);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //当有用户访问CountServlet时  需要从ServletContext对着中取出访问次数 +1  然后再存进去 页面显示"Welcome..."
        ServletContext servletContext = this.getServletContext();

        Integer count = (Integer) servletContext.getAttribute("count");

        count++;

        servletContext.setAttribute("count",count);

        //响应浏览器页面内容
        response.getWriter().print("Welcome...");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}