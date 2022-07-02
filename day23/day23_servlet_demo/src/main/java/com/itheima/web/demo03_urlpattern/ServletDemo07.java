package com.itheima.web.demo03_urlpattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  Servlet请求访问路径配置：
 *      1.完全路径匹配：以/开头 /不能省略     eg:@WebServlet("/demo07")  只能接收/demo07的请求
 *      2.目录匹配：以/开头 以*结尾          eg:@WebServlet("/user/*")  可以接收user路径下的所有请求
 *      3.扩展名匹配：以*开头               eg:@WebServlet("*.do")     可以接收以.do结尾的所有请求
 *      4.全局匹配：
 *          /：1.当用户访问的地址不存在对应的Servlet和jsp时就会进入路径配置为/的Servlet  2.当用户访问静态资源时也会直接进入路径配置为/的Servlet  eg：@WebServlet("/")
 *          /*：除了存在对应地址的Servlet之外，其他的访问都会进入到配置为/*的Servlet中【包括请求jsp、html直接进入】        eg：@WebServlet("/*")
 *
 * Servlet请求访问路径优先级：
 *      完全路径匹配>目录匹配>/*>扩展名匹配>/
 * 建议：一般开发中，使用完全路径匹配和目录匹配，对应全局匹配只有在SpringMVC框架中使用到了 核心控制器配置为/
 * 注意：
 *  1.完全路径路径匹配 /不能缺少  如果缺少 会导致项目无法正常启动
 *      报错：Caused by: java.lang.IllegalArgumentException: Invalid <url-pattern> demo07 in servlet mapping
 *  2.一个路径配置多个Servlet 会导致项目无法正常启动
 *      报错：Caused by: java.lang.IllegalArgumentException: The servlets named [com.itheima.web.demo02_architecture.ServletDemo06]
 *                      and [com.itheima.web.demo03_urlpattern.ServletDemo07]
 *                      are both mapped to the url-pattern [/demo06] which is not permitted
 * 3.一个Servlet可以配置多个访问路径   eg:@WebServlet({"/demo7","/demo07","/demo007"})
 *
 */
@WebServlet({"/demo7","/demo07","/demo007"})
public class ServletDemo07 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletDemo07...");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}