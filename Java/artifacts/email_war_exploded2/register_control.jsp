<%@ page import="entity.emailUser" %>
<%@ page import="dao.emailUserDao" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/6
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        emailUserDao eud = new emailUserDao();
        int i = eud.queryInset(username, password, email);
        if(i>0) {
            out.print("<script> alert('注册成功');location.href = '/email/index.jsp';</script> ");
        }else{
            out.print("<script> alert('注册失败');location.href = '/email/register.jsp';</script> ");
        }

    %>
</body>
</html>
