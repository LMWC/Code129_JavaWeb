package com.itheima.dao;

import com.itheima.bean.Product;
import com.itheima.bean.ProductCount;

import java.util.List;

public interface ProductDao {
    List<Product> selectAll();

    Product selectById(int id);

}
