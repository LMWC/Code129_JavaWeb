package com.itheima.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/*
  Filter生命周期：
    0.实例化：过滤器在服务器启动时，创建Filter对象
    1.初始化：过滤器在服务器启动时，创建好Filter对象之后执行init()方法进行初始化         【执行一次】
    2.过滤：当请求资源路径和过滤器拦截路径匹配时，每次请求，都会执行doFilter()方法进行过滤   【执行多次】
    3.销毁：当服务器正常关闭或项目从服务器移除时 执行destroy()方法销毁Filter对象         【执行一次】

  Filter生命周期和Servlet生命周期的区别？
    过滤器Filter是在服务器启动时创建对象并完成初始化
    Servlet默认是在来了第一次请求时进行创建对象并完成初始化   如果想让Servlet在服务器启动时也完成对象的创建并初始化，就需要设置Servlet的启动项参数loadOnStartUp

 */
@WebFilter("/demo03")
public class FilterDemo03 implements Filter {

    public FilterDemo03() {
        System.out.println("Filter实例化111...");
    }

    @Override
    public void destroy() {
        System.out.println("Filter销毁4444...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("FilterDemo03...");

        System.out.println("Filter执行过滤333...");

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("Filter初始化222...");
    }

}