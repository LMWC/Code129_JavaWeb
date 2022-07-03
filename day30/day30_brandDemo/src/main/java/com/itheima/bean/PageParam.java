package com.itheima.bean;

import java.io.Serializable;

/**
 * 条件查询分页展示 请求参数封装类
 */
public class PageParam implements Serializable {
    private Integer currentPage;
    private Integer pageSize;
    private Brand brand;
    //扩展属性  当前页数据起始下标
    private Integer offset;

    public Integer getOffset() {
        return (currentPage-1)*pageSize;
    }

    public PageParam() {
    }

    public PageParam(Integer currentPage, Integer pageSize, Brand brand) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.brand = brand;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", brand=" + brand +
                '}';
    }
}
