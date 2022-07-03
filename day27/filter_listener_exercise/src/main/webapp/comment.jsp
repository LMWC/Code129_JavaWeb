<%--
  Created by IntelliJ IDEA.
  User: lmw23
  Date: 2022/5/21
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>帖子</title>
</head>
<body>
    <form action="/CommentServlet" method="post">
          <textarea name="comment">
          </textarea>
        <input type="submit" value="发送">
    </form>
</body>
</html>
