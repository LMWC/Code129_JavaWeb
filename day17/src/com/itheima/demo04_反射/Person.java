package com.itheima.demo04_反射;

/**
 * @author 帅哥
 * @version 1.0
 */
public class Person {
    public String name;
    private int age;

    public Person() {}

    public Person(String name) {
        this.name = name;
    }

    public Person(int age) {
        this.age = age;
    }

    private Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void show(){
        System.out.println("name="+name+" age="+age);
    }

    public void show1(String address){
        System.out.println("name="+name+" age="+age+" address"+address);
    }

    public String show2(int num){
        System.out.println("name="+name+" age="+age+" num" + num);
        return "show2";
    }

    private void show3(String address,int num){
        System.out.println("address="+address+" num="+num);
    }


    public static void show4(){
        System.out.println("静态方法show4");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
