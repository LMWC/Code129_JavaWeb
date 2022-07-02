package com.itheima.demo05_注解定义;

import java.util.List;

/**
 * 注解：
 *  概念：表示一个功能性标记，一旦被编译工厂 程序通过反射识别到对应的注解，就会执行对应的功能。
 *      本质是一个接口，注解中只有属性
 *  格式：
 *      public @interface 注解名称 {
 *          属性类型  属性名();
 *          ...
 *          属性类型 属性名() default 默认值;
 *      }
 *  属性类型：
 *      1.基本类型
 *      2.String类型
 *      3.枚举类型
 *      4.注解类型
 *      5.Class类型
 *      6.一维数组类型
 *   注意：
 *      1.注解是一个功能性标记
 *      2.注解的属性类型只有6种 不能瞎定义
 *      3.一个注解可以有属性也可以没有属性
 *      4.注解一般使用在类、方法、属性、方法参数上。
 */
public @interface MyAnnotation {
    int num();
    String name();
    Sex sex();
    MyAnnotation02 MY_ANNOTATION_02();
    Class c();
    String[] arr();
}
