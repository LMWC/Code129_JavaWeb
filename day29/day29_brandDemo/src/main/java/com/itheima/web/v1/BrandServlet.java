package com.itheima.web.v1;

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

//@WebServlet("/brand/*")
public class BrandServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.截取请求地址
        String uri = request.getRequestURI(); //eg：查询所有：/brand/selectAll --截取得到-->selectAll
        int index = uri.lastIndexOf("/"); // /最后一次出现的位置
        String methodName = uri.substring(index + 1);
        System.out.println("methodName = " + methodName);

        //2.根据截取的请求地址调用相应的方法
        if(methodName.equals("selectAll")){
            selectAll(request, response);
        }else if(methodName.equals("add")){
            add(request, response);
        }else if(methodName.equals("update")){
            add(request, response);
        }else if(methodName.equals("delete")){
            add(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    //查询所有
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("===查询所有品牌===");

        //0.处理请求响应中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        //1.获取请求参数【没有】

        //2.调用业务处理
        BrandService brandService = new BrandService();
        List<Brand> list = brandService.selectAll();

        //3.响应json数据

        //将brand java对象 转成json数据时 根据Brand类中的getStatusStr()方法 为json对象封装了一个statusStr属性数据
        String jsonStr = JSON.toJSONString(list);
        System.out.println("jsonStr = " + jsonStr);


        response.getWriter().print(jsonStr);
    }

    //添加品牌
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("===添加品牌===");

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

    //修改品牌
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("===修改品牌===");
    }

    //删除品牌
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("===删除品牌===");
    }
}