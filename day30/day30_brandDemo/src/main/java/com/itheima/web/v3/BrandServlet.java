package com.itheima.web.v3;

import com.alibaba.fastjson.JSON;
import com.itheima.bean.Brand;
import com.itheima.bean.PageBean;
import com.itheima.bean.PageParam;
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
        //0.处理请求响应中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        try {
            //1.获取请求参数【封装到Brand对象中】
            Brand brand = JSON.parseObject(request.getInputStream(), Brand.class);

            //2.调用业务处理
            BrandService brandService = new BrandService();
            int rows = brandService.update(brand);

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

    //删除品牌
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("===删除品牌===");
        try {
            //1.获取请求参数【要删除品牌的id】
            // 获取普通格式请求参数依然使用：request.getParameter("参数名称")
            // 获取json格式请求参数：JSON.parseObject(request.getInputStream,Class);
            Integer id = Integer.parseInt(request.getParameter("id"));


            //2.调用业务处理
            BrandService brandService = new BrandService();
            int rows = brandService.delete(id);

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

    //批量删除品牌
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            System.out.println("===批量删除品牌===");
            //1.获取请求参数【要删除的品牌id数组 使用JSON.parseObject(ids,int[].class)】
            int[] ids = JSON.parseObject(request.getInputStream(), int[].class);

            System.out.println("【批量删除】ids = " + Arrays.toString(ids));

            //2.调用业务处理
            BrandService brandService = new BrandService();
            int rows = brandService.deleteByIds(ids);

            //3.响应数据 true|false
            if(rows>0){
                response.getWriter().print(true);
            }else{
                response.getWriter().print(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.getWriter().print(false);
        }
    }

    //分页查询
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("===分页查询===");
        //0.处理请求响应中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        //1.获取请求参数【当前页码：currentPage  每页显示条数：pageSize】
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        //万一前端没有传递当前页码和每页显示条数  设置一个默认的当前页码和每页显示条数
        int currentPage = 1;
        if(currentPageStr!=null && !currentPageStr.equals("")){
            currentPage = Integer.parseInt(currentPageStr);
        }

        int pageSize = pageSizeStr==null||pageSizeStr.equals("")?5:Integer.parseInt(pageSizeStr);

        //2.调用业务处理 返回PageBean对象【封装当前页数据list和总条数total】
        BrandService brandService = new BrandService();
        PageBean pageBean = brandService.selectByPage(currentPage,pageSize);
        System.out.println("pageBean = " + pageBean);

        //3.响应json数据
        response.getWriter().print(JSON.toJSONString(pageBean));
    }

    //条件查询分页展示
    public void selectByPageOnCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("===条件查询分页展示===");
        //0.处理请求响应中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        //1.获取请求参数【当前页码：currentPage  每页显示条数：pageSize 查询条件 brand】
        PageParam pageParam = JSON.parseObject(request.getInputStream(), PageParam.class);
        System.out.println("【条件查询：】pageParam = " + pageParam);

        pageParam.setCurrentPage(pageParam.getCurrentPage()==null?1:pageParam.getCurrentPage());
        pageParam.setPageSize(pageParam.getPageSize()==null?5:pageParam.getPageSize());

        //2.调用业务处理 返回PageBean对象【封装当前页数据list和总条数total】
        BrandService brandService = new BrandService();
        PageBean pageBean = brandService.selectByPageOnCondition(pageParam);
        System.out.println("pageBean = " + pageBean);

        //3.响应json数据
        response.getWriter().print(JSON.toJSONString(pageBean));
    }

}