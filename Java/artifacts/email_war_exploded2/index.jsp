<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script src="/email/js/jquery-3.0.0.min.js" type="text/javascript"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>邮件收发平台</title>
<link type="text/css" rel="stylesheet" href="css/sms.css" />
</head>
<body>
<div style="width:360px; margin:0px auto; margin-top: 60px; color:white;font-size:30px" class="png">思诚科技邮件管理系统</div>
<div id="loginForm" class="userForm png">
	<form method="post"action="login_control.jsp" onsubmit="" >
		<dl>			
			<div id="error" style="color: red;"></div>
			<dt>用户名：</dt>
			<dd><input id="un" type="text" name="username" /></dd>
			<dt>密　码：</dt>
			<dd><input id="pw" type="password" name="password" /></dd>
		</dl>
		<div class="buttons">
			<input class="btn-login png" type="submit" name="submit"  value=" " />
			<input class="btn-reg png" type="button" name="register" value=" " onclick="window.location.href=('/email/register.jsp')" />
		</div>		
	</form>
</div>
</body>

<script>
	function changess() {


        var un = $("#un").val();
        var pw = $("#pws").val();
        if(un==null||un==""){
            $("#error").html("账户名不能为空！");
            return false;
        }
        if(pw==null||pw==""){
            $("#error").html("密码不能为空！");
            return false;
        }
        return true;
    }
</script>

</html>

