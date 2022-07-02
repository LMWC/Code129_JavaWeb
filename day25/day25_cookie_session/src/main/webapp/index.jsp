<%@ page import="com.itheima.bean.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--JSP特有的注释--%>
<%--
  Created by IntelliJ IDEA.
  User: shuaige
  Date: 2022/5/19
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%--JSP指令 page指令：用于设置当前jsp页面字符编码及脚本语言  taglib指令：用来引入外部标签库--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--静态内容 --%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--JSP：动态网页  在JSP中既可以写静态内容html、css、js 还可以写动态内容java代码--%>
    <%--静态内容--%>
    <h1>Hello World666</h1>

    <%--
        JSP中编写java代码
            java小脚本：<% java代码 %>    java代码会被翻译到Servlet的service方法中
            java表达式：<%=java变量 %>    用于输出数据到页面显示  会被翻译到Servlet的service方法中
            java声明：<%! 全局变量|方法 %> 声明全局变量或方法     会被翻译到Servlet的类中
    --%>

    <%--java声明--%>
    <%!
        String message = "Hello China";
    %>

    <%--java小脚本--%>
    <%
        String msg = "Hello msg";
        System.out.println("Hello World666!");
    %>

    <%--输出表达式--%>
    <%=msg %>

    <%=message %>

    <%
        //创建一个集合 模拟从数据库查询出来的数据
        User user1 = new User(1, "zhangsan", "123");
        User user2 = new User(2, "lisi", "234");
        User user3 = new User(3, "itheima", "123");

        List<User> list = new ArrayList<User>();

        list.add(user1);
        list.add(user2);
        list.add(user3);

    %>

    <table border="1" width="300px" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <th>id</th>
            <th>用户名</th>
            <th>密码</th>
        </tr>
        <%
            for (User user : list) {
         %>
            <tr>
                <td><%=user.getId()%></td>
                <td><%=user.getUsername()%></td>
                <td><%=user.getPassword()%></td>
            </tr>
            <%
            }
        %>
    </table>
</body>
</html>
