package com.itheima.web.request;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 操作请求体：
 *  get方式没有请求体  post方式才有请求体
 *  request.getInputStream()：获取请求体数据字节输入流对象   也就是将post方式请求携带的参数转为流对象传输到服务器后台
 *
 *  获取请求参数：
 *      get方式：request.getQueryString();
 *      post方式：request.getInputStream();
 *
 */
@WebServlet("/requestDemo04")
public class RequestDemo04 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RequestDemo04...");

        //获取请求体数据  也就是获取post请求方式提交的参数
        ServletInputStream is = request.getInputStream();

        //需要将字节输入流转为字符输入流  需要用到转换流   字节输入流--转换流-->字符输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while((line=br.readLine())!=null){
            System.out.println("line = " + line);
        }

        //关闭流
        br.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}