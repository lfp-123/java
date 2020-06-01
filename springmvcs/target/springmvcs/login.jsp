<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/1/8
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/mvc/user/login" method="post">
    <p>账号：<input type="text" name="username"></p>
    <p>密码：<input type="password" name="password"></p>
    <p><input type="submit" value="登陆"></p>
</form>

</body>
</html>
