<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/1/8
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>欢迎${u.username} <p>头像：<img style="width: 100px; height: 100px" src="${pageContext.request.contextPath}/upload/${u.head}"></p>登陆成功！</h1>
    <h1>展示用户信息</h1>
<a href="/test/testPath/${u.id}">${u.id}测试</a>
<p>账户：${u.username}</p>
    <p>密码：${u.password}</p>
<a href="">新增用户</a>&nbsp;<a href="">批量删除</a>
<table border="1" cellspacing="1" cellpadding="5">

    <tr>
        <td><input type="checkbox">全选</td>
        <th>编号</th>
        <th>账号</th>
        <th>密码</th>
        <th>头像</th>
        <th>时间</th>
        <th>删除</th>
    </tr>
    <f:forEach items="${list}" var="user">
    <tr>
        <th><input type="checkbox" id=""></th>
        <th>${user.id}</th>
        <th><a href="">${user.username}</a></th>
        <th>${user.password}</th>
        <th>
            <a href="/mvc/user/imageDownload?fileName=${user.head}">
            <img style="width: 100px; height: 100px"
                 src="${pageContext.request.contextPath}/upload/${user.head}">
            </a>
        </th>
        <th>${user.time}</th>
        <th><a href="/mvc/user/delUser?id=${user.id}">删除</a> </th>
    </tr>
    </f:forEach>

</table>
<a href="">上一页</a><a href="">下一页</a>
</body>
</html>
