package com.itheima.web;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Servlet入门程序-注解方式：
 *  1.创建JavaWEB项目 添加依赖 servlet-api
 *  2.创建一个类实现Servlet接口 重新方法
 *  3.在Servlet类上打上@WebServlet注解 配置请求访问路径  通过路径找到这个Servlet处理请求
 */
//配置ServletDemo01的请求访问地址   一旦你请求http://localhost:8080/day23/demo01 就进入ServletDemo01中的service方法处理请求
@WebServlet("/demo01")
public class ServletDemo01 implements Servlet {
    public void init(ServletConfig config) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    //服务  一旦这个Servlet接收到了用户的请求 就会进入service方法中进行请求处理
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("ServletDemo01...");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
