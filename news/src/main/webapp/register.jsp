<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/20
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/news/register.action" method="post" enctype="multipart/form-data">
    <p>用户：<input type="text" name="name"></p>
    <p>密码：<input type="password" name="pw"></p>
    <input type="submit" value="注册">
</form>

</body>
</html>
