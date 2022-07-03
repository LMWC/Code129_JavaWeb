package com.itheima.bean;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String NAME;
    private double price;

    public Product() {
    }

    public Product(Integer id, String NAME, double price) {
        this.id = id;
        this.NAME = NAME;
        this.price = price;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", NAME='" + NAME + '\'' +
                ", price=" + price +
                '}';
    }
}
