<%@ page import="entity.User" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/6
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user =(User) session.getAttribute("u");
    if(user==null){
        response.sendRedirect("login.jsp");
    }
%>
