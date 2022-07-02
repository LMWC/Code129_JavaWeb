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
    <%--
        EL执行运算：
            1.EL 表达式没有字符串的拼接。+号表示算术运算   如果+号两边有一方不能转换为数字则会报错
            2.empty：
                2.1：判断对象是否为null                 是返回true
                2.2：判断字符串是否为null或""           是返回true
                2.3：判断list集合是否为null或长度为0    是返回true
           3.扩展：not empty：判断不为null  不为null就返回true

   --%>

    <%--1.向域对象中存储数据--%>
    <%
        //1.1：创建对象
        User user1 = null;
        User user2 = new User();
        User user3 = new User(1,"zs","123");
        request.setAttribute("user1",user1);
        request.setAttribute("user2",user2);
        request.setAttribute("user3",user3);

        //1.2:创建字符串
        String str1 = null;
        String str2 = "";
        String str3 = "haha";
        request.setAttribute("str1",str1);
        request.setAttribute("str2",str2);
        request.setAttribute("str3",str3);

        //1.3:创建List集合对象
        List<String> list1 = null;
        List<String> list2 = new ArrayList<String>();
        List<String> list3 = new ArrayList<String>();
        list3.add("zhangsan");
        request.setAttribute("list1",list1);
        request.setAttribute("list2",list2);
        request.setAttribute("list3",list3);

    %>

    <%--2.从域对象中取出数据--%>
    <h3>${5+3}</h3>
    <h3>${5+"3"}</h3>
    <h3>${"5"+"3"}</h3>
    <%--错误示例--%>
    <%--<h3>${"5"+"3a"}</h3>--%>

    <h4>${empty user1}</h4>
    <h4>${empty user2}</h4>
    <h4>${empty user3}</h4>
    <hr>
    <h4>${empty str1}</h4>
    <h4>${empty str2}</h4>
    <h4>${empty str3}</h4>
    <hr>
    <h4>${empty list1}</h4>
    <h4>${empty list2}</h4>
    <h4>${empty list3}</h4>
    <hr>

    <h4>${not empty user3}</h4>
    <h4>${not empty list3}</h4>
</body>
</html>
