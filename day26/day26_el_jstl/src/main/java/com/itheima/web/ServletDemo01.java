package com.itheima.web;

import com.itheima.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/selectAll")
public class ServletDemo01 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //需求：查询所有用户信息
        //1.处理请求响应中文乱码
        //2.获取请求参数【没有】
        //3.查询数据库得到所有用户列表数据 List<User>

        //模拟数据库查询出来的数据列表
        List<User> list = new ArrayList<>();
        User user1 = new User(1, "zs", "123");
        User user2 = new User(2, "ls", "234");
        User user3 = new User(3, "ww", "456");
        list.add(user1);
        list.add(user2);
        list.add(user3);

        //4.将list集合数据存入request域对象中
        request.setAttribute("list",list);

        //5.转发跳转到demo08.jsp页面显示所有用户列表数据
        request.getRequestDispatcher("demo08.jsp").forward(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}