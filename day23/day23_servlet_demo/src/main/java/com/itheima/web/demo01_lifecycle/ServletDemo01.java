package com.itheima.web.demo01_lifecycle;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Servlet生命周期
 *  实例化：默认在来了第一次请求的时候，web服务器调用Servlet类的无参构造方法创建Servlet类的对象       【执行一次】
 *  初始化：默认在来了第一次请求时，创建对象后会有web服务器调用init方法完成Servlet对象初始化工作       【执行一次】
 *  服务：每次请求Servlet时，服务器都会调用service方法处理请求                                    【执行多次】
 *  销毁：当服务器正常关闭 或 项目从服务器移除时 web服务器调用Servlet的destroy方法完成servlet对象销毁工作  【执行一次】
 *  扩展：
 *      Servlet单例多线程的，不管有多少用户访问这个Servlet，它只会创建一次，在程序运行时，内存中有且仅有一个对象。注意：不要在Servlet中 共享数据，容易导致线程不安全问题产生。
 */

@WebServlet(value = "/demo01",loadOnStartup = 2)
public class ServletDemo01 implements Servlet {

    public ServletDemo01() {
        System.out.println("ServletDemo01实例化 111...");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("ServletDemo01初始化 222...");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("ServletDemo01服务 333...");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("ServletDemo01销毁 444...");
    }
}
