package com.itheima.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 操作请求行：
 *   getMethod()：获取请求方式
 *   getContextPath();获得项目发布虚拟路径
 *   getRequestURI();获得请求地址，不带协议、ip地址和端口号  应用场景：权限控制、网关
 */
@WebServlet("/requestDemo02")
public class RequestDemo02 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //- getMethod();获取请求方式
        String method = request.getMethod();
        System.out.println("method = " + method);

        //- getRemoteAddr() ；获取客户机的IP地址(知道是谁请求的)
        String remoteAddr = request.getRemoteAddr();
        System.out.println("remoteAddr = " + remoteAddr);

        //- getContextPath();获得项目发布虚拟路径 【重点】
        String contextPath = request.getContextPath();
        System.out.println("contextPath = " + contextPath);

        //- getRequestURI();获得请求地址，不带协议、ip地址和端口号
        String uri = request.getRequestURI();
        System.out.println("uri = " + uri);

        //- getRequestURL()；获得请求地址，带协议、ip地址和端口号
        StringBuffer url = request.getRequestURL();
        System.out.println("url = " + url);

        //- getServerPort()；获得服务端的端口
        int serverPort = request.getServerPort();
        System.out.println("serverPort = " + serverPort);

        //- getQueryString()；获的get方式请求参数(URL的?后面拼接的请求参数  eg:username=zs&password=123456)
        String queryString = request.getQueryString();
        System.out.println("queryString = " + queryString);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}