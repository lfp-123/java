<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/10
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib  uri="http://java.sun.com/jstl/fmt" prefix="f"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    request.setAttribute("number",0.888823);

%>
<f:formatNumber value="1234556.11111" maxFractionDigits="2"></f:formatNumber> <br>
<f:formatNumber value="${number}" maxFractionDigits="2"></f:formatNumber>

<f:formatNumber type="percent" value="0.333" maxFractionDigits="2"></f:formatNumber> <br>
<f:formatNumber type="percent" value="${number}" maxFractionDigits="2"></f:formatNumber> <br>

<f:formatNumber type="currency" value="${number}" maxFractionDigits="2" pattern="$.00"></f:formatNumber> <br>
<p>转换前</p>
${date} <br>
<f:parseDate pattern="yyyy-MM-dd" value="2019-12-11"></f:parseDate>
<f:parseDate pattern="yyyy-MM-dd" value="${date}"></f:parseDate>
</body>
</html>
