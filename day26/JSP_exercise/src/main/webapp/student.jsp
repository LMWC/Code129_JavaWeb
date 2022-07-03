<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<%--<input type="button" value="新增" onclick="addBrand()"><br>
<hr>--%>
<table border="1" cellspacing="0" cellpadding="0" width="100%" align="center">
    <tr>
        <th>序号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>性别</th>
    </tr>
    <%--获取request域对象中存储的品牌列表数据  使用JSTL的forEach标签遍历展示--%>
    <%--每遍历一次  就输出一条品牌信息记录到页面--%>
    <c:forEach items="${list}" var="student">
        <tr align="center">
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.age}</td>
            <td>${student.gender}</td>
            <%--<td><a href="selectById?id=${brand.id}">修改</a> <a onclick="deleteById(${brand.id})">删除</a></td>--%>
        </tr>
    </c:forEach>
</table>

<%--<script>
    function addBrand() {
        //跳转到addBrand.jsp页面
        location.href = "addBrand.jsp";
    }

    //根据id删除品牌
    function deleteById(id){
        var flag = confirm("确定删除吗？");
        if(flag){
            console.log("严删除的品牌记录id：" + id);
            location.href = "delete?id="+id;
        }
    }
</script>--%>
</body>
</html>