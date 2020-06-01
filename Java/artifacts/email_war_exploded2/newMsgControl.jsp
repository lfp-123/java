<%@ page import="dao.mailboxDao" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/9
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="menu.jsp" %>
<%
    request.setCharacterEncoding("utf-8");
    String username = eu.getUsername();
    String toname = request.getParameter("toname");
    String content = request.getParameter("content");
    String title = request.getParameter("title");
    mailboxDao mailboxDao = new mailboxDao();
    int i = mailboxDao.querySend(username, toname, title, content);
    if(i>0) {
        out.print("<script> alert('发送成功');location.href = '/email/main.jsp';</script> ");
    }else{
        out.print("<script> alert('发送失败');location.href = '/email/main.jsp';</script> ");
    }

%>
</body>
</html>
