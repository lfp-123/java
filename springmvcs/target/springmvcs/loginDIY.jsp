<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/2/7
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/mvc/user/loginDIY" method="post">
    <p>账号：<input type="text" name="username"></p>
    <p>年龄：<input type="text" name="age"></p>
    <p>生日：<input type="text" name="time"></p>
    <p><input type="submit" value="登陆"></p>
</form>
</body>
</html>
