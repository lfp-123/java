<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="dao.mailboxDao" %>
<%@ page import="entity.mailbox" %>
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
		<%
			String username = eu.getUsername();
			mailboxDao md = new mailboxDao();
			List<mailbox> mailboxes = md.queryAllByToname(username);

		%>
		<div id="error">${error}</div>
		<div><span style="font-size:24px; font-weight: bold;">查看邮件</span> <input class="searchinp"  placeholder="可通过发送人、标题、内容搜索"/> <button>搜索</button></div>

		<div class="content messageList" style="clear: both;">
			<div class="emaildo">全选 <input type="checkbox"> <button>标记为已读</button>  <button>删除勾选</button></div>
			<% for (mailbox mailbox : mailboxes) {%>
			<ul>
				<li class="unReaded"> <input type="checkbox"/> <b><a href="readMsg.jsp?id=<%=mailbox.getId()%>">" <%=mailbox.getTitle()%>"</a> </b>
					<p>邮件内容（<%= mailbox.getContent().substring(0,22) %>..........）</p> <em><a href="">回信</a></em> <em><a
							href="">删除</a></em></li>

			</ul>
			<% }%>
			<br>
			<p style="text-align:right;"> <b>[1/5]</b> &nbsp; <a href="">上一页<a> &nbsp; <a href="">下一页</a> </p>
		</div>
	</div>
</div>
</body>
</html>




