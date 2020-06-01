<%@ page import="entity.User" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/9
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    request.setCharacterEncoding("utf-8");
    String[] cks = request.getParameterValues("ck");
%>
    <P> 账号： ${param.un}</P>
    <P> 密码： ${param.pw}</P>
<% for(int i=0;i<cks.length;i++){
session.setAttribute("i",i);%>
    ${paramValues.ck[i]}
        <%}%>

</body>
</html>
