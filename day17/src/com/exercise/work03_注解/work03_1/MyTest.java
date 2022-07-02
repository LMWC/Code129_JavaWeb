package com.exercise.work03_注解.work03_1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 注解的存活周期到任何阶段
@Target({ElementType.METHOD}) // 只能注解方法了
public @interface MyTest {
}
