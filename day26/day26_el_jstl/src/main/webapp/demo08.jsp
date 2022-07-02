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

    <%--需求1：要求使用forEach标签遍历0-9数字 显示的时候数字加粗红色 换行--%>
    <%--注意：forEach在遍历集合或数组元素时 会将数组的每一个元素存放到page域对象中 所以获取显示需要使用EL表达式--%>
    <%--
        c:forEach标签简单使用:
            begin:从几开始
            end：到几结束
            var：迭代变量
            step:间隔 步长
    --%>
    <c:forEach begin="0" end="9" var="i" step="2" varStatus="status">
        <b>${status.index} --> <font color="red">${i}</font></b><br>
    </c:forEach>

    <%--需求2：要求将Servlet中查询的所有用户列表数据显示在JSP页面表格中--%>
    <table border="1" cellspacing="0" cellpadding="0" width="300px" align="center">
        <tr>
            <th>序号</th>
            <th>姓名</th>
            <th>密码</th>
        </tr>
        <%--
            items：要遍历的集合或数组  使用EL表达式获取域对象中存储的集合或数组
            var：迭代变量  集合或数组中的每一个元素  forEach标签在遍历时会将每一个元素存入page域对象中 所以获取显示时 需要使用EL表达式
            varStatus：数组或集合中每一个元素的状态  通过${status.index} 得到元素的索引

            forEach标签实际使用语法：
                <c:forEach items="要遍历的集合|数组" var="迭代变量" varStatus="status">
                    ${迭代变量}
                </c:forEach>
        --%>
        <c:forEach items="${list}" var="user" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
