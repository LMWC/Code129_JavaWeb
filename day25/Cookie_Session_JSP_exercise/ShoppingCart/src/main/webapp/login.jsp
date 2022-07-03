<%@ page import="com.itheima.utils.CookieUtils" %>
<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: lmw23
  Date: 2022/5/19
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<center>
    <%--2.在用户再次访问登录页面时，从cookie中取出用户名和密码自动填充到登录用户名和密码输入框中--%>
    <%
        //解决请求响应中文乱码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //request:jsp内置对象 生来就有 可以直接使用  JSP中常用的内置对象还有：response、session、application(ServletContext)、out(response.getWriter())
        Cookie[] cookies = request.getCookies();
        String username = CookieUtils.getCookieValue(cookies,"username");
        String password = CookieUtils.getCookieValue(cookies,"password");
    %>

    <h1>用户登录</h1>
    <form action="login" method="post">
        用户名：<input type="text" name="username" value="<%=username==null?"":URLDecoder.decode(username)%>"/><br/>
        密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" name="password" value="<%=password==null?"":password%>"/><br/>
        <input type="checkbox" name="rem">记住密码<br>
        <input type="submit" value="登录"/>
    </form>
</center>
</body>
</html>
