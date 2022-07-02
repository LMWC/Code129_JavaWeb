package com.itheima.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.bean.User;
import org.junit.Test;

/*
    Jackson
        java对象转json：
            1.准备一个java对象
            2.创建ObjectMapper对象
            3.调用WriteValueAsString(Object obj)方法
       json转java对象：
            1.准备一个json字符串
            2.创建ObjectMapper对象
            3.调用readValue(String jsonStr,Class clazz)方法

   注意：
    1.使用json工具将java对象转json字符串时，java对象的属性一定要私有化，提供公有的set|get方法
    2.如果没有提供  使用fastjson转换会得到空对象     使用jackson转换会直接报错
 */
public class JacksonTest {

    //{"id":1,"username":"zs","password":"123"}
    //1.将java对象转为json字符串
    @Test
    public void fun01() throws JsonProcessingException {
        //1.准备一个java对象
        User user = new User(1, "zs", "123");

        //2.创建ObjectMapper对象
        ObjectMapper om = new ObjectMapper();

        //3.调用ObjectMapper对象的writeValueAsString方法
        String jsonStr = om.writeValueAsString(user);

        System.out.println("jsonStr = " + jsonStr);
    }

    //2.将json字符串转java对象
    @Test
    public void fun02() throws JsonProcessingException {
        //1.准备一个json字符串
        String jsonStr = "{\"id\":1,\"username\":\"zs\",\"password\":\"123\"}";

        //2.创建ObjectMapper对象
        ObjectMapper om = new ObjectMapper();

        //3.调用readValue方法
        User user = om.readValue(jsonStr, User.class);

        System.out.println("user = " + user);
    }

}
