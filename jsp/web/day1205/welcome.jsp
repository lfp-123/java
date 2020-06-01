<%@ page import="dao.UserDao" %>
<%@ page import="entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/5
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="new_login_control.jsp"%>
<!--
    // String un = request.getParameter("un");
    User us =(User) session.getAttribute("u");
    if(us==null){
        out.print("请先登陆！！");
        response.sendRedirect("login.jsp");
    }
-->
<P>
    用户：<%= user!=null? user.getUsername():"xxx" %>
</P>
<p> 登陆成功！！！</p>
<p>显示用户列表</p>
<%
    UserDao userDao = new UserDao();
    List<User> users = userDao.queryAll();
%>
<table border="1" cellpadding="5" cellspacing="1" width="60%">
    <a href="user_insert.jsp">新增</a>
    <tr>
        <th>编号</th>
        <th>账号</th>
        <th>密码</th>
        <th>出生年月</th>
        <th>性别</th>
        <th>年龄</th>
        <th>操作</th>

    </tr>
    <% for(User u :users){  %>
     <tr>
        <th><%= u.getId()%></th>
         <th><a href="user_update.jsp?id=<%= u.getId()%>"> <%= u.getUsername() %></a></th>
        <th><%= u.getPassword()%></th>
        <th><%= u.getDate()%></th>
        <th><%= u.getSex().equals("1")?"男":"女"%></th>
        <th><%= u.getAge()%></th>
         <th><a href="user_delete.jsp?id=<%= u.getId()%>"onclick= "return deleteUser()">删除</a> </th>
    </tr>
    <% }%>
</table>
</body>
<script>
    function deleteUser(){
          var confirm2 = confirm("确定删除吗？");
            return confirm2;
    }
</script>
</html>

