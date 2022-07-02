<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%--JSTL标签的使用
        使用步骤：
            1.项目pom.xml中添加jstl坐标【两个】
            2.在要使用jstl标签的jsp页面引入jstl标签库
                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            3.使用
       作用：遍历数据、判断数据
       c:choose标签：相当于java的多重if判断
            c:when：相当于多重if判断中的if和else if
            c:otherwise：相当于多重if判断中的else
    --%>

    <%--需求：如果年龄大于等于18岁 可以上大学了 大于等于13岁 可以上中学 大于等于7岁 可以上小学  否则回家玩泥巴--%>
    <%--1.在域对象中存储年龄数据age--%>
    <%
        request.setAttribute("age",6);
    %>

    <%--2.使用JSTL的choose标签进行多重if判断--%>
    <c:choose>
        <c:when test="${age>=18}">
            上大学
        </c:when>
        <c:when test="${age>=13}">
            上中学
        </c:when>
        <c:when test="${age>=7}">
            上小学
        </c:when>
        <c:otherwise>
            回家玩泥巴
        </c:otherwise>
    </c:choose>

</body>
</html>
