<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/16
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${prefix}/logins" enctype="multipart/form-data">
    <p>用户名<input type="text" name="un"></p>
        <p>密码<input type="text" name="pw"></p>
        <p> <input type="submit" value="login"></p>
    </form>
</form>
</body>
</html>
