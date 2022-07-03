package com.itheima.test;

import com.itheima.bean.User;
import com.itheima.dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    @Test
    public void testSelectAll() throws IOException {
        //1.加载MyBatis核心配置文件  myabtis-config.xml：这个配置文件名称可以随便起 但是要放在src/main/resources目录下
        InputStream is = Resources.getResourceAsStream("myabtis-config.xml");

        //创建者模式 ：传递is资源 由工厂创建者创建出工厂对象
        //2.通过SqlSessionFactoryBuilder类加载MyBatis配置文件得到SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        //整体创建对象流程：SqlSession工厂建造者--build()-->SqlSession工厂--openSession()-->SqlSession对象
        //SqlSessionFactory：相当于JDBC的连接池对象  SqlSession：相当于JDBC的连接对象
        //工厂模式：由工厂创建对应的对象
        //3.由SqlSessionFactory对象得到SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //JDK动态代理
        //4.通过SqlSession对象得到Dao接口的代理对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        //5.使用dao接口代理对象调用方法 完成操作
        List<User> list = userDao.selectAll();

        for (User user : list) {
            System.out.println("user = " + user);
        }

        //6.关闭SqlSession对象
        sqlSession.close();

    }
}
