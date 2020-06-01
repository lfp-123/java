<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="dao.mailboxDao" %>
<%@ page import="entity.mailbox" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>邮件收发平台</title>
	<link type="text/css" rel="stylesheet" href="css/sms.css" />
	<script src="js/jquery-3.0.0.min.js" type="text/javaScript"></script>
</head>

<body>
<div id="main">
	<div class="mainbox">
		<div class="title myMessage png"></div>
		<%@include file="menu.jsp" %>
		<div id="error">${error}</div>
		<form action="/email/main" id="search" >
		<div><span style="font-size:24px; font-weight: bold;">查看邮件</span> <input class="searchinp"  placeholder="可通过发送人、标题、内容搜索"/> <button id="search">搜索</button></div>
		</form>
		<div class="content messageList" style="clear: both;">
			<form method="post" id="email" action="/email/email?type=delAll">
			<div class="emaildo">全选 <input type="checkbox" id="all" onclick="checkAll()"> <button >标记为已读</button> <button id="deall">删除勾选</button></div>
				<ul>
				<c:forEach var="mailbox" items="${mailboxes}" >
				<li  <c:if test="${mailbox.state eq 0}">class="unReaded"</c:if> >
					<input type="checkbox"  name="ck" value="${mailbox.id}">
					<b><a href="readMsg.jsp?id=${mailbox.id}">" ${mailbox.title}"</a> </b>
					<p>邮件内容:
						<c:if test="${f:length(mailbox.content)>8}">
							${f:substring(mailbox.content,0,8)}...
						</c:if>
						<c:if test="${f:length(mailbox.content)<=8}">
							${mailbox.content}
						</c:if>
						</p> <em><a href="">回信</a></em> <em>
				<a href="/email/email?type=del&id=${mailbox.id}" onclick="return del()">删除</a></em></li>
				</c:forEach>
			</ul>
			</form>
			<br>
			<p style="text-align:right;"> <b>[1/5]</b> &nbsp; <a href="">上一页</a> &nbsp; <a href="">下一页</a> </p>
		</div>
	</div>
</div>
</body>
<script>

	function  del() {
		return confirm("确定删除吗?")
    }
	function checkAll(){
        //让其他复选框的选中状态   跟   全选选中状态一致
        var all=document.getElementById("all");  //全选
        var cks=document.getElementsByName("ck"); //其他复选框
        //复选框中有一个属性可以描述选中状态 checked=true/false    选中/不选中
        for(var i=0;i<cks.length;i++){
            cks[i].checked=all.checked;
        }
    }
    ${"#deAll"}.click(function(){
		document.getElementById("email").submit();
    })

</script>
</html>




