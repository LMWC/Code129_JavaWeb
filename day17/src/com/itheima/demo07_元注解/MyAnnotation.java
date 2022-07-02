package com.itheima.demo07_元注解;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
  元注解：定义注解的注解，如果自定义的注解没有使用元注解修饰则可以使用在任务位置，默认只保留在源码阶段。
      @Target(ElementType.METHOD)：设置注解可以使用在什么位置
           ElementType[] value()：属性是ElementType枚举类型的一维数组
            TYPE：用在类或接口上
            FIELD：用在字段属性上
            METHOD：用在方法上
            PARAMETER：用在参数上
      @Retention(RetentionPolicy.SOURCE)：设置注解保留到哪个阶段
        RetentionPolicy.SOURCE：属性是RetentionPolicy枚举类型
            SOURCE：保留在源码阶段  【默认的  如果不设置】
            CLASS：保留在字节码阶段  【源码阶段有】
            RUNTIME：保留在运行阶段  【源码、字节码阶段依然存在】    【常用】

 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface MyAnnotation {
}
