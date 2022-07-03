package com.itheima.test;

import com.itheima.bean.Brand;
import com.itheima.bean.QueryVo;
import com.itheima.dao.BrandDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisTest {

    //提前声明两个全局变量 在方法中使用
    SqlSession sqlSession = null;
    BrandDao brandDao = null;

    //在执行每个带有@Test注解方法之前执行
    @Before
    public void before() throws IOException {
        //1.加载mybatis-config.xml配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        //2.获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        //3.获取SqlSession对象
        sqlSession = sqlSessionFactory.openSession();

        //4.获取BrandDao接口代理对象
        brandDao = sqlSession.getMapper(BrandDao.class);
    }

    //在执行每个带有@Test注解方法之后执行
    @After
    public void after(){
        //6.关闭sqlSession对象
        sqlSession.close();
    }

    @Test
    public void testSelectByCondition(){
        //5.调用方法
        Brand brand = new Brand();
        brand.setStatus(1);
        brand.setBrandName("百度");
        brand.setCompanyName("百度");
        List<Brand> list = brandDao.selectByCondition(brand);

        for (Brand brand1 : list) {
            System.out.println("brand1 = " + brand1);
        }
    }


}
