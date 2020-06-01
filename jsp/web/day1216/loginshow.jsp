<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/16
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>登陆成功:${u.username}<img src="${prefix}/upload/${u.path}" style="height: 100px; width: 100px "></h2>
<table border="1" cellspacing="0" cellpadding="5">
    <tr>
        <th>用户</th>
        <th>密码</th>
        <th>头像</th>
    </tr>
        <c:forEach items="${eu}" var="eu">
    <tr>
        <td>${eu.username}</td>
        <td>${eu.password}</td>
        <td><img src="${prefix}/upload/${eu.path}"></td>
        <td>${eu.path}</td>
        <td><a href="${prefix}/download?fileName=${eu.path}">下载</a></td>
    </tr>
        </c:forEach>
</table>
</body>
</html>
