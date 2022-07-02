package com.itheima.demo06_注解使用;

/**
 * 注解的使用：打在java程序代码上 一般打在类、属性、方法、方法参数上
 *  基本使用规则：
 *      1.如果一个注解没有属性  就直接使用 @注解名
 *      2.如果一个注解有属性   使用时就需要给注解的所有属性赋值 @注解名(属性名=属性值 ...)
 *      3.注解一般打在类、属性、方法、方法参数上
 *  使用注意事项：
 *      1.一旦注解有属性了,使用注解的时候,属性必须赋值
 *      2.若属性类型是一维数组的时候,当数组的值只有一个的时候可以省略{}
 *      3.如果注解中只有一个属性,并且属性名为value,那么使用注解给注解属性赋值的时候,注解属性名value可以省略
 *      4.注解属性可以有默认值  格式:属性类型 属性名() default 默认值; 有默认值的属性在注解使用时可以不用赋值
 */

@MyAnnotation01
//注意事项1：1.一旦注解有属性了,使用注解的时候,属性必须赋值
@MyAnnotation02(num = 100,name="zhangsan",names={"zs","ls"})
public class Test01 {

    @MyAnnotation01
    //注意事项2：若属性类型是一维数组的时候,当数组的值只有一个的时候可以省略{}
    @MyAnnotation02(num = 200,name = "lisi", names="ls")
    private String name;

    @MyAnnotation01
    //注意事项3：如果注解中只有一个属性,并且属性名为value,那么使用注解时给注解属性赋值的时候,注解属性名value可以省略
    //@MyAnnotation03(value="haha")
    @MyAnnotation03("haha")
    //注意事项4：注解属性可以有默认值  格式:属性类型 属性名() default 默认值; 有默认值的属性在注解使用时可以不用赋值
    @MyAnnotation04(name="ww")
    public void show(@MyAnnotation01 String address){

    }


}
