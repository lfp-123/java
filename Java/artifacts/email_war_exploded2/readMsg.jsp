<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="dao.mailboxDao" %>
<%@ page import="entity.mailbox" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>邮件平台</title>
<link type="text/css" rel="stylesheet" href="css/sms.css" />
</head>
	<body>
		<div id="main">
			<div class="mainbox">
				<div class="title readMessage png"></div>
				<%@include file="menu.jsp" %>
				<%
					String ids = request.getParameter("id");
					int id = Integer.valueOf(ids);
					mailboxDao mailboxDao = new mailboxDao();
					mailbox u =mailboxDao.queryByTitle(id);
				%>
				<h1>阅读邮件</h1>
				<div class="content">
					<div class="message">
							<div class="tmenu">
								<ul class="clearfix">
									<li><%=u.getTitle() %> </li>
									<li><%=u.getFromname()%></li>
									<li><%=u.getCreatdate()%></li>
								</ul>
							</div>
					  	 	<div class="view">
								<p><%= u.getContent()%></p>
							</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
