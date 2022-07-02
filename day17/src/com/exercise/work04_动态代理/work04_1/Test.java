package com.exercise.work04_动态代理.work04_1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        /*
            四  编程：
                    对Collection接口进行代理，以前的remove(Object obj)方法是删除集合中第一次出现的元素
                    (比如集合中有多个“abc”,调用remove(“abc”)后只会删除一个元素)。
                    代理后，要求在调用remove(Object obj)方法后，能够删除集合中所有匹配的元素。【动态代理】

               动态代理:
                    1.怎么使用动态代理生成一个代理对象
                        Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)生成一个代理对象
                            参数1:被代理类的类加载器
                            参数2:被代理类所有实现的接口的Class对象
                            参数3: 处理类,用来监听代理对象调用的方法,只要代理对象调用了方法,就会来到InvocationHandler中的invoke方法

                    2.怎么去增强被代理类的方法
                         使用InvocationHandler接口中的invoke方法
                         public Object invoke(Object proxy, Method method, Object[] args)  把要增强的代码放在invoke方法中就可以了
                            参数1: 产生的代理对象,一般不用
                            参数2: 表示的是代理对象调用的当前方法
                            参数3: 表示的是代理对象调用的当前方法,传入的实参
                            返回值类型: 表示的是代理对象调用的当前方法的返回值
         */
        ArrayList<String> list = new ArrayList<>();// 金莲
        list.add("abc");
        list.add("bac");
        list.add("abc");
        list.add("cba");
        list.add("abc");
        list.add("abc");

        System.out.println("删除前："+list);//[abc, bac, abc, cba, abc, abc]
        // list.remove("abc");// 金莲.happy()   王婆代理.happy()

        // 使用动态代理获取代理对象
        List<String> proxy = (List<String>) Proxy.newProxyInstance(list.getClass().getClassLoader(), list.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //执行一下代理对象调用的方法
                Object res = method.invoke(list,args);

                //remove方法增强的代码
                //如果代理对象调用的方法是删除方法，就删除集合中所有元素
                if (method.getName().equals("remove")){
                    Iterator<String> it = list.iterator();
                    while (it.hasNext()){
                        String e = it.next();
                        //判断集合中的元素是否是要删除的元素
                        if (e.equals(args[0])){
                            it.remove();
                        }
                    }
                }

                /* if (method.getName().equals("set")){
                    throw new Exception("不允许修改");
                }*/
                if (method.getName().equals("set")){
                    System.out.println("set 哈哈哈哈");//假设这么增强set方法
                }

                return res;
            }
        });

        // 使用代理对象删除集合中所有的abc元素
        // boolean res = proxy.remove("abc");
        //System.out.println(res);

        //proxy.set(1,"bac");

        // 如果代理对象调用的方法的返回值类型不一样,增强方法中的返回值怎么写?
        boolean res1 = proxy.remove("abc");

        String res2 = proxy.set(1,"abc");

        System.out.println(res1);//true
        System.out.println(res2);//cba

        System.out.println("删除后："+list);//删除后：[bac,bac]
    }
}
