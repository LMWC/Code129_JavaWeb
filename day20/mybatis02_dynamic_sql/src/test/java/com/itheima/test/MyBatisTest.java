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

    SqlSession sqlSession =null;
    BrandDao brandDao = null;

    //@Before所在的方法会在每个单元测试方法执行之前执行一次
    @Before
    public void before() throws IOException {
        //1.加载配置文件
        InputStream is = Resources.getResourceAsStream("myabtis-config.xml");

        //2.获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        //3.获取SqlSession对象
        sqlSession = sqlSessionFactory.openSession();

        //4.获取dao接口代理对象
        brandDao = sqlSession.getMapper(BrandDao.class);
    }

    //@After注解所在方法 会在执行每个单元测试方法后执行一次
    @After
    public void after(){
        //6.关闭SqlSession对象
        sqlSession.close();
    }

    //测试查询全部品牌
    @Test
    public void testSelectByCondition01() throws IOException {
        //5.调用方法
        //brand对象用于封装查询条件参数
        Brand brand = new Brand();
        brand.setStatus(1);
        //brand.setBrandName("百度");

        List<Brand> list = brandDao.selectByCondition03(brand);

        for (Brand brand1 : list) {
            System.out.println("brand1 = " + brand1);
        }
    }

    //测试查询全部品牌
    @Test
    public void testUpdate01(){
        Brand brand = new Brand(7, "腾讯22", "腾讯科技22", null, null, null);

        int rows = brandDao.update03(brand);

        sqlSession.commit();
    }

    //批量删除
    @Test
    public void testDeleteByIds(){
        int[] ids = {5,6};
        int rows = brandDao.deleteByIds(ids);

        sqlSession.commit();
    }


    //测试增加品牌
    @Test
    public void testAdd(){
        Brand brand = new Brand(null, "黑马", "传智播客", 1, "666", 1);

        int rows = brandDao.add(brand);

        sqlSession.commit();
    }

    //测试修改品牌
    @Test
    public void testUpdate(){
        Brand brand = new Brand(9, "黑马程序员", "传智播客科技有限公司", 1, "6的一批", 1);

        int rows = brandDao.update(brand);

        sqlSession.commit();
    }

    //测试根据id删除品牌
    @Test
    public void testDeleteById(){

        int rows = brandDao.delete(9);

        sqlSession.commit();
    }

    //测试根据id查询品牌
    @Test
    public void testSelectById(){
        Brand brand = brandDao.selectById(9);

        System.out.println("brand = " + brand);
    }

    //测试查询所有品牌
    @Test
    public void testSelectAll(){
        List<Brand> list = brandDao.selectAll();
        for (Brand brand : list) {
            System.out.println("brand = " + brand);
        }
    }
}


