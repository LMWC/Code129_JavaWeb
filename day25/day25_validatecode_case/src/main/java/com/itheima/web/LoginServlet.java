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
/*
LoginServlet：登录处理
实现思路： 
    1.请求响应中文乱码处理 
    2.获取请求参数【用户名、密码 将用户名密码封装到user对象中、验证码】 
    3.验证码比对【比对用户输入的验证码和系统生成的验证码是否一致】 
    4.1：一致：登录处理：根据用户名和密码查询数据库，返回user对象                   
        5.1：user==null：登录失败                   
        5.2：user!=null：登录成功
    4.2：不一致：响应 "验证码输入有误"
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        InputStream is = null;
        SqlSession sqlSession = null;

        try {
            //1.处理请求响应中文乱码
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            //2.获取请求参数 【将登录的用户名和密码封装到User对象中】
            Map<String, String[]> parameterMap = request.getParameterMap();
            User user = new User();
            BeanUtils.populate(user,parameterMap);

            /*=========================获取用户输入的验证码=========================*/
            String validateCode = request.getParameter("validateCode");

            /*=========================3.验证码比对【比对用户输入的验证码和系统生成的验证码是否一致】=========================*/
            //获取系统生成的验证码  从session域对象中获取
            String checkCode = request.getSession().getAttribute("checkCode").toString();
            if(validateCode!=null && validateCode.equalsIgnoreCase(checkCode)){
                /*=========================一致：登录处理：根据用户名和密码查询数据库，返回user对象 =========================*/

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
                    response.getWriter().print("登录成功");
                }
            }else{
                /*=========================4.2不一致：响应 "验证码输入有误"=========================*/
                response.getWriter().print("验证码输入有误！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("服务器异常！");
        }finally {
            if(is!=null){
                is.close();
            }
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}