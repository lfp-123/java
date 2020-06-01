<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/9
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    /*
        获取请求传递的参数 类似于表单提交数据 通过地址栏？name= value  可以用request.getParameter
     */
%>
<h3>session <%= session.getAttribute("un")%> welcome成功！！！</h3>
<h3>application <%= application.getAttribute("un")%> welcome成功！！！</h3>
</body>
</html>
