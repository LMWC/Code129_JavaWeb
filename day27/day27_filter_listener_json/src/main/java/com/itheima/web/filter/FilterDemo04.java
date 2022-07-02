package com.itheima.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 过滤器拦截路径配置：
 *      1.拦截具体的资源：以/开头的完整路径,例如: /demo04：只有访问demo04时才会被拦截。   eg：  @WebFilter("/demo04")
 *      2.目录拦截：以/开头,以/*结尾,例如: /user/*：访问/user下的所有资源，都会被拦截    eg： @WebFilter("/user/*")
 *      3.后缀名拦截：不能以/开头,例如: *.jsp：访问后缀名为jsp的资源，都会被拦截          eg：  @WebFilter("*.do")
 *      4.拦截所有：/*：访问所有资源，都会被拦截
 *
 *      注意：
 *          1.过滤器拦截路径配置没有优先级  一个资源可以被多个过滤器拦截
 *          2.拦截具体的资源时，拦截路径/开头 /不能省略  一旦缺失/，会导致项目无法启动
 *          3.过滤器拦截所有请求配置/*，没有配置/的用法
 *          4.只要请求访问地址匹配过滤器拦截路径 过滤器就会执行doFilter  哪怕这个资源不存在也不影响
 */
@WebFilter("/*")
public class FilterDemo04 implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("FilterDemo04...");

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}