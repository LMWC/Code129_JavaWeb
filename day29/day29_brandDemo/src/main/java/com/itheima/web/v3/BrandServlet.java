package com.itheima.web.v3;

import com.alibaba.fastjson.JSON;
import com.itheima.bean.Brand;
import com.itheima.bean.PageBean;
import com.itheima.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {

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
        System.out.println("BrandServlet udpate");
        //0.处理请求响应中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        try {
            //1.获取请求参数 【json格式请求参数转为java对象】
            Brand brand = JSON.parseObject(request.getInputStream(), Brand.class);

            //2.调用业务处理
            BrandService brandService = new BrandService();
            int rows = brandService.update(brand);

            //3.响应处理结果
            if(rows>0){
                response.getWriter().print(true);
            }else{
                response.getWriter().print(false);
            }
        } catch (Exception e) {
            response.getWriter().print(false);
            e.printStackTrace();
        }
    }

    //删除品牌
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("===删除品牌===");
        //1.接收请求参数
        Integer id = Integer.parseInt(request.getParameter("id"));

        //2.调用业务处理
        BrandService brandService = new BrandService();
        int rows = brandService.delete(id);

        //3.响应处理结果
        if(rows>0){
            response.getWriter().print(true);
        }else{
            response.getWriter().print(false);
        }
    }

    //批量删除品牌
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("===批量删除品牌===");
        System.out.println("BrandServlet deleteBatchs");

        //1.接收请求参数   【id数组】
        int[] ids = JSON.parseObject(request.getInputStream(), int[].class);
        System.out.println("ids = " + Arrays.toString(ids));

        //2.调用业务处理
        BrandService brandService = new BrandService();
        int rows = brandService.deleteByIds(ids);

        //3.响应处理结果
        if(rows>0){
            response.getWriter().print(true);
        }else{
            response.getWriter().print(false);
        }
    }

    //分页查询
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("===分页查询===");
        //0.处理请求响应中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        //1.接收请求参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        //如果前端没有传递当前页码或每页显示条数 则使用设置的默认值
        int currentPage = currentPageStr==null||currentPageStr.length()==0?1:Integer.parseInt(currentPageStr);
        int pageSize = pageSizeStr==null||pageSizeStr.length()==0?5:Integer.parseInt(pageSizeStr);

        //2.调用Service处理 得到PageBean对象
        BrandService brandService = new BrandService();
        PageBean<Brand> pageBean = brandService.selectPage(currentPage,pageSize);
        System.out.println("pageBean = " + pageBean);

        //3.将PageBean对象转为json响应给前端
        String json = JSON.toJSONString(pageBean);
        response.getWriter().print(json);
    }

    //条件查询
    public void selectPageOnCondition(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //0.处理请求响应中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        //1.接收请求参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        //如果前端没有传递当前页码或每页显示条数 则使用设置的默认值
        int currentPage = currentPageStr==null||currentPageStr.length()==0?1:Integer.parseInt(currentPageStr);
        int pageSize = pageSizeStr==null||pageSizeStr.length()==0?5:Integer.parseInt(pageSizeStr);

        //封装查询条件到Brand对象中
        Brand brand = JSON.parseObject(request.getInputStream(), Brand.class);

        //2.调用Service处理 得到PageBean对象
        BrandService brandService = new BrandService();
        PageBean<Brand> pageBean = brandService.selectPageOnCondition(currentPage,pageSize,brand);
        System.out.println("pageBean = " + pageBean);

        //3.将PageBean对象转为json响应给前端
        String json = JSON.toJSONString(pageBean);
        response.getWriter().print(json);

    }
}