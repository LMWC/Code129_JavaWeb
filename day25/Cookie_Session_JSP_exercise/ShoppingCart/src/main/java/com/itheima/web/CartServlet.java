package com.itheima.web;

import com.itheima.bean.Product;
import com.itheima.bean.ProductCount;
import com.itheima.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            ProductService studentService = new ProductService();
            List<ProductCount> list = new ArrayList<>();

            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("count")){
                    String a=cookie.getValue();
                    String[] b=new String[6];
                    b=a.split("-");
                    int[] c=new int[6];
                    for(int i=0;i<6;i++){
                        c[i]=Integer.parseInt(b[i]);
                        if(c[i]!=0){
                            Product product = studentService.selectById(i+1);
                            ProductCount productCount = new ProductCount();
                            productCount.setId(product.getId());
                            productCount.setNAME(product.getNAME());
                            productCount.setPrice(product.getPrice());
                            productCount.setCount(c[i]);
                            list.add(productCount);
                        }
                    }
                }
            }
            System.out.println("list = " + list);

            //3.将查询返回的list集合数据存入request域对象 转发到brand.jsp页面展示
            request.setAttribute("list",list);
            request.getRequestDispatcher("cart.jsp").forward(request,response);



    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}