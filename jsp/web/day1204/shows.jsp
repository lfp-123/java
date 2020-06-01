<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/4
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String username  = request.getParameter("username");
    String password = request.getParameter("password");
    String gender = request.getParameter("gender");
    String[] ints = request.getParameterValues("ints");
    String[] add = request.getParameterValues("address");
    String bz = request.getParameter("bz");
    String dates = request.getParameter("dates");
    String files = request.getParameter("files");
%>
     <p>用户名:<%= username%></p>
     <p>密码:  <%= password%></p>
     <p>性别:  <%= gender %></p>
     <p>爱好:  <%  if(ints!=null){for (String in :ints){ out.print(in+ " "); }}%></p>
     <p>生日：<%= dates%></p>
     <p>地址:  <% if(add!=null) {for (String ins :add){ out.print(ins+ " "); }}%></p>
     <P>备注信息： <%= bz %></P>


</body>
</html>
