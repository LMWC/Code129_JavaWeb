package com.itheima.web;

import com.itheima.bean.User;
import com.itheima.dao.UserDao;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        InputStream is = null;
        SqlSession sqlSession = null;

        try {
            System.out.println("===用户注册===");
            //1.请求响应中文乱码处理
            //解决请求响应中文乱码
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            //2.获取请求参数 【用户注册信息封装到User对象中】
            Map<String, String[]> parameterMap = request.getParameterMap();
            User user = new User();
            BeanUtils.populate(user,parameterMap);

            //3.调用dao接口中的方法操作数据库
            //3.1：加载mybatis核心配置文件
            is = Resources.getResourceAsStream("mybatis-config.xml");
            //3.2：获取SqlSessionFactory对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            //3.3：获取SqlSession对象
            sqlSession = sqlSessionFactory.openSession();
            //3.4：获取UserDao接口的代理对象
            UserDao userDao = sqlSession.getMapper(UserDao.class);

            //4：先根据用户名查询数据库是否存在对应的用户user对象
            User user1 = userDao.selectByUsername(user.getUsername()); //通过user1判断用户名是否可用

            if(user1==null){
                //4.1：user1==null：用户名可用  进行用户注册【插入用户记录到数据库表中】
                userDao.add(user);
                //增删改操作 要手动提交事务
                sqlSession.commit();

                response.getWriter().print("注册成功");
            }else{
                //4.2：user1!=null：用户名已存在  提示 "用户名已存在"
                response.getWriter().print("用户名已存在");
            }

        } catch (Exception e) {
            //发生异常 事务回滚
            sqlSession.rollback();
            e.printStackTrace();
        }finally {
            //关闭对象 释放资源
            is.close();
            sqlSession.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}