package com.itheima.web;

import com.itheima.bean.User;
import com.itheima.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.请求响应中文乱码处理
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //1.获取用户登录 用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //2.调用业务处理
        UserService userService = new UserService();
        User user = userService.login(username, password);

        //3.响应
        if(user!=null){
            //登录成功
            //如果用户勾选了记住用户名密码的复选框  将用户名和密码保存在cookie中 用户在下次登录时无需输入用户名和密码
            String remember = request.getParameter("remember");
            if("on".equals(remember)){
                //1.创建cookie
                Cookie cookie01 = new Cookie("username", username);
                Cookie cookie02 = new Cookie("password", password);
                //2.设置cookie有效时间
                cookie01.setMaxAge(60*60*24*7);
                cookie02.setMaxAge(60*60*24*7);
                //3.将cookie写给客户端浏览器
                response.addCookie(cookie01);
                response.addCookie(cookie02);
            }

            //a.将用户信息存入session中
            request.getSession().setAttribute("user",user);
            //b.跳转到查询所有品牌信息列表页面
            response.sendRedirect("/selectAllServlet");

        }else{
            //登录失败 将错误提示信息存入request域对象中 跳转到login.jsp页面
            request.setAttribute("login_msg","用户名或密码不匹配");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}