package com.itheima.service;

import com.itheima.bean.Brand;
import com.itheima.bean.PageBean;
import com.itheima.bean.PageParam;
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

    public int update(Brand brand) {
        SqlSession sqlSession = null;
        int rows = 0;
        try {
            //调用dao
            //1.1:获取SqlSession对象
            sqlSession = SqlSessionFactoryUtils.getSqlSession();
            //1.2：获取BrandDao接口的代理对象
            BrandDao brandDao = sqlSession.getMapper(BrandDao.class);
            //1.3：调用方法
            rows = brandDao.update(brand);
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
        SqlSession sqlSession = null;
        int rows = 0;
        try {
            //调用dao
            //1.1:获取SqlSession对象
            sqlSession = SqlSessionFactoryUtils.getSqlSession();
            //1.2：获取BrandDao接口的代理对象
            BrandDao brandDao = sqlSession.getMapper(BrandDao.class);
            //1.3：调用方法
            rows = brandDao.delete(id);
            //1.4：关闭SqlSession对象
            SqlSessionFactoryUtils.closeAndCommit(sqlSession);
        } catch (Exception e) {
            //执行出现异常 事务回滚
            SqlSessionFactoryUtils.closeAndRollback(sqlSession);

            e.printStackTrace();
        }

        return rows;
    }

    public int deleteByIds(int[] ids) {
        SqlSession sqlSession = null;
        int rows = 0;
        try {
            //调用dao
            //1.1:获取SqlSession对象
            sqlSession = SqlSessionFactoryUtils.getSqlSession();
            //1.2：获取BrandDao接口的代理对象
            BrandDao brandDao = sqlSession.getMapper(BrandDao.class);
            //1.3：调用方法
            rows = brandDao.deleteByIds(ids);
            //1.4：关闭SqlSession对象
            SqlSessionFactoryUtils.closeAndCommit(sqlSession);
        } catch (Exception e) {
            //执行出现异常 事务回滚
            SqlSessionFactoryUtils.closeAndRollback(sqlSession);

            e.printStackTrace();
        }

        return rows;
    }

    public PageBean selectByPage(int currentPage, int pageSize) {
        //1.调用dao查询当前页数据list和总条数total
        //1.1:获取SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();
        //1.2：获取BrandDao接口的代理对象
        BrandDao brandDao = sqlSession.getMapper(BrandDao.class);
        //1.3：调用方法
        //查询当前页数据
        List<Brand> list = brandDao.selectByPageList((currentPage-1)*pageSize,pageSize);
        //查询总条数
        int total = brandDao.selectByPageTotal();

        //1.4：关闭SqlSession对象
        SqlSessionFactoryUtils.closeAndCommit(sqlSession);

        //2.将当前页数据list和总条数total封装到PageBean对象中并返回
        return new PageBean(list,total);
    }

    public PageBean selectByPageOnCondition(PageParam pageParam) {
        //1.调用dao查询当前页数据list和总条数total
        //1.1:获取SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession();
        //1.2：获取BrandDao接口的代理对象
        BrandDao brandDao = sqlSession.getMapper(BrandDao.class);
        //1.3：调用方法
        //查询当前页数据
        List<Brand> list = brandDao.selectByPageOnConditionList(pageParam);
        //查询总条数
        int total = brandDao.selectByPageOnConditionTotal(pageParam);

        //1.4：关闭SqlSession对象
        SqlSessionFactoryUtils.closeAndCommit(sqlSession);

        //2.将当前页数据list和总条数total封装到PageBean对象中并返回
        return new PageBean(list,total);
    }
}
