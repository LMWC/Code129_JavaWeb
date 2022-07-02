package com.itheima.demo12_动态代理练习;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Test01 {
    public static void main(String[] args) {
        //需求：要求使用动态代理去除一个集合中的所有重复元素
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("bac");
        list.add("abc");
        list.add("cba");
        list.add("abc");

        /*
        //删除abc之前
        System.out.println("删除前list = " + list);

        //list对象中原有的remove方法
        list.remove("abc");

        //删除abc之后
        System.out.println("删除后list = " + list);
        */

        //思考 如何删除list集合中重复的元素？
        /*
            解决方案
              1.遍历 删除
              2.将list-->hashset-->list
              3.Stream流                     【面试题推荐你使用】
              4.装饰者模式 对remove方法进行增强
              5.动态代理   对remove方法进行增强
         */

        /*
            实现步骤：
                1.有一个公共父接口  List接口
                2.有一个被代理对象  list对象
                3.使用Proxy.newProxyInstance()生成list对象的代理对象
                4.在InvocationHandler的invoke方法中对remove方法进行增强
         */
        //1.生成代理对象
        List<String> proxyList = (List<String>) Proxy.newProxyInstance(list.getClass().getClassLoader(), list.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = null;
                //对remove方法进行增强  其他方法不需要增强 就依然调用被代理对象原有的方法实现
                if(method.getName().equals("remove")){
                    result = false;
                    Iterator<String> it = list.iterator();
                    while (it.hasNext()){
                        String element = it.next();
                        //args[0]：获取到具体传入进来的参数
                        if(element.equals(args[0])){
                            it.remove();
                            result = true;
                        }
                    }
                }else{
                    method.invoke(list,args);
                }
                return result;
            }
        });

        //2.使用代理对象调用方法完成功能操作
        proxyList.remove("abc");

        System.out.println("list = " + list);


    }
}
