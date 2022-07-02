package com.itheima.demo11_动态代理;

/**
 * 被代理角色
 */
public class JinLian implements FindHappy {
    @Override
    public void happy() {
        System.out.println("金莲在happy...");
    }
}
