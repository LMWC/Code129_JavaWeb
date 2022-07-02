package com.itheima.web.demo02_architecture;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  1.当用户在浏览器输入地址 http://localhost:8080/day23/demo04 就会创建ServletDemo04的对象并调用service方法处理请求
 *  2.ServletDemo04中没有重写service方法 就会去调用父类中service方法 -->service(ServletRequest req, ServletResponse res)
 *  3.在HttpServlet的service(ServletRequest req, ServletResponse res)中 强转了req和resp两个对象 调用了service(HttpServletRequest req, HttpServletResponse resp)
 *  4.在HttpServlet的service(HttpServletRequest req, HttpServletResponse resp)中 先获取请求方式，再根据请求方式分别调用doXxx()方法
 *      get-->doGet(request,response)       post-->doPost(request,response)
 *  5.在ServletDemo04中重写了doGet方法 和 doPost方法
 *      因此当用户发起get请求，就会调用ServletDemo04中重写的doGet方法
 *           用户发起post请求，就会调用ServletDemo04中重写的doPost方法
 *
 *
 *  实现Servlet接口：比较麻烦 需要重写Servlet接口中的所有抽象方法
 *  继承GenericServlet抽象类：好处是GenericServlet对Servlet接口中的一些方法提供了默认实现，我们自定义Servlet时只需要重写service方法即可，但是缺少对HTTP协议操作的支持
 *  继承HttpServlet抽象类：HttpServlet抽象类继承了GenericServlet抽象类，在其中加入了对于HTTP协议操作的功能 功能更加强大
 *      实际开发中，自定义Servlet一般就直接继承HttpServlet，重写doGet和doPost方法 记得在doGet中要调用doPost方法
 *
 */
@WebServlet("/demo04")
public class ServletDemo04 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("处理get方式请求");
        //无论用户发起的是get方式请求 还是post方式请求 都进入到doPost方法中进行请求处理
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("处理post方式请求");

        //只需要在这里写一套代码就可以处理get方式和post方式请求了
    }
}
