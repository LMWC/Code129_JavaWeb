package com.itheima.demo05_druid;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertiesTest {
    public static void main(String[] args) throws Exception {
        //1.将文件转为流 从硬盘加载到内存中
        FileInputStream fis = new FileInputStream("day16\\src\\a.properties");

        //2.properties文件读取：直接将流加载到properties对象中就可以了
        Properties p = new Properties();
        p.load(fis);

        //3.获取properties文件中的数据 根据key获取value

        System.out.println(p.getProperty("name"));
        System.out.println(p.get("age"));


    }
}
