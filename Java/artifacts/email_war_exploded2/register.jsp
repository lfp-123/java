<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script src="/email/js/jquery-3.0.0.min.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>短消息平台</title>
<link type="text/css" rel="stylesheet" href="css/sms.css" />
</head>
<body>
<div id="regTitle" class="png"></div>
<div id="regForm" class="userForm png">

	<form action="register_control.jsp" onsubmit = "return detections()" method="post">
		<dl>
		    <div id="error" style="color:red;"> ${error }</div>
			<dt>用 户 名：</dt>
			<dd><input id="un" type="text" name="username" /></dd>
			<dt>密　　码：</dt>
			<dd><input id="pw1" type="password" name="password" /></dd>
			<dt>确认密码：</dt>
			<dd><input id="pw2" type="password" name="affirm" /></dd>
			<dt>邮　　箱：</dt>
			<dd><input id="email" type="text" name="email" /></dd>
		</dl>
		<div class="buttons">
			<input class="btn-reg png" type="submit" name="register" value=" "  /><input class="btn-reset png" type="reset" name="reset" value=" " />
		</div>
		<div class="goback"><a href="index.jsp" class="png">返回登录页</a></div>
	</form>
</div>
</body>
<script>
	function  detections() {
		var un = $("#un").val();
        var pw1 = $("#pw1").val();
        var pw2 = $("#pw2").val();
        var em = $("#email").val();
        if (un == "" || un == null) {
            $("#error").html("账户名不能为空！");
            return false;
        }
        if (pw1 == "" || pw1 == null) {
            $("#error").html("密码不能为空！");
            return false;
        }
        if (pw2 == "" || pw2 == null) {
            $("#error").html("密码不能为空！");
            return false;
        }
        if (em == "" || em == null) {
            $("#error").html("账户名不能为空！");
            return false;
        }
        if (pw1 !== pw2) {
            $("#error").html("两次密码不一致！")
            return false;
        }
        return true;
    }
	
</script>
</html>
