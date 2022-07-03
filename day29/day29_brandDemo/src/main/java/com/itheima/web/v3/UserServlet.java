package com.itheima.web.v3;

import com.alibaba.fastjson.JSON;
import com.itheima.bean.Brand;
import com.itheima.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    //用户注册
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("===用户注册===");
    }

    //用户登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("===用户登录===");
    }
}