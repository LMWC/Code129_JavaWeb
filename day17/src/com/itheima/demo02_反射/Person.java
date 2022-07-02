package com.itheima.demo02_反射;

public class Person {
    public String name;
    public int age;

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
}
