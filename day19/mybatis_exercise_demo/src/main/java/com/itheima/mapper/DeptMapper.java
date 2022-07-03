package com.itheima.mapper;

import com.itheima.bean.Dept;

import java.util.List;

public interface DeptMapper {
    Dept findById(String id);
    List<Dept> findAll();
    List<Dept> findByCondition(Dept dept);
}
