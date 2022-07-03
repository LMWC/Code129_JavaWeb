package com.itheima.web.v0;

import com.alibaba.fastjson.JSON;
import com.itheima.bean.Brand;
import com.itheima.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/selectAll")
public class SelectAllServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.处理请求响应中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        //1.获取请求参数【没有】

        //2.调用业务处理
        BrandService brandService = new BrandService();
        List<Brand> list = brandService.selectAll();

        //3.响应json数据

        //将brand java对象 转成json数据时 根据Brand类中的getStatusStr()方法 为json对象封装了一个statusStr属性数据
        String jsonStr = JSON.toJSONString(list);
        System.out.println("jsonStr = " + jsonStr);


        response.getWriter().print(jsonStr);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}