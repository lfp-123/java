<%@ page import="dao.UserDao" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/5
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%
        String id = request.getParameter("id");
        int result = Integer.parseInt(id);
        UserDao userDao = new UserDao();
        int i = userDao.deleteUserById(result);
        if(i>0) {
            out.print("<script> alert('修改成功');location.href = '/jsp/day1205/welcome.jsp';</script> ");
        }else{
            out.print("<script> alert('修改失败');location.href = '/jsp/day1205/welcome.jsp';</script> ");
        }
    %>
</body>
</html>
