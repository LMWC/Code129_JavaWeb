package com.itheima.web;

import com.itheima.util.CheckCodeUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.使用工具类 生成验证码 写给客户端浏览器页面
        ServletOutputStream outputStream = response.getOutputStream();
        String checkCode = CheckCodeUtil.outputVerifyImage(100, 50, outputStream, 4);

        //2.将验证码写入session
        request.getSession().setAttribute("checkCode",checkCode);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}