package com.itheima.web.demo04_servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 *  ServletContext的其他功能
 */
@WebServlet("/demo13")
public class ServletDemo13 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ServletDemo13...");

        //2..从ServletContext域对象中取出数据
        ServletContext servletContext = this.getServletContext();

        servletContext.removeAttribute("name");

        //1.获取文件的MIME类型【用于下载】
        String file1 = "a.mp3";
        String file2 = "b.mp4";

        String mimeType1 = servletContext.getMimeType(file1);
        String mimeType2 = servletContext.getMimeType(file2);

        System.out.println("mimeType1 = " + mimeType1);
        System.out.println("mimeType2 = " + mimeType2);

        //2.获取全局初始化参数  2.1:在web.xml中配置全局初始化参数  2.2：在任意Servlet中使用ServletContext对象获取
        String name = servletContext.getInitParameter("name");
        System.out.println("name = " + name);

        //3.获取webapp目录下的文件路径
        String realPath = servletContext.getRealPath("a.txt");
        System.out.println("realPath = " + realPath);

        //4.获取webapp目录下的文件资源的输入流对象
        InputStream is = servletContext.getResourceAsStream("a.txt");
        int len=-1;
        byte[] b = new byte[1024];
        while((len=is.read(b))!=-1){
            System.out.println(new String(b,0,len));
        }

        is.close();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}