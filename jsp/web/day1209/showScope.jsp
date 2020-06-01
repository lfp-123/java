<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/9
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>request:<%= request.getParameter("request")%></h1>
<h1>session:<%= session.getAttribute("session")%></h1>
<h1>application:<%= application.getAttribute("application")%></h1>
</body>
</html>
