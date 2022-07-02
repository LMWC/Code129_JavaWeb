package com.itheima.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Filter入门-XML配置方式：
 *  1.创建一个类 实现Filter接口 重写所有方法
 *  2.在web.xml中配置过滤器的拦截路径
 *
 *  javaweb三大组件使用：
 *      1.创建类 实现接口
 *      2.配置
 */
public class FilterDemo02 implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("FilterDemo02...");

        //放行
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}