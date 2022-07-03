package com.itheima.dao;

import com.itheima.bean.Brand;

import java.util.List;

public interface BrandDao {
    /**
     * 查询所有品牌信息
     * @return
     */
    List<Brand> selectAll();

    /**
     * 查看品牌详情
     * @param id 品牌id
     * @return 品牌对象
     */
    Brand selectById(int id);

    /**
     * 添加品牌
     * @param brand 封装要添加的品牌信息
     * @return 受影响的行数
     */
    int add(Brand brand);

    
    /**
     * 修改品牌的全部信息
     * @param brand 要修改的品牌信息
     * @return 受影响的行数
     */
    int update(Brand brand);

    /**
     * 根据id删除指定的品牌信息记录
     * @param id 品牌的id
     * @return 受影响的行数
     */
    int deleteById(int id);

}
