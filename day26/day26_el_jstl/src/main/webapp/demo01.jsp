<%--
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
    <%--使用EL获取域对象中存储的简单类型数据--%>
    <%--
        EL表达式获取简单数据类型：${key}
            注意：
                1.使用EL表达式简写方式获取域对象中的数据，会根据域对象的范围从小到大进行查找【page-->request-->session-->application】，
                        找到了就返回停止，不再继续向下查找；找不到返回空字符串""  而前面使用getAttribute(String name)找不到返回null
    --%>

    <%--1.向域对象中存储数据--%>
    <%
        /*
            pageContext、request、session、application：JSP内置对象  生来就有的  你创建一个jsp文件，这些对象就同时帮你创建好了，在jsp中就可以直接使用
                pageContext：page作用域对象  范围：当前页面
                request：request作用域对象    范围：一次请求
                session：session作用域对象    范围：一次会话
                application：ServletContext作用域对象 范围：整个应用
         */
        pageContext.setAttribute("id",101);
        request.setAttribute("username","zhangsan");
        session.setAttribute("password","123456");
        application.setAttribute("address","深圳");

        //request域对象中存储一个password
        request.setAttribute("password","我是密码");

    %>

    <%--2.从域对象中取出数据--%>
    <h3>page-传统方式：<%=pageContext.getAttribute("id") %></h3>
    <h3>EL方式：${pageScope.id}</h3>
    <h3>简化方式：${id}</h3>
    <hr>

    <h3>request-传统方式：<%=request.getAttribute("username") %></h3>
    <h3>EL方式：${requestScope.username}</h3>
    <h3>简化方式：${username}</h3>
    <hr>

    <h3>session-传统方式：<%=session.getAttribute("password") %></h3>
    <h3>EL方式：${sessionScope.password}</h3>
    <h3>简化方式：${password}</h3>
    <hr>

    <h3>application-传统方式：<%=application.getAttribute("address") %></h3>
    <h3>EL方式：${applicationScope.address}</h3>
    <h3>简化方式：${address}</h3>
    <hr>

    <%--没有在任何域对象中存储名称为email的数据--%>
    <h3>${email}</h3>

</body>
</html>
