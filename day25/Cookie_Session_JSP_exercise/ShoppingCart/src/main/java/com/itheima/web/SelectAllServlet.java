package com.itheima.web;

import com.itheima.bean.Product;
import com.itheima.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectAll")
public class SelectAllServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数【没有】
        //2.调用业务处理
        ProductService studentService = new ProductService();
        List<Product> list = studentService.selectAll();
        System.out.println("list = " + list);

        //3.将查询返回的list集合数据存入request域对象 转发到brand.jsp页面展示
        request.setAttribute("list",list);
        request.getRequestDispatcher("goods.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}