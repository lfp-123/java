<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/4
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/jsp/js/jquery-3.0.0.min.js" type="text/javascript"></script>
</head>
<body>
<fieldset style="width: 200px;text-align: center">

    <form action="/jsp/day02/server.jsp" method="get" onsubmit="return changes()">
        <h4>注册</h4>
        <p>用户名: <input type="text" name=" text" id ="un"></p>
        <p>密码1：<input type="password" name="password" id="pw1" ></p>
        <p>密码2：<input type="password" name="password"id="pw2" ></p>
        <p><input type="submit"value="提交"></p>
    </form>
</fieldset>
<div id="error" style="size: 11px;color: red;"></div>
</body>
<script>
    function  changes() {
       var num =  $("#un").val();
       var pw1 =  $("#pw1").val();
       var pw2 =  $("#pw2").val();
       if (num==null||num==""){
           $("#error").html("账户名不能为空！");
           return false;
       }
       if(pw1==null||pw1==""||pw2==null||pw2==""){
          $("#error").html("密码不能为空！");
           return false;
       }
       if(pw1!=pw2){
           $("#error").html("两次密码不相同！")
           return false ;
       }


    }
</script>
</html>
