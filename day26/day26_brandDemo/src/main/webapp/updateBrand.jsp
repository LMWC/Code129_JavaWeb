<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xiaomi
  Date: 2021/11/9
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>修改品牌</title>
</head>
<body>
<h3>修改品牌</h3>
<form action="update" method="post">
    <%--使用隐藏域 隐藏要修改品牌的id  在页面上不显示  会将id数据提交到后台--%>
    <input type="hidden" name="id" value="${brand.id}">
    品牌名称：<input name="brandName" value="${brand.brandName}"><br>
    企业名称：<input name="companyName" value="${brand.companyName}"><br>
    排序：<input name="ordered" value="${brand.ordered}"><br>
    描述信息：<textarea rows="5" cols="20" name="description">${brand.description}</textarea><br>
    状态：
    <c:choose>
        <c:when test="${brand.status == 1}">
            <input type="radio" name="status" value="0">禁用
            <input type="radio" name="status" value="1" checked>启用<br>
        </c:when>
        <c:otherwise>
            <input type="radio" name="status" value="0" checked>禁用
            <input type="radio" name="status" value="1" >启用<br>
        </c:otherwise>
    </c:choose>
    <input type="submit" value="提交">
</form>
</body>
</html>