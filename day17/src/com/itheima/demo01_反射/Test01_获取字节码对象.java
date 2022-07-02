package com.itheima.demo01_反射;

public class Test01_获取字节码对象 {
    public static void main(String[] args) throws ClassNotFoundException {
        /*
            获取一个类的字节码对象有三种方式：
                方式一：对象.getClass();
                方式二：Class.forName("全限定类名");         【推荐】
                方式三：类名.class;
         */
        //方式一：对象.getClass();
        Person person = new Person();
        Class c1 = person.getClass();

        //方式二：Class.forName("全限定类名");
        Class c2 = Class.forName("com.itheima.demo01_反射.Person");

        //方式三：类名.class;
        Class c3 = Person.class;

        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);
        System.out.println("c3 = " + c3);
        System.out.println(c1 == c3);

        //功能：
        //1.获取当前字节码对象所属类的名称
        System.out.println(c1.getName());               //获取类全名
        System.out.println(c1.getSimpleName());         //获取类名简写


    }
}
