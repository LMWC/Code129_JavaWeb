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
    public void testSelectAll() throws IOException {
        //5.调用方法
        List<Brand> list = brandDao.selectAll();

        for (Brand brand : list) {
            System.out.println("brand = " + brand);
        }
    }

    //测试查询详情
    @Test
    public void testSelectById() throws IOException {
        //5.调用方法
        Brand brand = brandDao.selectById(1);
        System.out.println("brand = " + brand);
    }

    //测试增加品牌
    @Test
    public void testAdd() throws IOException {
        //5.调用方法
        Brand brand = new Brand(null, "百度4", "百度科技4", 100, "666", 1);

        int rows = brandDao.add02(brand);

        //由于mybatis使用JDBC事务管理 默认关闭了事务的自动提交，所以在执行增删改操作时 需要手动提交回滚事务
        //提交事务：sqlSession.commit();
        //回滚事务：sqlSession.rollback();
        if(rows>0){
            System.out.println("增加成功！");
            sqlSession.commit();
        }else{
            System.out.println("增加失败！");
            sqlSession.rollback();
        }

        //获取brand品牌对象的id
        System.out.println("id = " + brand.getId());

    }

    //测试修改品牌
    @Test
    public void testUpdate(){
        //调用方法
        Brand brand = new Brand(8, "百科", "百度科技有限公司", 100, "666", 1);

        int rows = brandDao.update(brand);

        //增删改操作之后  需要手动提交事务
        sqlSession.commit();
    }

    //测试根据id删除品牌
    @Test
    public void testDeleteById(){

        //调用方法
        int rows = brandDao.deleteById(8);

        //增删改操作之后  需要手动提交事务
        sqlSession.commit();
    }

    //测试根据品牌名称模糊查询品牌列表
    @Test
    public void testSelectByBrandName(){
        List<Brand> list = brandDao.selectByBrandName02("百度");
        for (Brand brand : list) {
            System.out.println("brand = " + brand);
        }
    }

    //测试根据条件查询品牌信息
    @Test
    public void testSelectByCondition(){

        //方式一和方式二测试
        //List<Brand> list = brandDao.selectByCondition02(1,"百度","百度");


        //方式三测试
        //brand1对象用于封装查询条件参数
        /*Brand brand1 = new Brand();
        brand1.setStatus(0);
        brand1.setCompanyName("三只松鼠");
        brand1.setBrandName("三只松鼠");

        List<Brand> list = brandDao.selectByCondition03(brand1);*/

        //方式四测试
        //准备一个Map集合 封装查询条件参数
        Map<String,Object> map = new HashMap<>();
        /*map.put("status",1);
        map.put("companyName","华为");
        map.put("brandName","华为");*/
        map.put("status",null);
        map.put("companyName",null);
        map.put("brandName",null);


        List<Brand> list = brandDao.selectByCondition04(map);

        for (Brand brand : list) {
            System.out.println("brand = " + brand);
        }
    }

    //测试根据品牌状态和品牌名称模糊查询 再根据品牌排序字段进行升序排序
    @Test
    public void testSelectByCondition05(){

        //brand1对象用于封装查询条件参数
        Brand brand1 = new Brand();
        brand1.setStatus(1);
        brand1.setBrandName("百度");

        //封装查询条件参数
        QueryVo queryVo = new QueryVo();
        queryVo.setBrand(brand1);
        queryVo.setOrder("ordered");

        List<Brand> list = brandDao.selectByCondition05(queryVo);

        for (Brand brand : list) {
            System.out.println("brand = " + brand);
        }
    }

    //统计品牌个数
    @Test
    public void testCount(){
        int count = brandDao.count();
        System.out.println("count = " + count);
    }
}
