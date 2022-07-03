package com.itheima.bean;

import java.io.Serializable;

public class Company implements Serializable {
    private String id;
    private String name;//公司名称
    private String address;//地址
    private String phone; //联系电话
    private String industry; //公司行业

    public Company() {
    }

    public Company(String id, String name, String address, String phone, String industry) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.industry = industry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", industry='" + industry + '\'' +
                '}';
    }
}
