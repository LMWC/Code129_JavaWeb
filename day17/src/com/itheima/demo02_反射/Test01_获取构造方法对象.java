package com.itheima.demo02_反射;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class 
Test01_获取构造方法对象 {
    /*
        通过反射获取类中的构造方法对象：
            Constructor[] getConstructors()：获取类中所有public修饰的构造方法对象
            ** Constructor[] getDeclardConstructors()：获取类中所有构造方法对象 【包含public、protected、默认、private】
            Constructor getConstructor(Class... params)：根据参数类型获取类中public修饰的指定构造方法对象
            ** Constructor getDeclaredConstructor(Class... params)：根据参数类型获取类中指定构造方法对象 【包含public、protected、默认、private】
        使用步骤：
            1.获取类的字节码对象
            2.使用字节码对象就可以获取类中的构造方法  封装到Constructor对象中
        作用：
            使用Constructor对象创建对象 【反射方式创建对象  无需new】
            newInstance(Object... paras)：创建类的对象
     */
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //0.使用反射 一定要得到类的字节码对象
        Class c = Class.forName("com.itheima.demo02_反射.Person");

        //1.获取类中所有public修饰的构造方法
        Constructor[] constructors = c.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println("constructor = " + constructor);
        }

        //2.获取类中所有的构造方法
        System.out.println("--------------------------------------");
        Constructor[] declaredConstructors = c.getDeclaredConstructors();
        for (Constructor constructor : declaredConstructors) {
            System.out.println("constructor = " + constructor);
        }

        //3.获取指定的public 修饰的构造方法
        System.out.println("--------------------------------------");
        //第一个构造方法
        Constructor con01 = c.getConstructor();
        System.out.println("con01 = " + con01);
        //第二个构造方法
        Constructor con02 = c.getConstructor(String.class);
        System.out.println("con02 = " + con02);
        //第三个构造方法
        Constructor con03 = c.getConstructor(int.class);
        System.out.println("con03 = " + con03);

        //4.获取指定的构造方法[public protected 默认 private]
        System.out.println("--------------------------------------");
        //第四个构造方法
        Constructor con04 = c.getDeclaredConstructor(String.class, int.class);
        System.out.println("con04 = " + con04);

        //5.使用反射技术通过Constructor对象创建类的对象
        System.out.println("--------------------------------------");
        //第一个构造方法
        Person p1 = (Person) con01.newInstance();
        p1.show();
        //第二个构造方法
        Person p2 = (Person) con02.newInstance("marry");
        p2.show();
        //第三个构造方法
        Person p3 = (Person) con03.newInstance(18);
        p3.show();
        //第四个构造方法
        // 前面讲到：私有成员只能在本类中使用 不能在外部其他类中使用
        // 现在：使用反射就可以忽视这种要求  可以跳过权限检查 这样我们就可以在一个类中访问其他类中的私有成员
        con04.setAccessible(true); //跳过权限检查
        Person p4 = (Person) con04.newInstance("jack",20);
        p4.show();



    }
}
