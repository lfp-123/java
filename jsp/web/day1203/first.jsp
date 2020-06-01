<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<h1>
    南昌工程学院

</h1>
<h2>日期格式化</h2>
<%
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String format = sdf.format(new Date());
%>
当前日期为：<%= format%>
<h2>数字格式化</h2>
<%
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##");
    String num = decimalFormat.format(123456667);

%>
格式化时间为：<%=num%>
</body>
</html>
