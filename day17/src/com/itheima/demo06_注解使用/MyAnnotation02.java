package com.itheima.demo06_注解使用;

/**
 * 有属性的注解
 */
public @interface MyAnnotation02 {
    int num();
    String name();
    String[] names();
}
