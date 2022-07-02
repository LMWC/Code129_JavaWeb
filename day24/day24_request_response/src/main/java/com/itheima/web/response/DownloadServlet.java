package com.itheima.web.response;

import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
            手动编码下载实现思路：
                 //1.解决请求响应中文乱码   
                 //2.获取请求参数【要下载的文件名称】   
                 //3.获取要下载文件的字节输入流对象   
                 //4.使用response获取字节输出流对象  
                 //5.获取用户要下载的文件的MIME类型 
                 //6.设置响应头：写之前 告诉浏览器进行文件下载【告诉浏览器要下载的文件类型】 不要直接打开   
                 //7.将用户要下载的文件使用字节输出流对象写给客户端浏览器
         */

        //1.解决请求响应中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");  //针对响应体【服务器响应给页面的数据】生效

        //2.获取请求参数【要下载的文件名称】   
        String filename = request.getParameter("filename");
        System.out.println("原文件名："+filename);

        //3.获取要下载文件的字节输入流对象   
        InputStream is = this.getServletContext().getResourceAsStream("download/" + filename);

        //4.使用response获取字节输出流对象  
        ServletOutputStream os = response.getOutputStream();

        //5.获取用户要下载的文件的MIME类型 
        String mimeType = this.getServletContext().getMimeType(filename);

        //由于中文名称的文件下载之后 会出现文件名乱码 不能正常显示  而文件名是在响应头中设置的 ，所以 为了解决中文名称文件名乱码问题 需要对下载文件的中文文件名进行使用URL编码
        //火狐浏览器 低版本使用base64编码 高版本使用的是UTF-8  其他的浏览器都是使用UTF-8编码
        //所以 这里 我们就需要针对不同的浏览器做兼容性处理

        String userAgent = request.getHeader("user-agent");

        if(userAgent.contains("Firefox")){
            //如果是火狐浏览器  文件名称采用base64编码
            filename = base64EncodeFileName(filename);
        }else{
            //其他浏览器  文件名称采用UTF-8编码
            filename = URLEncoder.encode(filename, "UTF-8");
        }

        System.out.println("编码后新文件名："+filename);


        //6.设置响应头：写之前 告诉浏览器进行文件下载【告诉浏览器要下载的文件类型】 不要直接打开   
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition","attachment;filename="+filename);

        //7.将用户要下载的文件使用字节输出流对象写给客户端浏览器
        byte[] b = new byte[1024];
        int len = -1;
        while((len=is.read(b))!=-1){
            os.write(b,0,len);
        }

        os.close();
        is.close();
    }

    //封装好的Base64编码方法
    public static String base64EncodeFileName(String fileName) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        try {
            return "=?UTF-8?B?"
                    + new String(base64Encoder.encode(fileName
                    .getBytes("UTF-8"))) + "?=";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}