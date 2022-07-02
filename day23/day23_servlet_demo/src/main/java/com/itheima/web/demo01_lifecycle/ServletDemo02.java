package com.itheima.web.demo01_lifecycle;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Servlet生命周期相关扩展：
 *  1.Servlet初始化参数：只能在当前Servlet中获取使用
 *      ServletConfig对象：Servlet的配置对象
 *          1.获取初始化参数：getInitParameter("key");
 *          2.获取ServletContext对象：getServletContext()
 *
 *  2.配置Servlet启动项参数：让Servlet对象创建初始化在服务器启动时就提前完成，当用户请求Servlet时，服务器可以直接调用service方法进行处理  提高用户体验感
 *      servlet启动项参数：配置正整数，就表示服务器启动时完成创建初始化工作  参数越小，优先级越高
 */
@WebServlet(value = "/demo02",initParams = {@WebInitParam(name="akey",value = "aaa")},loadOnStartup = 1)
public class ServletDemo02 implements Servlet {

    //初始化
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        String akey = config.getInitParameter("akey");
        System.out.println("akey = " + akey);
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("ServletDemo02...");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
