<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>课程管理</title>
<link rel="icon" type="image/x-icon" href="images/tubiao.jpg" />
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/distpicker.js"></script>
<script>
$(document).ready(function(){
	  $("#delete").click(function(){
		  var name = prompt("请输入你想删除的课程名称","语文");
		  window.location.href="deleteCourse?name="+name;
		  alert("删除成功");
	  });
	  
	  $("#commit").click(function(){
		  alert("添加成功");
	  });
	});
</script>
<link rel="stylesheet" type="text/css" href="css/xueshengadd.css" />
<style type="text/css">
ul
{
list-style-type:none;
margin:0;
padding:0;
}
li
{
display:inline;
}

</style>
</head>
<body>
<div id="head" ></div>
	<table border="0">
			<tr>
				<th scope="col"><a href="../StudentTest/jsp/adminindex.jsp"><img src="images/index.png" border="0" width="50" height="50"></a></th>
				<th scope="col"><a href="../StudentTest/jsp/SelectstudentT.jsp"><img
						src="images/student.png" border="0" width="50" height="50"></a></th>
				<th scope="col"><a href="../StudentTest/jsp/SelectcourseT.jsp"><img
						src="images/course.png" border="0" width="50" height="50"></a></th>
				<th scope="col"><a href="../StudentTest/jsp/UpdateteacherT.jsp"><img
						src="images/teacher.png" border="0" width="50" height="50"></a></th>
				<th scope="col"><a href="../StudentTest/login.html"><img
						src="images/exit.png" border="0" width="50" height="50"></a></th>
			</tr>
			<tr>
				<td><span style="color: blue">信息首页</span></td>
				<td><span style="color: blue">学生管理</span></td>
				<td><span style="color: blue">课程管理</span></td>
				<td><span style="color: blue">教师管理</span></td>
				<td><span style="color: blue">退出登录</span></td>
			</tr>
		</table>
<b id="title">课程管理：</b> 
		<hr/>
	<form method="post" action="addCourse">
	<!-- 图标 -->
	<ul style="float:right ">
     <!--<li id = "search" ><span><img src="images/select.png" border="0" width="50" height="50"></span></li>-->	
	<li id = "add"><span><img src="images/add.png" border="0" width="50" height="50"></span></li>
	<li id = "delete"><span><img src="images/delete.png" border="0" width="50" height="50"></span></li>
	</ul>
		<br>
		<table width="75%" border="1" align="center" cellpadding="3"
			cellspacing="1" bordercolor="#000000"
			style="border-collapse: collapse">

			<tr>
				<td>课程名称：</td>
				<td><input id="name" name='CourseName' type='text' value='' /></td>
			</tr>
			<tr>
				<td>课程编码：</td>
				<td><input id ="xuehao"name='CourseCode' type='text' value='' /></td>
			</tr>
			<tr>
				<td>任课老师编码</td>
				<td><input name='TeacherCode' type='text' /></td>
			</tr>
			<tr>
				<td>开课时间：</td>
				<td><textarea name="time" cols="50" rows="8" id="beizhu"></textarea></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input id ="commit" type="submit" value="提交" /> <input type="reset"
					value="重置" /></td>
			</tr>
		</table>
	</form>
	
	
	 
</body>
</html>
