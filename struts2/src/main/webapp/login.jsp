<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/20
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/struts2/test.action"method="post">
    <p>用户<input type="text" name="u.username"></p>
    <p>密码<input type="password" name="u.password"></p>
    爱好：
    <Input type="checkbox" name="u.hobby" value="篮球">篮球
    <Input type="checkbox" name="u.hobby" value="足球">足球
    <Input type="checkbox" name="u.hobby" value="排球">排球
    <Input type="checkbox" name="u.hobby" value="游泳">游泳
    <Input type="checkbox" name="u.hobby" value="池塘">池塘
    <p><input type="submit" value="提交"> </p>
</form>
</body>
</html>
