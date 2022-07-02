package com.itheima.web.cookie;

import com.itheima.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/*
Cookie进阶：
    1.Cookie 存活时间
        默认情况下，Cookie 存储在浏览器内存中，当浏览器关闭，内存释放，则Cookie被销毁
        setMaxAge(int seconds)：设置Cookie存活时间
            正数：将 Cookie写入浏览器所在电脑的硬盘，持久化存储。到时间自动删除
            负数：默认值，Cookie在当前浏览器内存中，当浏览器关闭，则 Cookie被销毁
            零：删除对应 Cookie
    2.Servlet可以一次写入多个Cookie到浏览器
    3.Cookie存储中文和特殊字符
        tomcat8.5+：Cookie可以存储中文，但是不能存储特殊字符(空格 + ....)
        tomcat7版本：Cookie不能存储中文及特殊字符
        因此：在使用Cookie存储中文和特殊字符时，建议对Cookie的值进行编码后再存储 ，取出时需要解码
            Cookie存值：编码 URLEncoder.encode(value,"UTF-8");
            Cookie取值：解码 URLDecoder.decode(value,"UTF-8");
    4.Cookie路径：默认是写入Cookie所在的当前Servlet的URI路径【不带协议、ip地址、端口号】
            eg:
                 address --> http://localhost:8080/day25/cookieDemo03  --> /day25
                 email --> http://localhost:8080/day25/cc/cookieDemo06  --> /day25/cc
           项目中的cookie路径一般不进行设置，如果要设置 一般设置为当前项目的虚拟路径 request.getContextPath()  eg: /day25
           设置cookie路径为当前项目虚拟路径原因：
                这样就可以保证该cookie在当前项目中的任何Servlet中都可以获取到使用
           当前访问的Servlet如果与一个Cookie路径相同或在其子目录下，则可以在这个Servlet中获取该CooKie的值
                eg：
                 http://localhost:8080/day25/cc/cookieDemo06  --> 获取Cookie名称address的值   -获取到了->
                    原因是因为名称为address的Cookie路径时/day25   而CookieDemo06的Servlet路径时/day25/cc

                http://localhost:8080/day25/cookieDemo03   --> 获取Cookie名称为email的值  -获取不到->
                    原因是因为名称为email的Cookie路径是/day25/cc   而CookieDemo03的Servlet路径时/day25

          总结：
                1.Cookie路径的作用 是为了保证Cookie在当前项目中可以共享使用
                2.Cookie的路径设置 一般不设置  如果设置 设置为当前项目的虚拟路径  eg：cookie.setPath(request.getContextPath());

 */
@WebServlet("/cookieDemo03")
public class CookieDemo03 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("cookieDemo03...");

        //获取名称为"email"的cookie的值
        Cookie[] cookies = request.getCookies();
        String cookieValue = CookieUtils.getCookieValue(cookies, "email");
        System.out.println("email = " + cookieValue);


        //写入cookie
        Cookie cookie = new Cookie("address", "shenzhen");
        Cookie cookie1 = new Cookie("sex","boy");

        Cookie cookie2 = new Cookie("country", URLEncoder.encode("中国","UTF-8"));

        //设置有效时间
        cookie.setMaxAge(60*2);
        response.addCookie(cookie);

        response.addCookie(cookie1);
        response.addCookie(cookie2);


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}