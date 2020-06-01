<%@ page import="entity.User" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/5
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="new_login_control.jsp"%>
<h2>新增用户列表</h2>
<form action="insert_control.jsp" method="post">
<P>用户名：<input type="text" name="un"></P>
<P>密码：<input type="password" name="pw"></P>
<P>出生年月：<input type="date" name="date"></P>
    <P>性别：
        <input type="radio" value="1"  name="sex"> 男
        <input type="radio" value="0"  name="sex"> 女
    </P>
    <P>年龄<input type="text" name="age" ></P>
    <input type="submit" value="提交">
</form>

</body>
</html>
