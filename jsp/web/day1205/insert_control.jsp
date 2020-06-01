<%@ page import="dao.UserDao" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/5
  Time: 17:52
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
    String un = request.getParameter("un");
    String pw = request.getParameter("pw");
    String date = request.getParameter("date");
    String sex = request.getParameter("sex");
    String age = request.getParameter("age");
    UserDao userDao = new UserDao();
    int i = userDao.insertUser(un, pw, date, sex, Integer.parseInt(age));
    if(i>0) {
        out.print("<script> alert('添加成功');location.href = '/jsp/day1205/welcome.jsp';</script> ");
    }else{
        out.print("<script> alert('添加失败');location.href = '/jsp/day1205/welcome.jsp';</script> ");
    }


%>
</body>
</html>
