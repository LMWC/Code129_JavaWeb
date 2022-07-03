<%@ page import="com.itheima.bean.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="com.itheima.dao.ProductDao" %>
<%@ page import="com.itheima.service.ProductService" %>
<%--
  Created by IntelliJ IDEA.
  User: lmw23
  Date: 2022/5/20
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>goods</title>
</head>
<body>
<%
    //1.获取请求参数【没有】
    //2.调用业务处理
    ProductService studentService = new ProductService();
    List<Product> list = studentService.selectAll();
    System.out.println("list = " + list);

    //3.将查询返回的list集合数据存入request域对象 转发到brand.jsp页面展示
    request.setAttribute("list",list);
%>
<table border="1" cellspacing="0" cellpadding="0" width="100%" align="center">
    <tr>
        <th colspan="2" align="center">商品列表</th>
        <th><a href="./cart">购物车</a></th>
    </tr>
    <tr>
        <th>名称</th>
        <th>价格</th>
        <th>操作</th>
    </tr>
    <%--获取request域对象中存储的品牌列表数据  使用JSTL的forEach标签遍历展示--%>
    <%--每遍历一次  就输出一条品牌信息记录到页面--%>
    <c:forEach items="${list}" var="product">
        <tr align="center">
            <td>${product.NAME}</td>
            <td>${product.price}</td>
            <td><a href="insert?id=${product.id}">添加</a></td>
                <%--<td><a href="selectById?id=${brand.id}">修改</a> <a onclick="deleteById(${brand.id})">删除</a></td>--%>
        </tr>
    </c:forEach>
</table>

</body>
</html>
