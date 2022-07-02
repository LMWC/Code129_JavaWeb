package com.itheima.demo09_模拟Test注解;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test01 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        /*
            需求：
                1.自定义一个注解@MyTest
                2.编写一个测试类TestDemo，在测试类中编写一些普通方法 方法上打上@MyTest注解
                3.在Test01测试类main方法中 通过反射技术一旦识别到TestDemo类中的方法上有@MyTest注解 就执行对应的方法
             实现步骤：
                1.自定义一个注解@MyTest(1.设置保留到运行阶段 2.打在方法上 3.无需属性)
                2.编写一个测试类TestDemo，在测试类中编写一些普通方法 方法上打上@MyTest注解
                3.使用反射解析注解
                    3.1：获取TestDemo类的字节码对象
                    3.2：通过字节码对象获取TestDemo类中的所有方法对象Method
                    3.3：判断Method对象上是否有@MyTest注解
                    3.4：如果有 就使用反射技术调用方法执行
         */
        //3.1：获取TestDemo类的字节码对象
        Class c = Class.forName("com.itheima.demo09_模拟Test注解.TestDemo");

        //3.2：通过字节码对象获取TestDemo类中的所有方法对象Method
        Method[] methods = c.getDeclaredMethods();

        //3.3：循环遍历所有方法，判断Method对象上是否有@MyTest注解
        for (Method method : methods) {
            if(method.isAnnotationPresent(MyTest.class)){
                //3.4：如果有 就使用反射技术调用方法执行
                //c.getDeclaredConstructor().newInstance() 通过无参构造方法使用反射创建对象
                method.invoke(c.getDeclaredConstructor().newInstance());
            }
        }
        System.out.println("程序执行结束");
    }
}
