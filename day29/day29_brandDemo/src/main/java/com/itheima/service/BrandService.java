package com.itheima.service;

import com.itheima.bean.Brand;
import com.itheima.bean.PageBean;
import com.itheima.dao.BrandDao;
import com.itheima.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BrandService {
    public List<Brand> selectAll() {
        //调用dao
        //1.1:获取SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();
        //1.2：获取BrandDao接口的代理对象
        BrandDao brandDao = sqlSession.getMapper(BrandDao.class);
        //1.3：调用方法
        List<Brand> list = brandDao.selectAll();
        //1.4：关闭SqlSession对象
        SqlSessionFactoryUtils.closeAndCommit(sqlSession);

        //2.返回所有品牌列表数据集合
        return list;
    }

    public int add(Brand brand) {
        SqlSession sqlSession = null;
        int rows = 0;
        try {
            //调用dao
            //1.1:获取SqlSession对象
            sqlSession = SqlSessionFactoryUtils.getSqlSession();
            //1.2：获取BrandDao接口的代理对象
            BrandDao brandDao = sqlSession.getMapper(BrandDao.class);
            //1.3：调用方法
            rows = brandDao.add(brand);
            //1.4：关闭SqlSession对象
            SqlSessionFactoryUtils.closeAndCommit(sqlSession);
        } catch (Exception e) {
            //执行出现异常 事务回滚
            SqlSessionFactoryUtils.closeAndRollback(sqlSession);

            e.printStackTrace();
        }

        return rows;
    }

    public int delete(Integer id) {
        //调用dao
        //1.获取SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();
        //2.获取Dao接口代理对象
        BrandDao brandDao = sqlSession.getMapper(BrandDao.class);
        //3.调用方法
        int rows = brandDao.delete(id);
        //4.关闭SqlSession对象
        if(rows>0){
            SqlSessionFactoryUtils.closeAndCommit(sqlSession);
        }else{
            SqlSessionFactoryUtils.closeAndRollback(sqlSession);
        }
        return rows;
    }

    public int update(Brand brand) {
        //调用dao
        //1.获取SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();
        //2.获取Dao接口代理对象
        BrandDao brandDao = sqlSession.getMapper(BrandDao.class);
        //3.调用方法
        int rows = brandDao.update(brand);
        //4.关闭SqlSession对象
        if(rows>0){
            SqlSessionFactoryUtils.closeAndCommit(sqlSession);
        }else{
            SqlSessionFactoryUtils.closeAndRollback(sqlSession);
        }
        return rows;
    }

    public int deleteByIds(int[] ids) {
        //调用dao
        //1.获取SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();
        //2.获取Dao接口代理对象
        BrandDao brandDao = sqlSession.getMapper(BrandDao.class);
        //3.调用方法
        int rows = brandDao.deleteByIds(ids);
        //4.关闭SqlSession对象
        if(rows>0){
            SqlSessionFactoryUtils.closeAndCommit(sqlSession);
        }else{
            SqlSessionFactoryUtils.closeAndRollback(sqlSession);
        }
        return rows;
    }

    public PageBean<Brand> selectPage(int currentPage, int pageSize) {
        //获取SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();
        //获取Dao接口代理对象
        BrandDao brandDao = sqlSession.getMapper(BrandDao.class);

        //1.调用dao 查询当前页数据
        int offset = (currentPage - 1) * pageSize;  //当前页数据起始下标计算
        List<Brand> list = brandDao.selectByPage(offset, pageSize);

        //2.调用dao  查询总条数
        int total = brandDao.total();

        //3.封装数据到PageBean对象并返回
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setTotal(total);
        pageBean.setRows(list);
        return pageBean;
    }

    public PageBean<Brand> selectPageOnCondition(int currentPage, int pageSize,Brand brand) {
        //获取SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();
        //获取Dao接口代理对象
        BrandDao brandDao = sqlSession.getMapper(BrandDao.class);

        //1.调用dao 查询当前页数据
        int offset = (currentPage - 1) * pageSize;  //当前页数据起始下标计算
        List<Brand> list = brandDao.selectByPageOnCondition(offset, pageSize,brand);

        //2.调用dao  查询总条数
        int total = brandDao.totalOnCondition(brand);

        //3.封装数据到PageBean对象并返回
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setTotal(total);
        pageBean.setRows(list);
        return pageBean;
    }
}
