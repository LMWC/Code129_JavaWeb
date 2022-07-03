package com.itheima.bean;

import java.io.Serializable;

/**
 * 包含了对象属性的类 我们就称为对象包装类型 简而言之本质上还是一个java类
 * 类实现Serializable是为了可以将对象转为流写入到一个文件中 在很多框架中都会做这样的一件事  程序运行了作缓存处理 将对象保存到一个文件中
 */
public class QueryVo implements Serializable {
    private Brand brand;    //品牌信息 包含的就有品牌状态和品牌的名称
    private String order;   //排序字段

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
