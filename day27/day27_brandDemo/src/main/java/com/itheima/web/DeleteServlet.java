package com.itheima.web;

import com.itheima.bean.Brand;
import com.itheima.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.处理请求响应乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //1.获取请求信息 根据id删除 获取要删除的品牌id
        String id = request.getParameter("id");

        //2.调用业务处理
        BrandService brandService = new BrandService();
        int rows = brandService.delete(Integer.parseInt(id));

        //3.删除完成 不管成功失败 重新查询所有品牌信息展示
        response.sendRedirect("/selectAllServlet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}