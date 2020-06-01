<%@ page import="entity.User" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/10
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    User u = new User();
    request.setAttribute("u",u);
    request.setAttribute("names","原始方法");
%>

<c:set var = "username" value="张三" scope="session"></c:set>
<c:set var ="name" value="李四" scope="request"></c:set>
<c:set var ="name" value="李四" scope="session"></c:set>
<c:set var ="name" value="李四" scope="page"></c:set>
<c:set var ="name" value="李四" scope="application"></c:set>
<p> session : ${sessionScope.username}</p>
<%--<c:set value="赋值的内容" property="属性名" target="对象"></c:set>--%>
<c:set value="良缘" property="username" target="${u}"></c:set>
<P> 用户 ： ${u.username}</P>
<%--<c:remove var="删除变量" scope="session"> </c:remove>--%>
<c:remove var="username" scope="request"></c:remove>
<p> 用户：${username}</p>
<p> 删除：${name}</p>
<P> 原始方法： ${names}</P>
<c:set var ="name" value="迪丽热巴" scope="request"></c:set>
<c:out value="${name}" default="对象是null"></c:out>

<p> ${"<a href ='#'> EL </a> "}</p>
<P> <c:out value="<a href ='#'> JSTL </a> " escapeXml="Y"> </c:out></P>
<c:set var = "sex" value="1" scope="request"> </c:set>
<%--<c:if test="条件表达式">满足if的表达式</c:if>--%>
<p>性别：</p>
<c:if test="${sex eq 1}">男</c:if>
<c:if test="${sex eq 0}">女</c:if>
<%--choose 标签 类似于多重if --%>
<c:choose>
    <c:when test="1">你</c:when>
    <c:when test="2">我</c:when>
    <c:when test="3">他</c:when>
    <c:when test="4">阿红</c:when>
    <c:when test="5">安康</c:when>
</c:choose>

</body>
</html>
