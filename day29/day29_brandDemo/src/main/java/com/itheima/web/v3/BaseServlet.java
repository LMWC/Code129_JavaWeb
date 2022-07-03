package com.itheima.web.v3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/*
    注意：
        1.BaseServlet只是作为一个父类 抽取子类中的doGet和doPost方法，让子类继承共用，所以不需要设置请求访问地址
        2.Servlet请求访问地址还是设置在子类上
        3.this在继承环境下，写在父类中，哪个子类调用 this表示的就是哪个子类对象  如果this不是在继承环境下，一般都表示当前类对象
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.截取请求地址
        String uri = request.getRequestURI(); //eg：查询所有：/brand/selectAll --截取得到-->selectAll
        int index = uri.lastIndexOf("/"); // /最后一次出现的位置
        String methodName = uri.substring(index + 1);
        System.out.println("methodName = " + methodName);

        //2.根据截取的请求地址调用相应的方法

        try {
            //2.1.获取当前类的字节码对象
            Class c = this.getClass();
            //2.2.获取Method对象
            Method method = c.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //2.3.使用Method对象调用invoke方法  执行当前方法
            method.invoke(this,request,response);
        } catch (NoSuchMethodException e) {
            System.err.println("找不到请求对应的方法！！！");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}