<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/9
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>统计网站访问次数</title>
</head>
<body>
<%
    Integer index =(Integer) application.getAttribute("count");
    if(index ==null){
        application.setAttribute("count",1);
    }else {
        index++;
        application.setAttribute("count",index);
    }
    request.getRequestDispatcher("show.jsp").forward(request,response);
%>

</body>
</html>
