package com.itheima.dao;

import com.itheima.bean.Brand;

import java.util.List;

public interface BrandDao {

    //查询所有品牌
    List<Brand> selectAll();

    //添加品牌
    int add(Brand brand);

    //根据id查询品牌
    Brand selectById(int id);

    //修改品牌
    int update(Brand brand);

    //根据id删除品牌
    int delete(int id);
}
