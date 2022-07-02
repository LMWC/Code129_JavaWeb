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

@WebServlet("/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //0.处理请求响应中文乱码
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            //1.获取请求参数【要添加的品牌数据】 封装到Brand对象中
            Map<String, String[]> parameterMap = request.getParameterMap();
            Brand brand = new Brand();
            BeanUtils.populate(brand,parameterMap);

            //2.调用业务处理
            BrandService brandService = new BrandService();
            int rows = brandService.add(brand);

            //3.响应：无论添加成功还是失败 都重新查询所有品牌信息展示  【重定向到SelectAllServlet】
            response.sendRedirect("selectAll");

            //转发到SelectAllServlet 重新查询所有品牌信息展示
            //如果使用转发跳转到selectAll 会导致和之前的添加add请求属于一次请求  一旦刷新浏览器 就会进行表单重复提交
            //所以 一般在进行增删改之后 需要重新查询显示所有数据时  都是使用重定向到查询所有数据的Servlet
            //request.getRequestDispatcher("selectAll").forward(request,response);

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