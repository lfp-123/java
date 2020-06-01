<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/5
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="/jsp/js/jquery-3.0.0.min.js" type="text/javascript"></script>
    <title>Title</title>
</head>
<body>
        <form action="login_control.jsp" method="post" onsubmit="return change()">
            <p>用户名：<input  id="un" type="text" name="un"></p>
            <p>密码：<input id="pws" type="password" name="pw"></p>
            <p><input type="submit" value="登陆"></p>
        </form>
        <div id="error" style="color: red"></div>
</body>
<script>
    function change() {
        var un = $("#un").val();
        var pws = $("#pws").val();

        if(un==null||un==""){
            $("#error").html("账户名不能为空！");
            return false;
        }
        if(pws==null||pws==""){
            $("#error").html("密码不能为空！");
            return false;
        }
        return true;
    }
</script>
</html>
