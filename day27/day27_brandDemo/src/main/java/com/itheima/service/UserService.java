package com.itheima.service;

import com.itheima.bean.User;
import com.itheima.dao.UserDao;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserService {

    /**
     * 用户注册
     * @param user
     * @return
     */
    public boolean register(User user){
        //1.获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取dao接口代理对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        //4.调用方法
        User u = userDao.selectByUsername(user.getUsername());
        if(u==null){
            int rows = userDao.add(user);
            //增删改 手动提交事务
            sqlSession.commit();
        }
        //5.关闭对象 释放资源
        sqlSession.close();

        //6.返回处理结果
        return u==null;
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public User login(String username,String password){
        //1.获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取dao接口代理对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        //4.调用方法
        User user = userDao.select(username, password);
        //5.关闭对象 释放资源
        sqlSession.close();

        //6.返回处理结果
        return user;
    }
}
