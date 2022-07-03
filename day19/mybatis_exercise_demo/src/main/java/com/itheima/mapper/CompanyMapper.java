package com.itheima.mapper;

import com.itheima.bean.Company;

import java.util.List;

public interface CompanyMapper {
    Company findById(String id);
    List<Company> findAll();
    List<Company> findByCondition(Company company);
}
