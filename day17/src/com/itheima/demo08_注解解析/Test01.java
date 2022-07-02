package com.itheima.demo08_注解解析;

import java.lang.annotation.Annotation;

public class Test01 {

    /*
        注解解析：
            isAnnotationPresent(MyAnnotation.class)：判断是否此元素上是否存在某个注解
            getAnnotation(MyAnnotation.class)：获取此元素上指定类型的注解
            annotation.name()：注解对象.属性名()  获取注解属性值

     */
    public static void main(String[] args) throws ClassNotFoundException {
        //需求：判断Student类上是否有MyAnnotation注解 如果有 请求获取该注解的name属性值

        //1.获取Student类的字节码对象
        Class c = Class.forName("com.itheima.demo08_注解解析.Student");

        //2.使用isAnnotationPresent(Class<? extends Annotation> annotationClass) 判断
        boolean flag = c.isAnnotationPresent(MyAnnotation.class);

        //3.有 使用getAnnotation(Class<T> annotationClass) 获取注解对象
        if(flag){
            System.out.println("存在");
            //需要强转
            MyAnnotation annotation = (MyAnnotation) c.getAnnotation(MyAnnotation.class);

            //4.获取注解的属性值
            String name = annotation.name();
            System.out.println("name = " + name);

        }else{
            System.out.println("不存在！");
        }
    }
}
