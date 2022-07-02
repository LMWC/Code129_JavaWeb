package com.itheima.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.itheima.bean.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
    FastJson
        1.java对象转json字符串
            1.1：准备java对象
            1.2：JSON.toJSONString(Object obj)
       2.json字符串转java对象
            1.1：准备json字符串
            1.2：JSON.parseObject(String jsonStr,Class clazz)

 */
public class FastJsonTest {

    //jsonStr = {"id":1,"password":"123","username":"zs"}
    //1.将java对象转为json字符串 [java对象转成了json对象]
    @Test
    public void fun01(){
        //1.准备一个java对象
        User user = new User(1, "zs", "123");

        //2.调用JSON的toJSONString方法
        String jsonStr = JSON.toJSONString(user);

        System.out.println("jsonStr = " + jsonStr);
    }

    //[{"id":1,"password":"123","username":"zs"},{"id":2,"password":"234","username":"李四"},{"id":3,"password":"456","username":"王五"}]
    //2.将List集合转为json字符串  [list集合转成json数组]
    @Test
    public void fun02(){
        //1.准备一个List集合
        List<User> list = new ArrayList<>();

        User user1 = new User(1, "zs", "123");
        User user2 = new User(2, "李四", "234");
        User user3 = new User(3, "王五", "456");

        list.add(user1);
        list.add(user2);
        list.add(user3);

        //2.调用JSON的toJSONString方法
        String jsonStr = JSON.toJSONString(list);

        System.out.println("jsonStr = " + jsonStr);
    }


    //{"msg":"查询成功！","flag":true,"data":[{"id":1,"password":"123","username":"zs"},{"id":2,"password":"234","username":"李四"},{"id":3,"password":"456","username":"王五"}]}
    //3.将Map集合转为json字符串   [map集合转成混合形式的json]
    @Test
    public void fun03(){
        //1.准备一个Map集合
        Map<String,Object> map =new HashMap<>();

        List<User> list = new ArrayList<>();

        User user1 = new User(1, "zs", "123");
        User user2 = new User(2, "李四", "234");
        User user3 = new User(3, "王五", "456");

        list.add(user1);
        list.add(user2);
        list.add(user3);

        map.put("flag",true);
        map.put("msg","查询成功！");
        map.put("data",list);

        //2.调用JSON的toJSONString方法
        String jsonStr = JSON.toJSONString(map);

        System.out.println("jsonStr = " + jsonStr);
    }


    //4.将json字符串转为java对象
    @Test
    public void fun04(){
        //1.准备一个json字符串
        String jsonStr = "{\"id\":1,\"password\":\"123\",\"username\":\"zs\"}";

        //2.调用JSON的parseObject方法
        User user = JSON.parseObject(jsonStr, User.class);

        System.out.println("user = " + user);
    }

    //5.1将json字符串转为List集合
    @Test
    public void fun05(){
        //1.准备一个json字符串
        String jsonStr = "[{\"id\":1,\"password\":\"123\",\"username\":\"zs\"},{\"id\":2,\"password\":\"234\",\"username\":\"李四\"},{\"id\":3,\"password\":\"456\",\"username\":\"王五\"}]";

        //2.调用JSON的parseObject方法
        List list = JSON.parseObject(jsonStr, List.class);

        System.out.println("list = " + list);
    }

    //5.2将json字符串转为List泛型集合
    @Test
    public void fun052(){
        //1.准备一个json字符串
        String jsonStr = "[{\"id\":1,\"password\":\"123\",\"username\":\"zs\"},{\"id\":2,\"password\":\"234\",\"username\":\"李四\"},{\"id\":3,\"password\":\"456\",\"username\":\"王五\"}]";

        //2.调用JSON的parseObject方法
        //当将json字符串转为复杂类型 需要使用TypeReference手动指定复杂类型进行转换
        TypeReference<List<User>> typeReference = new TypeReference<List<User>>(){};

        List list = JSON.parseObject(jsonStr, typeReference);

        System.out.println("list = " + list);
    }

    //6.将json字符串转为Map集合对象
    @Test
    public void fun06(){
        //1.准备一个json字符串
        String jsonStr = "{\"msg\":\"查询成功！\",\"flag\":true,\"data\":[{\"id\":1,\"password\":\"123\",\"username\":\"zs\"},{\"id\":2,\"password\":\"234\",\"username\":\"李四\"},{\"id\":3,\"password\":\"456\",\"username\":\"王五\"}]}";

        //2.调用JSON的parseObject方法
        Map map = JSON.parseObject(jsonStr, Map.class);

        System.out.println("map = " + map);
    }
}
