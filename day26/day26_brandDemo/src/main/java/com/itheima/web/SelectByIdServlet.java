package com.itheima.web;

import com.itheima.bean.Brand;
import com.itheima.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/selectById")
public class SelectByIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数  【要修改的品牌id】
        Integer id = Integer.parseInt(request.getParameter("id"));

        //2.调用业务处理 【根据id查询对应的品牌数据记录】
        BrandService brandService = new BrandService();
        Brand brand = brandService.selectById(id);
        System.out.println("brand = " + brand);

        //3.将品牌数据存入request域对象中  转发到updateBrand.jsp页面展示
        request.setAttribute("brand",brand);
        request.getRequestDispatcher("updateBrand.jsp").forward(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}