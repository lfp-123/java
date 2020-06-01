<%@ page import="util.DButil" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="entity.User" %>
<%@ page import="java.sql.Date" %>
<%@ page import="sun.security.pkcs11.Secmod" %>
<%@ page import="dao.UserDao" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/5
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
        //1、转码 2、接受数据，验证 3、判断、跳转
    /*
    request.getRequestDispatcher("跳转的相对路径").forward(request,response);
    重定向 实现页面跳转
    response.sendRedirect("welcome.jsp");
     */
    request.setCharacterEncoding("UTF-8");
    //获取请求参数的un

    String username = request.getParameter("un");
    String password = request.getParameter("pw");
    UserDao userDao = new UserDao();
    User u = userDao.queryByUsernamePassword(username, password);
    if(u!=null){
        //地址只能写相路径  一次请求一次响应
        //存储用户对象

        session.setAttribute("u",u);
        request.getRequestDispatcher("welcome.jsp").forward(request,response);

        //response.sendRedirect("welcome.jsp");
        //response.sendRedirect("http://www.baidu.com");
    }else{
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }

%>
</body>

</html>
