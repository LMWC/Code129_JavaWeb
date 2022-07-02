package com.itheima.web;

import com.itheima.utils.CheckCodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/*
    CheckCodeServlet：专门生成验证码
    实现思路： 
        1.生成一个验证码图片【工具类】 
        2.将验证码图片写给客户端浏览器显示 
        3.将验证码存储到session域对象中 实现数据在一次会话间共享
 */
@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //提前使用request对象获取session对象使用
        HttpSession session = request.getSession();

        //1.生成一个验证码图片【工具类】 
        //2.将验证码图片写给客户端浏览器显示 
        String checkCode = CheckCodeUtil.outputVerifyImage(200, 50, response.getOutputStream(), 4);

        System.out.println("checkCode = " + checkCode);

        //3.将验证码存储到session域对象中 实现数据在一次会话间共享
        session.setAttribute("checkCode",checkCode);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}