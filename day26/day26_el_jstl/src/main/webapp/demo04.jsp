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
        EL使用注意事项：
            1.EL表达式主要获取的是域对象(page<request<session<application)中的数据
            2.EL表达式使用简易方式获取数据，从范围小到范围大的顺序进行查找，找到返回停止，找不到返回””
            3.EL获取的数据不存在返回空字符串””,不是null
            4.如果属性名中出现了“.”“+”“-”等特殊的符号的时候，需要使用${xxxScope['属性名’]}
            5.EL 会自动进行数据类型转换，可以直接使用
            6.EL 表达式没有空指针异常和索引越界异常。
            7.EL 表达式没有字符串的拼接。+号表示算术运算   如果+号两边有一方不能转换为数字则会报错

   --%>

    <%--1.向域对象中存储数据--%>
    <%
        /*属性名中包含特殊字符  需要使用[]  但是一般建议不要这样做 属于没事找事*/
        request.setAttribute("a.b.c","大傻子");

        User user = null;
        request.setAttribute("user",user);

        String[] names = {"zs","ls","ww"};
        request.setAttribute("names",names);

    %>

    <%--2.从域对象中取出数据--%>
    <h3>${requestScope['a.b.c']}</h3>
    <h3>${user.username}</h3>
    <h3>${names[9]}</h3>

    <h3>${5+3}</h3>
    <h3>${5+"3"}</h3>
    <h3>${"5"+"3"}</h3>
    <h3>${"5"+"3a"}</h3>
    <hr>
</body>
</html>
