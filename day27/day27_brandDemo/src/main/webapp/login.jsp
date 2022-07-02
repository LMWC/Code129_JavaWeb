<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link href="css/login.css" rel="stylesheet">
</head>

<body>
<div id="loginDiv" style="height: 350px">
    <form action="/loginServlet" id="form">
        <h1 id="loginMsg">欢迎登录</h1>
        <div id="errorMsg">${login_msg}</div>
        <p>用户名:<input id="username" name="username" value="${cookie.username.value}" type="text"></p>

        <p>密 &nbsp; 码:<input id="password" name="password" value="${cookie.password.value}" type="password"></p>
        <p>是否记住:<input id="remember" name="remember" type="checkbox"></p>
        <div id="subDiv">
            <input type="submit" class="button" value="登录">
            <input type="reset" class="button" value="取消">&nbsp;&nbsp;&nbsp;
            <a href="register.jsp">没有账号？</a>
        </div>
    </form>
</div>

</body>
</html>
