package com.itheima.web.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 操作响应头：
 *  重定向：
 *          //需求：服务器 responseDemo02接收到用户的请求，告诉用户需要重新发送一次请求到百度  这样的行为我们称为重定向
 *         //实际上 ： 重定向指的就是客户端浏览器重新发送一次新的请求
 * 特点：
 *      1. 重定向是属于客户端行为，表示客户端浏览器重新发送一次请求，因此重定向发送了两次请求
 *      2. 重定向的地址栏路径改变,请求数据会丢失
 *      3. 重定向的路径写绝对路径(带域名/ip地址的, 如果是同一个项目里面的,域名/ip地址可以省略，可以写相对路径)
 *      4. 重定向的路径可以是项目内部的,也可以是项目以外的(eg:百度)
 *      5. 重定向不能重定向到WEB-INF下的资源
 *      6. 把数据存到request域对象里面, 重定向不可用
 *
 *  绝对路径和相对路径：
 *      绝对路径：以盘符或http开头的一个完整路径
 *          eg：
 *              D:\lupin
 *              http://localhost:8080/day24/responseDemo02
 *      相对路径：一般都是以当前文件路径作为参照物 去找到其他文件的路径
 *          ../：上一级目录
 *          ./：当前目录
 *
 *     注意/表示什么：
 *          在javaweb中设置请求地址时，/表示绝对路径
 *          前端页面|设置重定向地址：/表示的是 http://ip地址:端口号/                                 eg: http://localhost:8080/
 *              比如：
 *                  前端页面：<form action="requestDemo06" method="get">
 *                  重定向：response.sendRedirect("/responseDemo01");
 *          服务器后台|转发地址设置：/表示的是 http://ip地址:端口号/项目虚拟路径/                      eg：http://localhost:8080/day24/
 *              比如：
 *                  @WebServlet("/responseDemo02")
 *                  public class ResponseDemo02 extends HttpServlet
 *                  或
 *                  request.getRequestDispatcher("/requestDemo08").forward(request,response);
 *
 *     终极解决方案：
 *          /不管用在前端页面还是服务器后台都表示 http://ip地址:端口号/  eg: http://localhost:8080/ 这样就不会出现路径问题404了
 *          需要设置项目虚拟路径为/就可以了
 *
 */
@WebServlet("/responseDemo02")
public class ResponseDemo02 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ResponseDemo02...");

        //需求：服务器 responseDemo02接收到用户的请求，告诉用户需要重新发送一次请求到百度  这样的行为我们称为重定向
        //实际上 ： 重定向指的就是客户端浏览器重新发送一次新的请求

        //实现方式一：
        //设置响应行状态码
        //response.setStatus(302);
        //设置响应头告诉浏览器重定向地址
        //response.setHeader("location","http://www.baidu.com");

        //实现方式二：【推荐】  项目外部资源  写绝对路径
        //response.sendRedirect("http://www.itcast.cn");

        //重定向到responseDemo01【项目内部资源】  可以写相对路径
        response.sendRedirect("responseDemo01");


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}