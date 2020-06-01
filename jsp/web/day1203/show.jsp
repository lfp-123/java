<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/3
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>使用get方法获取数据</h1>
<li><P>用户名： <%= request.getParameter("username")%></P></li>
<li><P>密码： <%= request.getParameter("password")%></P></li>
</body>
</html>
