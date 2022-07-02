package com.itheima.web;

import com.itheima.bean.Brand;
import com.itheima.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectAllServlet")
public class SelectAllServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数
        //2.调用业务处理
        BrandService brandService = new BrandService();
        List<Brand> list = brandService.selectAll();
        //3.响应
        //3.1：将查询出的所有品牌信息存入request域对象中
        request.setAttribute("list",list);
        //3.2：转发跳转到brand.jsp页面 取出品牌列表数据展示
        request.getRequestDispatcher("/brand.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}