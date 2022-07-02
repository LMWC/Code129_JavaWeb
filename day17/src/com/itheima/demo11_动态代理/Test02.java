package com.itheima.demo11_动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test02 {
    public static void main(String[] args) {
        /*
            动态代理使用步骤：
                1.提供一个父接口       FindHappy
                2.提供一个被代理对象    jl
                3.使用Proxy.newProxyInstance()动态生成一个对象的代理对象
                4.在InvocationHandler的invoke方法中 对要进行增强的方法进行增强，无需增强的方法调用被代理对象的原有方法
            注意：
                1.使用JDK生成的代理对象要用父接口类型接收
                2.使用了代理，调用方法时通过代理对象调用方法
         */
        //1.准备被代理对象 jl
        JinLian jl = new JinLian();

        //2.生成代理对象
        FindHappy proxy = (FindHappy) Proxy.newProxyInstance(jl.getClass().getClassLoader(), jl.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object res = null;
                //对要进行增强的方法进行增强，无需增强的方法调用被代理对象的原有方法
                if (method.getName().equals("happy")) {
                    System.out.println("开好房间");
                    System.out.println("约好人");
                    res = method.invoke(jl);              //使用反射调用被代理对象的方法
                    System.out.println("打扫房间");
                } else {
                    res = method.invoke(jl);              //使用反射调用被代理对象的方法
                }
                return res;
            }
        });

        //3.使用代理对象调用方法
        proxy.happy();
    }
}
