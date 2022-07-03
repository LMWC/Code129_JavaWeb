package com.itheima.bean;

import java.io.Serializable;

public class ProductCount implements Serializable {
    private int id;
    private String NAME;
    private double price;
    private int count;

    public ProductCount() {
    }

    public ProductCount(Integer id, String NAME, double price, int count) {
        this.id = id;
        this.NAME = NAME;
        this.price = price;
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ProductCount{" +
                "id=" + id +
                ", NAME='" + NAME + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
