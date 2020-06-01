<%@ page import="dao.UserDao" %>
<%@ page import="entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/10
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<c:forEach var="指定遍历的对象名称（临时变量）"--%>
           <%--items="指定需要遍历的集合，数组 结合el表达式"--%>
           <%--begin="指定开始下标" end="指定结束下标"--%>
           <%--step="步长 代表每一次变量的改变"--%>
           <%--varStatus="遍历对象的状态--%>
           <%--count ：当前行数 从1开始--%>
           <%--index：索引值 从0开始"></c:forEach>--%>

<c:forEach begin="1" end="10" step="3" varStatus="status">
    * ${status.count}<br>
</c:forEach>
<c:choose>
    <c:when test="${param.un == 'admin'}">
        欢迎您：管理员
    </c:when>
    <c:when test="${param.un eq 'vip'}">
        欢迎您：超级
    </c:when>
    <c:when test="${param.un eq 'user'}">
        欢迎您：
    </c:when>
    <c:otherwise>
        欢迎你：游客-- <a href="login.jsp">请登录</a>
    </c:otherwise>
</c:choose>
集合遍历 foreach <br>

<c:forEach begin="1" end="10" step="3" varStatus="status">
    * ${status.count}<br>
</c:forEach>
<%
    UserDao userDao = new UserDao();
    List<User> users = userDao.queryAll();
    request.setAttribute("users",users); // 设作用域内的值
%>

<table border="1" cellspacing="0" cellpadding="5">
<tr>
    <td>编号</td>
    <td>姓名</td>
    <td>密码</td>
    <td>生日</td>
    <td>性别</td>
    <td>年龄</td>
</tr>
<c:forEach var="u" items="${users}" varStatus="status">
    <tr  <c:if test="${status.count%2 eq 0}"> style="background-color: red" </c:if> >
        <td>${u.id})</td>
        <td>${u.username}</td>
        <td>${u.password}</td>
        <td>${u.date}</td>
        <td>${u.sex}</td>
        <td>${u.age}</td>
    </tr>
</c:forEach>
</table>

</body>
</html>
