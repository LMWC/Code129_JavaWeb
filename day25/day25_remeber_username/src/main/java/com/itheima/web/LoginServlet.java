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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        InputStream is = null;
        SqlSession sqlSession = null;

        try {
            //1.处理请求响应中文乱码
            //解决请求响应中文乱码
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            //2.获取请求参数 【将登录的用户名和密码封装到User对象中】
            Map<String, String[]> parameterMap = request.getParameterMap();
            User user = new User();
            BeanUtils.populate(user,parameterMap);

            //3.根据用户名和密码去调用UserDao接口中的方法 查询数据库中是否存在对应的记录 返回User对象
            //3.1：加载MyBatis核心配置文件
            is = Resources.getResourceAsStream("mybatis-config.xml");
            //3.2：获取SqlSessionFactory对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            //3.3：获取SqlSession对象
            sqlSession = sqlSessionFactory.openSession();
            //3.4：获取UserDao接口代理对象
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            //3.5：调用方法 根据用户名和密码查询用户
            User loginUser = userDao.select(user);

            //4.判断loginUser对象是否为null
            if(loginUser==null){
                //4.1：loginUser==null：用户名密码不存在 登录失败
                response.getWriter().print("登录失败");
            }else{
                //4.2：loginUser!=null：用户名密码存在   登录成功
                /*==============1.登录成功，如果勾选了记住用户名复选框 则将用户名和密码存入cookie写给客户端浏览器==============*/
                //1.获取复选框的值 判断是否勾选
                String rem = request.getParameter("rem");
                if(rem!=null && rem.equals("on")){
                    //勾选了记住用户名复选框
                    //1.1：创建cookie存入用户名和密码
                    Cookie cookie1 = new Cookie("username", user.getUsername());
                    Cookie cookie2 = new Cookie("password", user.getPassword());

                    //1.2：设置cookie的有效时间
                    cookie1.setMaxAge(60*60*24*7);
                    cookie2.setMaxAge(60*60*24*7);

                    //1.3：将cookie写给客户端浏览器
                    response.addCookie(cookie1);
                    response.addCookie(cookie2);

                }


                response.getWriter().print("登录成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("服务器异常！");
        }finally {
            is.close();
            sqlSession.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}