package com.itheima.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


/**
 * SqlSession工具类：
 *  1.获取SqlSession对象
 *  2.关闭SqlSession对象
 *  优化：
 *      不需要每次获取SqlSession对象的同时获取创建一个SqlSessionFactory对象
 *      使用静态代码块优化sqlSessionFactory代码
 */
public class SqlSessionFactoryUtils {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            //1.加载MyBatis核心配置文件
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

            //2.获取SqlSessionFactory对象
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        } catch (IOException e) {
            System.err.println("加载MyBatis配置文件失败！");
            e.printStackTrace();
        }
    }

    /**
     * 获取SqlSession对象
     * @return
     */
    public static SqlSession getSqlSession(){
        //3.获取SqlSession对象返回
        return sqlSessionFactory.openSession();
    }

    /**
     * 关闭SqlSession对象并提交事务
     * @param sqlSession
     */
    public static void closeAndCommit(SqlSession sqlSession){
        if(sqlSession!=null){
            sqlSession.commit();
            sqlSession.close();
        }
    }

    /**
     * 关闭SqlSession对象并回滚事务
     * @param sqlSession
     */
    public static void closeAndRollback(SqlSession sqlSession){
        if(sqlSession!=null){
            sqlSession.rollback();
            sqlSession.close();
        }
    }
}
