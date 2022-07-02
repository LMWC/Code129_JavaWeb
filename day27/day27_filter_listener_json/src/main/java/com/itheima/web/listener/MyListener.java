package com.itheima.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/*
    ServletContextListener监听器使用：
        1.创建一个类 实现ServletContextListener接口
        2.配置监听器类  【将监听对象和被监听的对象绑定在一起 一旦监听到了某种行为，就可以执行某种操作】
            注解：@WebListener
            xml方式：
                <!--配置监听器-->
                <listener>
                    <listener-class>com.itheima.web.listener.MyListener</listener-class>
                </listener>
    作用：监听ServletContext对象的创建和销毁
        监听ServletContext创建：表示服务器启动 可以提前加载资源 做初始化工作
        监听ServletContext销毁：表示服务器关闭 可以提前释放资源
 */
//@WebListener
public class MyListener implements ServletContextListener {

    //监听到ServletContext创建
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext对象创建了111...");

        ServletContext servletContext = sce.getServletContext();
        System.out.println("servletContext = " + servletContext);
    }

    //监听到ServletContext销毁
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext对象销毁了444...");

        ServletContext servletContext = sce.getServletContext();
        System.out.println("servletContext = " + servletContext);
    }
}
