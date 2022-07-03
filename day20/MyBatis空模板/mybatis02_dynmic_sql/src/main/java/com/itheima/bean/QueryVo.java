package com.itheima.bean;

import java.io.Serializable;

/**
 * 由于QueryVo类中的brand属性是一个对象  所以我们就称QueryVo类是一个包装类  本质上还是一个java类
 */
public class QueryVo implements Serializable {
    private Brand brand;
    private String orderField;

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    @Override
    public String toString() {
        return "QueryVo{" +
                "brand=" + brand +
                ", orderField='" + orderField + '\'' +
                '}';
    }
}
