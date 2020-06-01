<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/25
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Struts2和hibernate登陆功能</title>
</head>
<body>
    <form method="post" action="/hib/user.action">
        <p>用户：<input type="text" name="u.username"> </p>
        <p>密码：<input type="password" name="u.password"> </p>
        <p><input type="submit" value="登陆"></p>
    </form>
</body>
</html>
