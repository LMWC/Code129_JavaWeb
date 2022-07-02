package com.itheima.web.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Session的基本使用
 *  作为域对象使用
 *  作用范围：一次会话 多次请求  也就是说可以使用Session对象保存数据在多次请求间进行共享
 *  使用API：
 *      存：setAttribute(String name,Object value);
 *      取：getAttribute(String name);
 *      删：removeAttribute(String name);
 */
@WebServlet("/sessionDemo02")
public class SessionDemo02 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("SessionDemo02...");

        //从session域对象取数据
        //1.获取session对象使用
        HttpSession session = request.getSession();

        //打印session对象的sessionId和内存地址
        System.out.println("sessionId = " + session.getId());
        System.out.println("session = " + session);


        //2.调用session对象的getAttribute方法
        Object username = session.getAttribute("username");
        System.out.println("username = " + username);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}