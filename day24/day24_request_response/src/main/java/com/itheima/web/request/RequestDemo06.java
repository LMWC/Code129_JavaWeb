package com.itheima.web.request;

import com.itheima.bean.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * 使用BeanUtils封装请求参数到javabean对象中
 *  使用步骤：
 *      1.项目中引入BeanUtils的jar包
 *      2.使用BeanUtils的populate方法将所有请求参数封装到java对象中  populate(bean,map)
 *          参数1：java对象
 *          参数2：封装了所有请求参数的map集合
 *          2.1：创建一个java对象 使用无参构造方法
 *          2.2：获取所有的请求参数封装到map集合中
 *          2.3：调用populate方法将所有请求参数封装到java对象中
 *  注意：map集合中数据的key的名称要和java对象的属性名称一致  如果不一致 则无法将map集合中的数据封装到java对象中
 */
@WebServlet("/requestDemo06")
public class RequestDemo06 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RequestDemo06...");

        //设置请求体字符编码 解决post方式提交参数中文乱码
        request.setCharacterEncoding("UTF-8");

        //2.1：创建一个java对象 使用无参构造方法
        User user = new User();

        //2.2：获取所有的请求参数封装到map集合中
        Map<String, String[]> parameterMap = request.getParameterMap();

        try {
            //2.3：调用populate方法将所有请求参数封装到java对象中
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("user = " + user);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}