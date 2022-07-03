package com.itheima.web;

import com.itheima.bean.Product;
import com.itheima.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决请求响应中文乱码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //1.获取请求参数  【要修改的品牌id】
        int id = Integer.parseInt(request.getParameter("id"));

        //2.调用业务处理 【根据id查询对应的品牌数据记录】
        ProductService productService = new ProductService();
        Product product = productService.selectById(id);
        System.out.println("product = " + product);
        //3.将品牌数据存入request域对象中  转发到updateBrand.jsp页面展示
        //request.setAttribute("brand",brand);
        //request.getRequestDispatcher("updateBrand.jsp").forward(request,response);
        Cookie[] cookies = request.getCookies();
        String count="count";
        for (Cookie cookie1 : cookies) {
            if(cookie1.getName().equals(count)){
                //return cookie.getValue();
                String a=cookie1.getValue();
                String[] b=new String[6];
                b=a.split("-");
                int[] c=new int[6];
                for(int i=0;i<6;i++){
                    c[i]=Integer.parseInt(b[i]);
                }
                if(product.getId()==1){
                    c[0]++;
                    String d= c[0]+"-"+c[1]+"-"+c[2]+"-"+c[3]+"-"+c[4]+"-"+c[5];
                    Cookie cookie = new Cookie("count", d);
                    cookie.setMaxAge(60*60*24*7);
                    response.addCookie(cookie);
                    response.getWriter().print("成功添加到购物车！");
                    response.setHeader("refresh","1;url=http://localhost/goods.jsp");
                    return;
                }else if(product.getId()==2){
                    c[1]++;
                    String d= c[0]+"-"+c[1]+"-"+c[2]+"-"+c[3]+"-"+c[4]+"-"+c[5];
                    Cookie cookie = new Cookie("count", d);
                    cookie.setMaxAge(60*60*24*7);
                    response.addCookie(cookie);
                    response.getWriter().print("成功添加到购物车！");
                    response.setHeader("refresh","1;url=http://localhost/goods.jsp");
                    return;
                }else if(product.getId()==3){
                    c[2]++;
                    String d= c[0]+"-"+c[1]+"-"+c[2]+"-"+c[3]+"-"+c[4]+"-"+c[5];
                    Cookie cookie = new Cookie("count", d);
                    cookie.setMaxAge(60*60*24*7);
                    response.addCookie(cookie);
                    response.getWriter().print("成功添加到购物车！");
                    response.setHeader("refresh","1;url=http://localhost/goods.jsp");
                    return;
                }else if(product.getId()==4){
                    c[3]++;
                    String d= c[0]+"-"+c[1]+"-"+c[2]+"-"+c[3]+"-"+c[4]+"-"+c[5];
                    Cookie cookie = new Cookie("count", d);
                    cookie.setMaxAge(60*60*24*7);
                    response.addCookie(cookie);
                    response.getWriter().print("成功添加到购物车！");
                    response.setHeader("refresh","1;url=http://localhost/goods.jsp");
                    return;
                }else if(product.getId()==5){
                    c[4]++;
                    String d= c[0]+"-"+c[1]+"-"+c[2]+"-"+c[3]+"-"+c[4]+"-"+c[5];
                    Cookie cookie = new Cookie("count", d);
                    cookie.setMaxAge(60*60*24*7);
                    response.addCookie(cookie);
                    response.getWriter().print("成功添加到购物车！");
                    response.setHeader("refresh","1;url=http://localhost/goods.jsp");
                    return;
                }else{
                    c[5]++;
                    String d= c[0]+"-"+c[1]+"-"+c[2]+"-"+c[3]+"-"+c[4]+"-"+c[5];
                    Cookie cookie = new Cookie("count", d);
                    cookie.setMaxAge(60*60*24*7);
                    response.addCookie(cookie);
                    response.getWriter().print("成功添加到购物车！");
                    response.setHeader("refresh","1;url=http://localhost/goods.jsp");
                    return;
                }
            }
        }
            //if (cookies==null || count==null || count.equals(""))

                if(product.getId()==1){
                    Cookie cookie = new Cookie("count", "1-0-0-0-0-0");
                    cookie.setMaxAge(60*60*24*7);
                    response.addCookie(cookie);
                    response.getWriter().print("成功添加到购物车！");
                    response.setHeader("refresh","1;url=http://localhost/goods.jsp");
                    return;
                }else if(product.getId()==2){
                    Cookie cookie = new Cookie("count", "0-1-0-0-0-0");
                    cookie.setMaxAge(60*60*24*7);
                    response.addCookie(cookie);
                    response.getWriter().print("成功添加到购物车！");
                    response.setHeader("refresh","1;url=http://localhost/goods.jsp");
                    return;
                }else if(product.getId()==3){
                    Cookie cookie = new Cookie("count", "0-0-1-0-0-0");
                    cookie.setMaxAge(60*60*24*7);
                    response.addCookie(cookie);
                    response.getWriter().print("成功添加到购物车！");
                    response.setHeader("refresh","1;url=http://localhost/goods.jsp");
                    return;
                }else if(product.getId()==4){
                    Cookie cookie = new Cookie("count", "0-0-0-1-0-0");
                    cookie.setMaxAge(60*60*24*7);
                    response.addCookie(cookie);
                    response.getWriter().print("成功添加到购物车！");
                    response.setHeader("refresh","1;url=http://localhost/goods.jsp");
                    return;
                }else if(product.getId()==5){
                    Cookie cookie = new Cookie("count", "0-0-0-0-1-0");
                    cookie.setMaxAge(60*60*24*7);
                    response.addCookie(cookie);
                    response.getWriter().print("成功添加到购物车！");
                    response.setHeader("refresh","1;url=http://localhost/goods.jsp");
                    return;
                }else{
                    Cookie cookie = new Cookie("count", "0-0-0-0-0-1");
                    cookie.setMaxAge(60*60*24*7);
                    response.addCookie(cookie);
                    response.getWriter().print("成功添加到购物车！");
                    response.setHeader("refresh","1;url=http://localhost/goods.jsp");
                    return;
                }


        //response.getWriter().print("添加失败！");
        //response.setHeader("refresh","1;url=http://localhost/goods.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}