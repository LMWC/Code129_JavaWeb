
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--使用EL获取域对象中存储的简单类型数据--%>

    <%--2.从域对象中取出数据--%>
    <h3>page-传统方式：<%=pageContext.getAttribute("id") %></h3>
    <h3>EL方式：${pageScope.id}</h3>
    <hr>

    <h3>request-传统方式：<%=request.getAttribute("username") %></h3>
    <h3>EL方式：${requestScope.username}</h3>
    <hr>

    <h3>session-传统方式：<%=session.getAttribute("password") %></h3>
    <h3>EL方式：${sessionScope.password}</h3>
    <hr>

    <h3>application-传统方式：<%=application.getAttribute("address") %></h3>
    <h3>EL方式：${applicationScope.address}</h3>
    <hr>


</body>
</html>
