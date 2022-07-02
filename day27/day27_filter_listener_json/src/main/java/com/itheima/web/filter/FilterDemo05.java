package com.itheima.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/*
  过滤器链：
    概念：在一个web应用中，一个资源存在多个过滤器对其进行过滤，这多个过滤器在一起就组成了过滤器链。
    多个过滤器过滤同一资源  过滤器之间执行的顺序是什么样？
        过滤器链中过滤器的执行顺序：

    示例：FilterDemo02、FilterDemo04、FilterDemo05这个三个过滤器都对ServletDemo01进行拦截过滤，这三个过滤器就组成了一个过滤器链 FilterChain
        在过滤器链FilterChain对象中就记录了这多个过滤器之间的执行顺序 
            1.xml方式配置的过滤器先执行，注解方式配置的过滤器后执行 
            2.xml方式配置 filter-mapping在前的先执行， 注解方式配置，根据Filter名称先后顺序执行
    过滤器链中过滤器执行原理：
        当第一个过滤器执行chain.doFilter()方法之后，会继续调用下一个过滤器执行，如果当前的这个过滤器就是过滤器中的最后一个过滤器，就会调用目标资源执行。

 */
//@WebFilter("/*")
public class FilterDemo05 implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("FilterDemo05...");

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}