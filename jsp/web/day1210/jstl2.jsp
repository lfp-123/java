<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/12/10
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--fmt 做数据格式化（数字，日期）
formatNumber 实现数字格式化
value：指定格式化数字(可以写实际的值，EL表达式)
maxFractionDigits：指定保留的小数位，会四舍五入
type：number为数字  percent：百分比  currency：显示金额(货币)
pattern：可以指定数字格式 0表示数字
--%>
<%
    request.setAttribute("number",0.897865);
    request.setAttribute("date",new Date());
%>
<fmt:formatNumber value="123456.12645" maxFractionDigits="2"></fmt:formatNumber><br>
<fmt:formatNumber value="${number}" maxFractionDigits="2"></fmt:formatNumber><br>

<%--百分比--%>
<fmt:formatNumber type="percent" value="0.897865" maxFractionDigits="2"></fmt:formatNumber><br>
<fmt:formatNumber value="${number}" type="percent" maxFractionDigits="2"></fmt:formatNumber><br>
<%--货币--%>
<fmt:formatNumber type="currency" value="98765.4578" maxFractionDigits="2"></fmt:formatNumber><br>
<fmt:formatNumber type="currency" value="98765.4578" maxFractionDigits="2" pattern="$.00"></fmt:formatNumber><br>

<%--日期格式化--%>
<h2>转换前：${date}</h2>
<h2>转换后：<%--将字符串转日期--%>
    <fmt:parseDate value="2019-06-04" pattern="yyyy-MM-dd"></fmt:parseDate>
</h2>
<h2>转换后：<%--将日期格式化--%>
    <fmt:formatDate value="${date}" pattern="yyyy-MM-dd"></fmt:formatDate>
</h2>

<%--函数集 ${fn:方法()}--%>
<%--超过8位不展示，用...代替--%>
<c:set value="方法方法所撒奥奥奥奥奥奥奥奥" var="message" scope="request"></c:set>
<c:if test="${fn:length(message)>8}">
    ${fn:substring(message,0,8)}...
</c:if>
<c:if test="${fn:length(message)<=8}">
    ${message}
</c:if>
</body>
</html>
