package com.itheima.web;

import com.itheima.bean.Brand;
import com.itheima.service.BrandService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //0.处理请求响应中文乱码
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            //1.获取请求参数  【要修改的品牌数据信息  封装到Brand对象中】
            Map<String, String[]> parameterMap = request.getParameterMap();
            Brand brand = new Brand();
            BeanUtils.populate(brand,parameterMap);

            //2.调用业务处理
            BrandService brandService = new BrandService();
            int rows = brandService.update(brand);

            //3.修改完成 不管成功还是失败 都重定向到SelectAllServlet 重新加载所有品牌数据展示
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