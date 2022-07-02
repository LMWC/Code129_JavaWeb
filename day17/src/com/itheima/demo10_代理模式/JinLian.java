package com.itheima.demo10_代理模式;

/**
 * 被代理角色
 */
public class JinLian implements FindHappy{
    @Override
    public void happy() {
        System.out.println("金莲在happy...");
    }
}
