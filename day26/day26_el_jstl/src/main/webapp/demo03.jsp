<%@ page import="com.itheima.bean.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: shuaige
  Date: 2022/5/20
  Time: 8:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--使用EL获取域对象中存储的数组、List集合、Map集合类型数据
        获取数组元素：${key[下标]}
        获取List集合元素：${key[下标]} 或 ${key.get(下标)}
        获取Map集合元素：${key["map的键"]} 或 ${key.get("map的键")} 或 ${key.map的键}

   --%>
    <%--
        特点：
            1.传统方式获取域对象中存储的java对象属性 需要进行类型转换，而EL表达式会自动进行数据类型转换
            2.EL表达式获取javabean属性 底层是基于属性的get方法获取到的  如果属性名称和get方法不匹配 或者没有提供get方法 则会找不到或者报错
    --%>

    <%--1.向域对象中存储数据--%>
    <%
        //1.1：创建一个数组
        String[] names = {"张三","李四","王五"};
        //1.2：将数组存入到request域对象中
        request.setAttribute("names",names);

        //1.3：创建一个List集合
        List<String> list = new ArrayList<String>();
        list.add("冬瓜");
        list.add("西瓜");
        list.add("傻瓜");
        //1.4：将List集合存入到request域对象中
        request.setAttribute("list",list);

        //1.5:创建一个Map集合
        Map<String,String> map = new HashMap<String,String>();
        map.put("CN","大中国");
        map.put("JP","小日本");
        map.put("USA","丑陋国");
        //1.6：将Map集合存入request域对象中
        request.setAttribute("map",map);


    %>

    <%--2.1从域对象中取出names数组中第二个元素--%>
    <h3>request-传统方式：<%=((String[])request.getAttribute("names"))[1] %></h3>
    <h3>EL简化方式：${names[1]}</h3>
    <hr>

    <%--2.2从域对象中取出List集合中第二个元素--%>
    <h3>EL简化方式：${list[1]}</h3>
    <h3>EL简化方式：${list.get(1)}</h3>
    <hr>

    <%--2.3从域对象中取出Map集合中键为CN的值--%>
    <h3>EL简化方式：${map["CN"]}</h3>
    <h3>EL简化方式：${map.get("CN")}</h3>
    <h3>EL简化方式：${map.CN}</h3>
    <hr>
</body>
</html>
