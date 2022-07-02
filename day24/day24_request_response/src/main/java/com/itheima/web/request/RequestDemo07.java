package com.itheima.web.request;

import com.itheima.bean.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 请求转发：
 *  概念：表示服务器内部的一种资源跳转方式
 *  需求：用户在浏览器请求requestDemo07，但是requestDemo07处理不了用户的请求，自动将用户的请求交给了requestDemo08处理？
 *  分析实现：需要使用请求转化  requestDemo07将用户的请求交给requestDemo08的过程就是一个请求转发
 *  实现代码：
 *      request.getRequestDispatcher("转发地址").forward(request,response);
 *  特点：
 *      1.请求转发是服务器内部行为，对于客户端用户来说，只发送了一次请求
 *      2.请求转发地址栏请求路径不会变，并且用户的请求数据也不会丢失
 *      3.请求转发只能转发到本项目中的资源,不能转发到外部项目资源
 *      4.请求转发的路径只能写相对路径,不能写绝对路径
 *      5.WEB-INF里面的资源不能直接访问，但是可以通过请求转发访问到WEB-INF里面的资源
 *
 *  request作为域对象使用：
 *      作用范围：一次请求间有效
 *      使用场景：只能用于请求转发中  在A资源中使用request域对象存储数据  在B资源中获取使用
 *      API：
 *          存：setAttribute(String name,Object value);
 *          取：getAttribute(String name);
 *          删：removeAttribute(String name);
 */
@WebServlet("/requestDemo07")
public class RequestDemo07 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RequestDemo07...");


        //转发之前 在request域对象中存储数据实现一次请求间共享
        request.setAttribute("name","zhangshuai");


        //http://localhost:8080/day24/requestDemo07
        //http://localhost:8080/day24/requestDemo08
        //现在从requestDemo07跳转到requestDemo08就可以直接写相对路径  写的就是requestDemo08
        request.getRequestDispatcher("requestDemo08").forward(request,response);


        //请求转发到百度  访问不到
        //request.getRequestDispatcher("http://www.baidu.com").forward(request,response);

        //请求转发到WEB-INF下的a.html  正常访问
        //request.getRequestDispatcher("WEB-INF/a.html").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}