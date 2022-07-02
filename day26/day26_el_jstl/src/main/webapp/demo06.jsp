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
    --%>

    <%--需求：如果年龄大于等于7岁 可以上小学了  否则回家玩泥巴--%>

    <%
        request.setAttribute("age",7);
    %>
    <%--
        c:if标签：用于判断
            属性：
                test属性：必须的 存放判断条件  在test属性中写一个返回boolean类型的el表达式
                    当test属性值为true时，会执行if标签体中的代码，如果为false，则不执行
           了解的属性：
                var属性：声明一个变量用于 存储test属性值 方便后续使用
                scope属性：表示将test属性值存储在哪个域对象中 默认存储在page域对象中
    --%>
    <%--实际使用--%>
    <%--将test属性值存入session域对象中 等价于  session.setAttribute("flag",true);--%>
    <c:if test="${age>=7}" var="flag" scope="session">
        <h3>可以上小学了</h3>
    </c:if>
    <c:if test="${age<7}">
        <h3>回家玩泥巴</h3>
    </c:if>


    <h3>${sessionScope.flag}</h3>



    <%--<%
        int age = 8;
        if(age>7){
    %>
            <h3>可以上小学了</h3>
    <%
        }
    %>--%>
</body>
</html>
