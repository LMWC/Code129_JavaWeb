package com.itheima.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
    实现思路：
        1.创建一个Filter LoginFilter 设置拦截路径为/*
        2.编写doFilter方法中的过滤业务代码
            2.0: 强转两个对象
            2.1：判断用户请求的资源是否需要进行拦截 【根据用户请求的资源地址【URI】进行判断】
                2.1.1：不需要登录：直接放行
                2.1.2：需要登录：进行登录验证【判断session存储的是否有user对象】
                    3.1：user!=null：已经登录 放行
                    3.2：user==null：没有登录 跳转到登录页面
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //1.强转两个对象
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //2.1：判断用户请求的资源是否需要进行拦截 【根据用户请求的资源地址【URI】进行判断】
        String uri = req.getRequestURI();
        System.out.println("uri = " + uri);

        if(uri.contains("index") || uri.contains("register") || uri.contains("login") || uri.contains("css") || uri.contains("img")){
            //2.1.1：不需要登录：直接放行  【建议放行强转后的对象】
            chain.doFilter(req, resp);
        }else{
            //2.1.2：需要登录：进行登录验证【判断session存储的是否有user对象】
            Object user = req.getSession().getAttribute("user");

            if(user!=null){
                //3.1：user!=null：已经登录 放行
                chain.doFilter(req, resp);
            }else{
                //3.2：user==null：没有登录 跳转到登录页面
                resp.sendRedirect("login.jsp");
            }
        }

    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}