package com.itheima.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Filter入门案例--注解方式：
 *  1.创建一个类实现Filter接口 重写方法
 *  2.编写doFilter方法 【在这里写过滤的业务代码】
 *  3.在Filter类上打上@WebFilter注解 配置该过滤器的拦截资源路径
 *
 *  注意：
 *      1.过滤器对指定的资源进行过滤 具体过滤什么资源  需要看过滤器的拦截路径配置
 *      2.过滤器过滤请求 会进入过滤器的doFilter方法执行
 *          如果放行【chain.doFilter(request,response);】 才会执行到目标请求资源
 *          如果不放行，则执行到Filter就返回了
 */
@WebFilter("/demo01")
public class FilterDemo01 implements Filter {

    //初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    //执行过滤
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("FilterDemo01...");

        //放行
        chain.doFilter(request,response);
    }

    //销毁
    @Override
    public void destroy() {

    }
}
