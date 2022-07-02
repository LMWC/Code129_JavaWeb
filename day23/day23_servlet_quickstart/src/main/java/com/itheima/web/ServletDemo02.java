package com.itheima.web;

import javax.servlet.*;
import java.io.IOException;

/**
 * Servlet入门程序-xml配置方式：
 *  1.创建一个类 实现Servlet接口 重写方法
 *  2.在web.xml中配置Servlet的请求访问地址
 *
 *      pom.xml：针对Maven工具使用配置
 *      web.xml：针对当前的javaweb项目配置
 *      mybatis-config.xml：针对mybatis程序的配置
 */
public class ServletDemo02 implements Servlet {
    public void init(ServletConfig config) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("ServletDemo02...");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
