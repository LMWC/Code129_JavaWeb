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

@WebServlet("/selectByIdServlet")
public class SelectByIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数  根据id修改品牌信息 获取品牌id
        String id = request.getParameter("id");
        //2.调用业务处理
        BrandService brandService = new BrandService();
        Brand brand = brandService.selectById(Integer.parseInt(id));
        //3.响应
        //3.1：将查询出的单个品牌信息存入request域对象中
        request.setAttribute("brand",brand);
        //3.2：转发跳转到updateBrand.jsp页面 取出品牌数据展示完成回显
        request.getRequestDispatcher("/updateBrand.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}