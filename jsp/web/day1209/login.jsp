<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/9
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
        <!--
        通过cookie实现记住密码
        请求所有携带的cookie信息
        cookie数组会有默认值
        -->
        <%
            String un= "";
            String pw= "";
            Cookie[] cookies = request.getCookies();
            if(cookies!=null) {
                for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("username")){
                        un=  cookie.getValue();
                    }
                    if(cookie.getName().equals("password")){
                        pw = cookie.getValue();
                    }

                }
            }
        %>
        <form action="login_control.jsp" method="post">

           <P> 用户名： <input type="text" name="un"  value="<%= un %>"></p>
           <P> 密码：<input type="password" name="pw" value="<%= pw %>"></p>
           <P> <input type="submit" value="提交"></p>

        </form>
</body>

</html>
