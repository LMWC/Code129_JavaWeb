package com.itheima.demo11_动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Test01 {
    public static void main(String[] args) {
        //使用动态代理得到金莲的代理对象
        //1.创建金莲对象
        JinLian jl = new JinLian();

        //2.获取动态代理对象  目的：为了增强原有对象的方法功能

        //参数1：被代理对象的类加载器
        ClassLoader classLoader = jl.getClass().getClassLoader();
        //参数2：被代理对象实现的接口
        Class<?>[] interfaces = jl.getClass().getInterfaces();
        //参数3：事件处理器接口实现类对象  当使用代理对象调用方法时 就会进入InvocationHandler对象的invoke方法执行
        //           InvocationHandler监听代理对象调用的方法，就可以根据方法名进行判断该方法是否需要功能增强
        InvocationHandler ih = new InvocationHandler() {
            /*
                参数1：Object proxy 代理对象 【慎用|不用】
                参数2：Method method 代理对象调用的方法对象  happy方法-->happy方法的Method对象   method.getName():获取方法名
                参数3：Object[] args：代理对象调用方法时传递的参数
                返回值：Object  当代理对象调用的方法没有返回值时也就是方法返回值为void 则返回null，如果调用的方法有返回值 就正常返回。
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object res = null;

                System.out.println("方法名："+method.getName());
                System.out.println("方法参数："+ Arrays.toString(args));

                //通过方法名称进行判断 对要增强的方法进行增强 对不需要进行增强的方法依然调用被代理对象的原有方法
                //要增强的方法
                if(method.getName().equals("happy")){
                    System.out.println("开好房间");
                    System.out.println("约好人");
                    res =method.invoke(jl);              //使用反射调用被代理对象的方法
                    System.out.println("打扫房间");
                }else{
                    //不需要增强的方法
                    res =method.invoke(jl);
                }
                return res;
            }
        };


        //动态代理对象应该使用父接口类型接收
        //第一个参数和第二个参数是为了在JVM中产生一个对象的代理对象，第三个参数ih 用于监听代理对象的行为 代理对象调用了哪些方法
        FindHappy findHappy = (FindHappy) Proxy.newProxyInstance(classLoader, interfaces, ih);

        //使用代理对象调用方法
        findHappy.happy();
    }
}
