package com.itheima.demo06_注解使用;

public @interface MyAnnotation04 {
    String name();
    int age() default 18;  //age属性有默认值 默认值为18
}
