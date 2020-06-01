<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/1/7
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>测试SpringMVC</h3>
<p>用户名${u.username}</p>
<p>密码${u.password}</p>
<p>爱好:
    <c:forEach var="c" items="${u.ck}">
        ${c}--
    </c:forEach>
</p>
</body>
</html>
