<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/9
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    pageContext.setAttribute("page","pageOne");
    //request 作用域在一次请求
    request.setAttribute("request","request");
    //session 作用域一次会话有效
    session.setAttribute("session","session");
    //appliscation
    application.setAttribute("application","application");
    //request.getRequestDispatcher("showScope.jsp").forward(request,response);
    response.sendRedirect("showScope.jsp");


%>
<H1>PAGEONE1： <%= pageContext.getAttribute("page")%></H1>
<%
    pageContext.include("PageTwo.jsp");

%>
<H1>PAGEONE2： <%= pageContext.getAttribute("page1")%></H1>
</body>
</html>
