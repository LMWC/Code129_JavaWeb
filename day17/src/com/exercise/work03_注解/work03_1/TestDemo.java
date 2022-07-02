package com.exercise.work03_注解.work03_1;

import java.lang.reflect.Method;

public class TestDemo {
    @MyTest
    public void run1(){
        System.out.println("===run1===");
    }

    public void run2(){
        System.out.println("===run2===");
    }

    public void run3(){
        System.out.println("===run3===");
    }

    @MyTest
    public void run4(){
        System.out.println("===run4===");
    }

    // 自己模拟那个启动按钮。只启动有注解的方法
    public static void main(String[] args) throws Exception{
        TestDemo t = new TestDemo();
        // 扫描所有的方法对象，看方法上是否有注解，有就触发它。
        Class clazz = TestDemo.class;
        // 获取全部的方法
        Method[] methods = clazz.getDeclaredMethods();
        // 遍历全部方法
        for (Method mt : methods){
            // 判断这个方法是否有MyTest注解，有就触发它执行
            if (mt.isAnnotationPresent(MyTest.class)){
                // 触发这个方法执行！
                mt.invoke(t);
            }
        }
    }
}
