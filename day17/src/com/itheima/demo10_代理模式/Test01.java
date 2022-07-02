package com.itheima.demo10_代理模式;

public class Test01 {
    public static void main(String[] args) {
        //1.创建金莲对象
        JinLian jl = new JinLian();

        //2.创建王婆对象 把金莲送进去
        WangPo wangPo = new WangPo(jl);

        //3.调用王婆的happy方法  【让金莲更好的happy】
        wangPo.happy();
    }
}
