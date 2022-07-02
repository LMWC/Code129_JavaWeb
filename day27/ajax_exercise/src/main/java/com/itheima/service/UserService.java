package com.itheima.service;

import com.itheima.bean.Brand;
import com.itheima.bean.User;
import com.itheima.dao.UserDao;
import com.itheima.utils.SqlSessionFactoryUtils;
import com.mysql.jdbc.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserService {

    /**
     * 根据用户名查询用户  判断用户名是否可用
     * @param username 用户名
     * @return true:表示用户名存在 不可用   false：表示用户名不存在 可以使用
     */
    public boolean selectByName(String username) {

        //1.处理业务：判断用户名是否为null或""   如果是，则代表用户名不可用 返回true
        if(StringUtils.isNullOrEmpty(username)){
            return true;
        }

        //2.调用dao
        //2.1：获取SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();

        //2.2：获取dao接口代理对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        //2.3：调用方法
        User user = userDao.selectByName(username);

        //2.4：关闭SqlSession对象
        SqlSessionFactoryUtils.closeAndCommit(sqlSession);


        //3.判断user对象是否为null  返回true|false
        if(user==null){
            return false;
        }else{
            return true;
        }

    }

    public int add(User user) {
        //1.调用dao
        //1.1：获取SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();

        //1.2：获取dao接口代理对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        //1.3：调用方法
        int rows = userDao.add(user);

        //1.4：关闭SqlSession对象
        SqlSessionFactoryUtils.closeAndCommit(sqlSession);

        return rows;
    }

    public List<Brand> selectAll() {
        //1.调用dao
        //1.1：获取SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();

        //1.2：获取dao接口代理对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        //1.3：调用方法
        List<Brand> list = userDao.selectAll();

        //1.4：关闭SqlSession对象
        SqlSessionFactoryUtils.closeAndCommit(sqlSession);

        return list;
    }

    public User login(String username,String password){
        //1.调用dao
        //1.1：获取SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();

        //1.2：获取dao接口代理对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        //4.调用方法
        User user = userDao.select(username, password);
        //5.关闭对象 释放资源
        sqlSession.close();

        //6.返回处理结果
        return user;
    }
}
