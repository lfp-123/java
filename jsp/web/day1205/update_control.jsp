<%@ page import="java.util.Date" %>
<%@ page import="dao.UserDao" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/5
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        //1 转码 2 取值 3 判断跳转
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        Integer integer = Integer.parseInt(id);
        String un = request.getParameter("un");
        String pw = request.getParameter("pw");
        String date = request.getParameter("date");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        Integer integer1 = Integer.valueOf(age);
        UserDao userDao = new UserDao();
        int i = userDao.updateById(integer,un, pw,date, sex, integer1);
        if(i>0) {
            out.print("<script> alert('修改成功');location.href = '/jsp/day1205/welcome.jsp';</script> ");
        }else{
            out.print("<script> alert('修改失败');location.href = '/jsp/day1205/welcome.jsp';</script> ");
        }
    %>


</body>
</html>
