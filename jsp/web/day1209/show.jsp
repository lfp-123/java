<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/9
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>该网站访问次数：<%= application.getAttribute("count")%></p>
<%
    pageContext.setAttribute("scope",request,PageContext.REQUEST_SCOPE);

%>
</body>
</html>
