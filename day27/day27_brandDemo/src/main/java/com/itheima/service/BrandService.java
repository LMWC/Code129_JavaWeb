package com.itheima.service;

import com.itheima.bean.Brand;
import com.itheima.dao.BrandDao;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandService {

    /**
     * 查询所有品牌信息列表
     * @return
     */
    public List<Brand> selectAll(){
        //1.获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取dao接口代理对象
        BrandDao brandDao = sqlSession.getMapper(BrandDao.class);
        //4.调用方法
        List<Brand> list = brandDao.selectAll();
        //5.关闭对象 释放资源
        sqlSession.close();

        //6.返回处理结果
        return list;
    }

    /**
     * 新增品牌信息
     * @param brand
     * @return
     */
    public int add(Brand brand){
        //1.获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取dao接口代理对象
        BrandDao brandDao = sqlSession.getMapper(BrandDao.class);
        //4.调用方法
        int rows = brandDao.add(brand);
        //5.手动提交事务 关闭对象 释放资源
        sqlSession.commit();
        sqlSession.close();

        //6.返回处理结果
        return rows;
    }

    /**
     * 根据id查询单个品牌信息
     * @param id
     * @return
     */
    public Brand selectById(int id){
        //1.获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取dao接口代理对象
        BrandDao brandDao = sqlSession.getMapper(BrandDao.class);
        //4.调用方法
        Brand brand = brandDao.selectById(id);
        //5.关闭对象 释放资源
        sqlSession.close();

        //6.返回处理结果
        return brand;
    }

    /**
     * 更新品牌信息
     * @param brand
     * @return
     */
    public int update(Brand brand){
        //1.获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取dao接口代理对象
        BrandDao brandDao = sqlSession.getMapper(BrandDao.class);
        //4.调用方法
        int rows = brandDao.update(brand);
        //5.手动提交事务 关闭对象 释放资源
        sqlSession.commit();
        sqlSession.close();

        //6.返回处理结果
        return rows;
    }


    /**
     * 根据id删除指定品牌信息
     * @param id
     * @return
     */
    public int delete(int id){
        //1.获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取dao接口代理对象
        BrandDao brandDao = sqlSession.getMapper(BrandDao.class);
        //4.调用方法
        int rows = brandDao.delete(id);
        //5.手动提交事务 关闭对象 释放资源
        sqlSession.commit();
        sqlSession.close();

        //6.返回处理结果
        return rows;
    }

}
