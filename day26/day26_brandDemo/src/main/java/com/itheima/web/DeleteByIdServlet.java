package com.itheima.web;

import com.itheima.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteByIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1.获取请求参数 【要删除的品牌id】
            Integer id = Integer.parseInt(request.getParameter("id"));
            System.out.println("要删除的品牌id = " + id);

            //2.调用业务处理
            BrandService brandService = new BrandService();
            int rows = brandService.delete(id);

            //3.不管删除成功还是失败 都重新加载所有品牌记录 【重定向到selectAll】
            response.sendRedirect("selectAll");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("服务器异常！");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}