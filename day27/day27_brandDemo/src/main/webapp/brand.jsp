<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>品牌列表</title>
</head>
<body>
    <h1>${user.username},欢迎您</h1>

    <input type="button" value="新增" onclick="addBrand()"><br>
    <hr>
    <table border="1" cellspacing="0" cellpadding="0" width="80%">
        <tr>
            <th>序号</th>
            <th>品牌名称</th>
            <th>企业名称</th>
            <th>排序</th>
            <th>品牌介绍</th>
            <th>状态</th>
            <th>操作</th>
        </tr>

        <c:forEach items="${list}" var="brand">
            <tr align="center">
                <td>${brand.id}</td>
                <td>${brand.brandName}</td>
                <td>${brand.companyName}</td>
                <td>${brand.ordered}</td>
                <td>${brand.description}</td>
                <c:choose>
                    <c:when test="${brand.status==0}"><td>禁用</td></c:when>
                    <c:when test="${brand.status==1}"><td>启用</td></c:when>
                </c:choose>
                <td><a href="/selectByIdServlet?id=${brand.id}">修改</a>
                    <%--<a href="#">删除</a>--%>
                    <a onclick="deleteById(${brand.id})">删除</a>
                </td>
            </tr>
        </c:forEach>

    </table>
    <script>
        function addBrand() {
            location.href = "/addBrand.jsp";
        }

        function deleteById(id) {
            var flag = confirm("确定要删除吗？");
            if(flag){
                location.href = "/deleteServlet?id="+id;
            }
        }
    </script>
</body>
</html>
