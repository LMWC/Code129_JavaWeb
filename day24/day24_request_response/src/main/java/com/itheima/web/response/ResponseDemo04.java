package com.itheima.web.response;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 操作响应体：写给浏览器的数据
 *      getOutputStream()：得到字节输出流对象 向浏览器写入数据  一般用于写入二进制数据【图片、音频、视频...】
 *      getWriter()：得到字符输出流对象   向浏览器写入数据  一般用于写入字符文本数据
 *
 *      注意：
 *          1.两个流在使用时是互斥的，不能同时使用  要使用字符流就不能再使用字节流
 *          2.但是一种类型的流可以使用多次
 *  响应中文乱码：
 *       //服务器告诉浏览器 我给你响应的数据是text|html类型，你要使用utf-8编码进行解码展示
 *       //response.setHeader("content-type","text/html;charset=UTF-8");
 *       //response.setContentType("text/html;charset=UTF-8");
 */
@WebServlet("/responseDemo04")
public class ResponseDemo04 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ResponseDemo04...");

        //解决请求响应中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //解决响应中文乱码
        //服务器告诉浏览器 我给你响应的数据是text|html类型，你要使用utf-8编码进行解码展示
        //response.setHeader("content-type","text/html;charset=UTF-8");
        //response.setContentType("text/html;charset=UTF-8");

        //方式一：使用字节输出流向浏览器写入文本数据 “Hello World”
        ServletOutputStream os = response.getOutputStream(); //数据写入目的地：用户浏览器
        //os.write("Hello World".getBytes());
        os.write("中国牛逼".getBytes());


        //方式二：使用字符输出流向浏览器写入文本数据  “Hello China”
        /*response.getWriter().print("Hello China ");

        response.getWriter().print("Hello SZ129 ");

        response.getWriter().print("中国威武");*/
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}