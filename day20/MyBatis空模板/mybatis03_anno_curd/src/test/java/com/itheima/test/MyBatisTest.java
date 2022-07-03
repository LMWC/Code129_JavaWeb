package com.itheima.test;

import com.itheima.bean.Brand;
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
import java.util.List;

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
    public void testSelectAll() throws IOException {
        //5.调用方法
        List<Brand> list = brandDao.selectAll();
        for (Brand brand : list) {
            System.out.println("brand = " + brand);
        }
    }

    @Test
    public void testSelectById() throws IOException {
        //5.调用方法
        Brand brand = brandDao.selectById(3);
        System.out.println("brand = " + brand);
    }

    @Test
    public void testAdd() throws IOException {
        //5.调用方法
        Brand brand = new Brand(null, "百度2", "北京百度2科技股份有限公司", 100, "百度一下 你就知道", 1);
        int rows = brandDao.add(brand);

        //注意：MyBatis采用JDBC事务管理  默认关闭了事务的自动提交  所以执行增删改操作需要手动提交事务
        sqlSession.commit();
    }


    @Test
    public void testUpdate() throws IOException {
        //5.调用方法
        //5.1：先根据id查询出品牌信息
        Brand brand = brandDao.selectById(10);

        //5.2：修改品牌信息
        brand.setBrandName("黑马");
        brand.setCompanyName("传智播客");
        brand.setDescription("黑马一下 你就知道");

        //5.3：将修改后的品牌信息保存到数据库
        int rows = brandDao.update(brand);

        //手动提交事务
        sqlSession.commit();
    }

    @Test
    public void testDeleteById() throws IOException {
        //5.调用方法
        int rows = brandDao.deleteById(9);
        //手动提交事务
        sqlSession.commit();
    }

}
