package com.itheima;

import org.junit.Test;

public class Test01 {

    /*
        Maven常用命令：用来管理Maven项目构建
            compile ：编译,对src/main/java目录的下的代码进行编译
            clean：清理,清除编译产生的target文件夹内容             【作用：清理缓存  有时代码发生修改 但是没有及时编译 导致运行结果还是之前的】
            test：测试,执行src/test/java/下所有junit的测试
            package：打包,javase项目打包成jar包【方便给别人使用】,javaweb项目打包成war包
            install：安装 ，把本地项目的jar包安装到本地仓库  【方便在开发其他项目时通过坐标引入使用】

            注意：
                1.使用compile编译时只编译src/main下面的资源 不会编译src/test目录下的资源
                2.src/test目录下的资源 当执行test命令时会被编译
                    所以：有时我们直接执行某个单元测试方法会出现报错 no tests whhere found 原因：就是单元测试类没有被编译 解决：执行一下test命令

            Maven生命周期：指的是Maven进行一次项目构建执行了哪些事件
                compile-->test-->package-->install
                注意：
                    1.同一生命周期内，执行后面的命令 前面的命令会自动执行
                    2.clean不是Maven生命周期中的命令
               问题：我想先清理当前项目target目录下的编译内容  然后在打包安装到本地仓库。
               解决：mvn clean install
     */

    @Test
    public void fun01(){
        System.out.println("fun01...");
    }

    @Test
    public void fun02(){
        System.out.println("fun02...");
    }

    @Test
    public void fun03(){
        System.out.println("fun03...");
    }
}
