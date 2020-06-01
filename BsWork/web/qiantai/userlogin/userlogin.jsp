<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script language="JavaScript" src="<%=path %>/js/public.js" type="text/javascript"></script>
	<script type='text/javascript' src='<%=path %>/dwr/interface/loginService.js'></script>
    <script type='text/javascript' src='<%=path %>/dwr/engine.js'></script>
    <script type='text/javascript' src='<%=path %>/dwr/util.js'></script>
    <script type='text/javascript' src='<%=path %>/js/jquery.min.js'></script>
	  <script>
		 function ajax() {
			 var name =  $("#userName").val();
			 var pw = $("#userPw").val();
             var type= document.getElementsByName("userType");
             var typeReal = type.item(0).value;
			 $.get('/bs/ajax','name='+name+'&pw='+pw+'&type='+typeReal,function (result) {
				 alert(result);
				 callback(result);
                })
		 }
         function callback(data)
         {
             document.getElementById("indicator").style.display="none";
             if(data=="no")
             {
                 alert("用户名或密码错误");
             }
             if(data=="yes")
             {   alert("登陆成功");
                 window.location.reload();
             }

         }
	  </script>

	  <script type="text/javascript">
	        function check()
			{                                                                                         
			     if(document.ThisForm.userName.value=="")
				 {
				 	alert("请输入用户名");
					return false;
				 }
				 if(document.ThisForm.userPw.value=="")
				 {
				 	alert("请输入密码");
					return false;
				 }

				    document.getElementById("indicator").style.display="block";
					var user = document.getElementById("userName");
					var pw = document.getElementById("userPw");
					var type= document.getElementsByName("userType");

					//loginService.login(user.value,pw.value,type.item(0).value,callback);


			}
		

	</script>
  </head>
  <c:if test="${sessionScope.userType==null}">
			<form action="<%=path%>/user?type=userLogin" name="ThisForm" method="post">
			      <table cellspacing="0" cellpadding="0" width="98%" align="center" border="0">
			          <tr>
			            <td align="center" colspan="2" height="10">教师用教师号登陆(学生用学号登陆)</td>
			          </tr>
			          <tr>
			            <td align="center" colspan="2" height="9"></td>
			          </tr>
			          <tr>
			            <td align="right" width="31%" height="30" style="font-size: 11px;">用户名：</td>
			            <td align="left" width="69%"><input id="userName" class="input" title="用户名不能为空" size="14" name="userName" type="text" /></td>
			          </tr>
			          <tr>
			            <td align="right" height="30" style="font-size: 11px;">密　码：</td>
			            <td align="left"><input id="userPw" class="input" title="密码不能为空" type="password" size="16" name="userPw"/></td>
			          </tr>
			          <tr>
			            <td align="right" height="30" style="font-size: 11px;">身　份：</td>
			            <td align="left">
                           <select name="userType" style="width: 70px">
                               <option value="1">老师</option>
                               <option value="2">学生</option>
                           </select>
                           <input type="button" value="登  录" onclick="ajax()" style="border:#ccc 1px solid; background-color:#FFFFFF; font-size:12px; padding-top:3px;" />
			               <img id="indicator" src="<%=path %>/img/loading.gif" style="display:none"/>
                        </td>
			          </tr>
			          <tr>
			            <td align="center" colspan="2" height="10">
			            </td>
			          </tr>
			      </table>
		    </form>
		    </c:if>
			<c:if test="${sessionScope.userType!=null && sessionScope.userType==1}">
			    <br/>
			     欢迎您：${sessionScope.tea.name }(老师) &nbsp;&nbsp;&nbsp;&nbsp;
			    <a href="<%=path %>/stu?type=logout">安全退出</a> &nbsp;&nbsp;&nbsp;&nbsp;
			    <br/><br/><br/>
			</c:if>
			
			<c:if test="${sessionScope.userType!=null && sessionScope.userType==2}">
			    <br/>
			     欢迎您：${sessionScope.stu.name1 }(学生) &nbsp;&nbsp;&nbsp;&nbsp;
			    <a href="<%=path %>/stu?type=logout">安全退出</a> &nbsp;&nbsp;&nbsp;&nbsp;
			    <br/><br/><br/>
			</c:if>
  </body>
</html>
