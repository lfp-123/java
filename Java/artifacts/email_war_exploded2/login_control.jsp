<%@ page import="dao.emailUserDao" %>
<%@ page import="entity.emailUser" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/6
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        emailUserDao eud = new emailUserDao();
        emailUser eu = eud.queryByUsernamePassword(username, password);
        if(eu!=null){
            session.setAttribute("eu",eu);
            request.getRequestDispatcher("main.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    %>
</body>
</html>
