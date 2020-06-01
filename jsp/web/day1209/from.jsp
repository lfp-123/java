<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/9
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="el.jsp" method="post">

    <P> 用户名： <input type="text" name="un"  value=""></p>
    <P> 密码：<input type="password" name="pw" value=""></p>
    <P> 爱好：
    <input type="checkbox" name="ck" value="篮球">篮球
    <input type="checkbox" name="ck" value="足球">足球
    <input type="checkbox" name="ck" value="游泳">游泳
    <input type="checkbox" name="ck" value="羽毛球">羽毛球
    <input type="checkbox" name="ck" value="台球"> 台球

    <P> <input type="submit" value="提交"></p>

</form>
</body>
</html>
