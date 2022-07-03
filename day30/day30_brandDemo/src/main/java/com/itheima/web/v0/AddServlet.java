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

//@WebServlet("/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.处理请求响应中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        try {
            //1.获取请求参数【封装到Brand对象中】
            Brand brand = JSON.parseObject(request.getInputStream(), Brand.class);

            //2.调用业务处理
            BrandService brandService = new BrandService();
            int rows = brandService.add(brand);

            //3.响应数据 true|false
            if(rows>0){
                response.getWriter().print(true);
            }else{
                response.getWriter().print(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print(false);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}