package com.itheima.web;

import com.itheima.bean.Brand;
import com.itheima.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.处理请求响应乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //1.获取请求信息 将要添加的品牌信息封装到Brand对象中
        String brandName = request.getParameter("brandName");
        String companyName = request.getParameter("companyName");
        String ordered = request.getParameter("ordered");
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        Brand brand = new Brand();
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setOrdered(Integer.parseInt(ordered));
        brand.setDescription(description);
        brand.setStatus(Integer.parseInt(status));

        //2.调用业务处理
        BrandService brandService = new BrandService();
        int rows = brandService.add(brand);

        //3.添加完成 不管成功失败 重新查询所有品牌信息展示
        //注意：不要使用转发跳转 使用转发跳转地址栏不发生改变，请求信息不会丢失 F5刷新会重新提交一次添加请求 新增一条记录 导致表单重复提交
        //request.getRequestDispatcher("/selectAllServlet").forward(request,response);

        //增删改之后 使用重定向跳转 重新查询
        response.sendRedirect("/selectAllServlet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}