<%@ page import="dao.UserDao" %>
<%@ page import="entity.User" %>
<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/5
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="new_login_control.jsp"%>

<h1>欢迎<%= user!=null?user.getUsername():"xxx"%>修改页面</h1>
<h1>修改用户信息(要显示原来的信息)</h1>
<%
    String id = request.getParameter("id");
    User u=new User();
    if(id!=null){
    UserDao userDao = new UserDao();
    u = userDao.queryByid(id);
}

%>
<form action="update_control.jsp" method="post">
    <input type="hidden" value="<%= u.getId()%>" name="id">
<P>用户：<input type="text" value="<%= u.getUsername()%>" name="un"> </P>
<P>密码：<input type="password" value="<%= u.getPassword()%>" name="pw"> </P>
<P>出生年月：<input type="date" value="<%= u.getDate()%>" name="date"> </P>
<P>性别：
        <input type="radio" value="1" <%if(u.getSex().equals("1")){%>checked<% } %>  name="sex"> 男
        <input type="radio" value="0" <%if(u.getSex().equals("0")){%>checked<% } %> name="sex"> 女
</P>
<P>年龄：<input type="age" value="<%= u.getAge()%>" name="age"> </P>
    <p><input type="submit" value="修改"></p>
</form>
</body>
</html>
