package com.itheima.demo10_代理模式;

/**
 * 代理角色
 */
public class WangPo implements FindHappy{

    private JinLian jl;

    public WangPo(JinLian jl) {
        this.jl = jl;
    }

    @Override
    public void happy() {
        //开好房间
        System.out.println("开好房间");
        //约好人
        System.out.println("约好人");
        //叫来金莲happy
        jl.happy();
        //打扫房间
        System.out.println("打扫房间");
    }
}
