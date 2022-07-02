<%@ page import="com.itheima.bean.User" %><%--
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
    <%--使用EL获取域对象中存储的java对象属性数据  语法：${key.javabean属性}--%>
    <%--
        特点：
            1.传统方式获取域对象中存储的java对象属性 需要进行类型转换，而EL表达式会自动进行数据类型转换
            2.EL表达式获取javabean属性 底层是基于属性的get方法获取到的  如果属性名称和get方法不匹配 或者没有提供get方法 则会找不到或者报错
    --%>

    <%--1.向域对象中存储数据--%>
    <%
        //1.1：创建一个user对象
        User user = new User(7,"程涛","123abc");

        //1.2：将user对象存入到request域对象中
        request.setAttribute("user",user);
    %>

    <%--2.从域对象中取出user对象的username属性值--%>
    <h3>request-传统方式：<%=((User)request.getAttribute("user")).getUsername() %></h3>
    <h3>EL简化方式：${user.username}</h3>
    <hr>
</body>
</html>
