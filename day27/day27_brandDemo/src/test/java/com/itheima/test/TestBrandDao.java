package com.itheima.test;

import com.itheima.bean.Brand;
import com.itheima.dao.BrandDao;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestBrandDao {

    //测试查询所有品牌列表信息
    @Test
    public void testSelectAll() throws IOException {
        /*InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(is).openSession();*/


        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandDao brandDao = sqlSession.getMapper(BrandDao.class);
        List<Brand> brands = brandDao.selectAll();
        System.out.println("brands = " + brands);


    }

}
