<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/9
  Time: 10:02
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
            if(un.equals("admin")&&pw.equals("123")){
                session.setAttribute("un",un);
                //1 创建cookie
                Cookie uncookie = new Cookie("username", un);
                Cookie pwcookie = new Cookie("password", pw);
                //2 一定要设置时间，否则会长期保留
                uncookie.setMaxAge(5*60);
                pwcookie.setMaxAge(5*60);
                //3 响应中带有cookie，reponse存入cookie
                response.addCookie(uncookie);
                response.addCookie(pwcookie);
                application.setAttribute("un",un);
                /*
                类似于用户信息很多页面都会用到session 可能会有很多个请求的时候会需要 这种数据最后存入session
                并不是所有的数据都适合存入session
                 */
                request.getRequestDispatcher("welcome.jsp").forward(request,response);
            }else {
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
        %>
</body>
</html>
