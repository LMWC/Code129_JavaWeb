package com.itheima.service;

import com.itheima.bean.Brand;
import com.itheima.dao.BrandDao;
import com.itheima.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 业务处理类
 */
public class BrandService {

    public List<Brand> selectAll(){

        //1.调用dao接口方法 查询数据库
        //1.1：获取SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();
        //1.2：获取dao接口代理对象
        BrandDao brandDao = sqlSession.getMapper(BrandDao.class);
        //1.3：调用方法
        List<Brand> list = brandDao.selectAll();
        //1.4：关闭SqlSession对象
        SqlSessionFactoryUtils.closeAndCommit(sqlSession);

        //2.返回查询到的所有品牌信息列表list集合
        return list;
    }

    public int add(Brand brand) {
        //1.调用BrandDao 完成品牌添加
        //1.1：获取SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();
        //1.2：获取dao接口代理对象
        BrandDao brandDao = sqlSession.getMapper(BrandDao.class);
        //1.3：调用方法
        int rows = brandDao.add(brand);
        //1.4：关闭SqlSession对象
        if(rows>0){
            SqlSessionFactoryUtils.closeAndCommit(sqlSession);
        }else{
            SqlSessionFactoryUtils.closeAndRollback(sqlSession);
        }

        return rows;
    }

    public Brand selectById(int id) {
        //1.调用dao  根据id查询品牌返回
        //1.1：获取SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();
        //1.2：获取dao接口代理对象
        BrandDao brandDao = sqlSession.getMapper(BrandDao.class);
        //1.3：调用方法
        Brand brand = brandDao.selectById(id);
        //1.4：关闭SqlSession对象
        SqlSessionFactoryUtils.closeAndCommit(sqlSession);

        //2.返回查询到的所有品牌信息列表list集合
        return brand;
    }

    public int update(Brand brand) {

        //1.调用dao 完成品牌修改‘
        //1.1：获取SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();
        //1.2：获取dao接口代理对象
        BrandDao brandDao = sqlSession.getMapper(BrandDao.class);
        //1.3：调用方法
        int rows = brandDao.update(brand);
        //1.4：关闭SqlSession对象
        if(rows>0){
            SqlSessionFactoryUtils.closeAndCommit(sqlSession);
        }else{
            SqlSessionFactoryUtils.closeAndRollback(sqlSession);
        }

        return rows;
    }

    public int delete(int id) {
        //1.调用BrandDao 完成品牌添加
        //1.1：获取SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();
        //1.2：获取dao接口代理对象
        BrandDao brandDao = sqlSession.getMapper(BrandDao.class);
        //1.3：调用方法
        int rows = brandDao.delete(id);
        //1.4：关闭SqlSession对象
        if(rows>0){
            SqlSessionFactoryUtils.closeAndCommit(sqlSession);
        }else{
            SqlSessionFactoryUtils.closeAndRollback(sqlSession);
        }

        return rows;
    }
}
