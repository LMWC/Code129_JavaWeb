package com.itheima.web;

import com.itheima.bean.User;
import com.itheima.service.UserService;
import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //1.获取用户输入的验证码
        String checkCode = request.getParameter("checkCode");
        //获取session中存储的验证码
        HttpSession session = request.getSession();
        String checkCodeGen = (String)session.getAttribute("checkCode");

        //2.比对用户输入的验证码和session中存储的验证码是否一致
        if(checkCode==null || !checkCode.equalsIgnoreCase(checkCodeGen)){
            //3.1：如果不一致 则提示用户验证码输入有误
            request.setAttribute("register_msg","验证码输入有误！");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            //跳出 不允许注册
            return;
        }

        //3.2：如果一致 获取用户名和密码 根据用户名查询用户user是否存在 true,用户名已存在 不允许注册  false 用户名不存在 允许注册
        //4.1：获取用户名 密码 封装到user对象中
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);


        //4.2：用户名密码非空判断
        if(StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(password)){
            request.setAttribute("register_msg","用户名或密码不能为空！");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }


        //4.3：调用业务处理，完成用户注册
        UserService userService = new UserService();
        boolean flag = userService.register(user);

        if (flag){
            request.setAttribute("register_msg","用户注册成功,请登录！");
            response.sendRedirect("/login.jsp");
        }else{
            request.setAttribute("register_msg","用户名已存在！");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}