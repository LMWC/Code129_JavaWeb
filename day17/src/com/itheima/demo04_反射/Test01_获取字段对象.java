package com.itheima.demo04_反射;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Field;

public class Test01_获取字段对象 {
    /*
        通过反射获取类中的字段属性：
            Field[] getFields()：获取当前类中所有public修饰的字段
            ** Field[] getDeclardFields()：获取当前类中所有的字段 【包含public、protected、默认、private】
            Field getField(String FieldName)：根据字段名称和获取当前类中public修饰的指定字段对象
            ** Field getDeclaredField(String FieldName)：根据字段名称获取当前类中指定字段对象 【包含public、protected、默认、private】
        使用步骤：
            1.获取类的字节码对象
            2.使用字节码对象就可以获取类中的字段  封装到Field对象中
        作用：
            使用Field对象为一个对象的属性进行赋值以及获取属性值
            set(Object o,Object value):为属性赋值
            get(Object o):获取属性值
     */
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        //0.使用反射 一定要得到类的字节码对象
        Class c = Class.forName("com.itheima.demo04_反射.Person");

        //1.获取当前类中所有public修饰的字段对象
        Field[] Fields = c.getFields();
        for (Field field : Fields) {
            System.out.println("Field = " + field);
        }

        //2.获取当前类中所有字段对象 【包含public、protected、默认、private】
        System.out.println("----------------------------------------------");
        Field[] declaredFields = c.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println("field = " + field);
        }

        //3.根据字段名称获取当前类中public修饰的指定字段对象
        System.out.println("----------------------------------------------");
        Field nameF = c.getField("name");
        System.out.println("nameF = " + nameF);

        //4.根据字段名称获取当前类中任意修饰符修饰的指定字段对象
        System.out.println("----------------------------------------------");
        Field ageF = c.getDeclaredField("age");
        System.out.println("ageF = " + ageF);

        //5.使用Field对象调用方法为属性赋值 或者 获取属性值
        System.out.println("----------------------------------------------");
        /*
            set(Object obj, Object value)：为对象属性赋值
                第一个参数：Object obj 属性所属的对象   原来：对象.属性=值
                第二个参数：Object value 属性值
         */
        //得到Person类对象
        Object o = c.getDeclaredConstructor().newInstance();

        nameF.set(o,"zhangsan");
        ageF.setAccessible(true); //跳过权限检查
        ageF.set(o,16);
        //System.out.println("o = " + o);

        System.out.println("nameF.get(o) = " + nameF.get(o));
        System.out.println("ageF.get(o) = " + ageF.get(o));


    }
}
