package com.itheima.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类  封装前端所需分页数据
 */
public class PageBean implements Serializable {
    private List<Brand> list;
    private int total;

    public PageBean() {
    }

    public PageBean(List<Brand> list, int total) {
        this.list = list;
        this.total = total;
    }

    public List<Brand> getList() {
        return list;
    }

    public void setList(List<Brand> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "list=" + list +
                ", total=" + total +
                '}';
    }
}
