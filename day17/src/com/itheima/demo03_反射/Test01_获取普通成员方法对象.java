package com.itheima.demo03_反射;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class Test01_获取普通成员方法对象 {
    /*
        通过反射获取类中的成员方法对象：
            Method[] getMethods()：获取当前类及父类中所有public修饰的成员方法对象
            ** Method[] getDeclardMethods()：获取当前类中所有成员方法对象 【包含public、protected、默认、private】
            Method getMethod(String methodName，Class... params)：根据方法名称和参数类型获取当前类及父类中public修饰的指定成员方法对象
            ** Method getDeclaredMethod(String methodName,Class... params)：根据方法名称和参数类型获取当前类中指定成员方法对象 【包含public、protected、默认、private】
        使用步骤：
            1.获取类的字节码对象
            2.使用字节码对象就可以获取类中的成员方法  封装到Method对象中
        作用：
            使用Method对象调用方法执行
            inoke(Object o,Object... params)
     */
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //0.使用反射 一定要得到类的字节码对象
        Class c = Class.forName("com.itheima.demo03_反射.Person");

        //1.获取当前类及父类中所有public修饰的成员方法对象
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            System.out.println("method = " + method);
        }

        //2.获取当前类中所有成员方法对象 【包含public、protected、默认、private】
        System.out.println("----------------------------------------------");
        Method[] declaredMethods = c.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println("method = " + method);
        }

        //3.根据方法名称和参数类型获取当前类及父类中public修饰的指定成员方法对象
        System.out.println("----------------------------------------------");
        Method show = c.getMethod("show");
        System.out.println("show = " + show);

        Method show2 = c.getMethod("show2", int.class);
        System.out.println("show2 = " + show2);

        //4.根据方法名称和参数类型获取当前类中指定成员方法对象 【包含public、protected、默认、private】
        Method show3 = c.getDeclaredMethod("show3", String.class, int.class);
        System.out.println("show3 = " + show3);

        Method show4 = c.getDeclaredMethod("show4");
        System.out.println("show4 = " + show4);

        //5.使用Method对象调用方法执行
        /*
            public Object invoke(Object obj, Object... args): 使用Method对象调用invoke方法就可以执行对应的方法
                参数1：Object obj  调用这个方法要用到的对象 执行方法的所属对象  原来：person.show();
                参数2：Object... args 方法执行的实际参数
                返回值：Object 当方法无返回值为void时 返回null  如果方法执行后有返回值，这里就表示方法执行后的返回值
         */
        //5.1：调用show方法执行
        System.out.println("----------------------------------------------");
        //使用反射创建对象  c.getDeclaredConstructor()：得到一个类的无参构造方法
        Object o = c.getDeclaredConstructor().newInstance();

        Object showRes = show.invoke(o);
        System.out.println("showRes = " + showRes);

        //5.2:调用show2
        Object show2Res = show2.invoke(o, 100);
        System.out.println("show2Res = " + show2Res);

        //5.3:调用show3 private
        show3.setAccessible(true); //跳过权限检查
        Object show3Res = show3.invoke(o, "sz", 200);
        System.out.println("show3Res = " + show3Res);

        //5.4：调用show4 static
        Object show4Res = show4.invoke(o);
        System.out.println("show4Res = " + show4Res);
        //注意：由于在java中 静态方法建议使用类名调用  因为静态方法是属于类的 被各个对象共同拥有

        //静态方法调用执行 可以不传递对象 直接传一个null就可以了  此时表示这个show4方法就由类调用
        show4.invoke(null);


    }
}
