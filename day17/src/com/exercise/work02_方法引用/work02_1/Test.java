package com.exercise.work02_方法引用.work02_1;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List names = new ArrayList();
        names.add("大明");
        names.add("二明");
        names.add("小明");

        //得到集合的stream并循环引用输出语句
        names.stream().forEach(System.out::println);
    }
}
