<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="dao.mailboxDao" %>
<%@ page import="entity.mailbox" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>邮件收发平台</title>
<link type="text/css" rel="stylesheet" href="css/sms.css" />
</head>
<body>
	<div id="main">
		<div class="mainbox">
			<div class="title myMessage png"></div>
			<%@include file="menu.jsp" %>
			<div id="error">${error}</div>
			<h1>已发邮件</h1>
			<div class="content messageList">
				<div class="emaildo">全选 <input type="checkbox">  <button>删除勾选</button></div>
				<c:forEach var="mailbox" items="${mailboxes}">
					<ul>
						<li class="unReaded"> <input type="checkbox"/> <b><a href="readMsg.jsp?id=${mailbox.id}">" ${mailbox.title}"</a> </b>
							<p>邮件内容
								<c:if test="${f:length(mailbox.content)>8}">
									${f:substring(mailbox.content,0,8)}.....
								</c:if>
								<c:if test="${f:length(mailbox.content)<=8}">
									${mailbox.content}
								</c:if>.........
							</p> <em><a href="">回信</a></em> <em><a
									href="/email/email?type=del&id=${mailbox.id}">删除</a></em></li>
					</ul>
				</c:forEach>



			</div>
		</div>
	</div>
</body>
</html>
