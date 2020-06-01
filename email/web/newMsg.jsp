<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="dao.mailboxDao" %>
<%@ page import="entity.mailbox" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <title>发邮件</title>	    
<link type="text/css" rel="stylesheet" href="css/sms.css" />
</head>

 <body>
 <form action="/email/email?type=add" method="post" onsubmit="">
	<div id="main">
		<div class="mainbox">			
			<%@include file="menu.jsp" %>
			<h1>发送邮件</h1>
			<div class="content">
				<div class="message">
						<div class="tmenu">
							<ul class="clearfix">
								<li>
									发送给：
									<select name="toname">
										<c:forEach items="${emailUsers}" var="mail">
											<c:if test="${mail.username ne eu.username}">
												<option value="${mail.username}">${mail.username}</option>
											</c:if>
										</c:forEach>
									</select>
								</li>								
								<li>标题：<input type="text" name="title" id="title"/></li>
							</ul>
						</div>
						<div class="view">
							<textarea name="content" id="content"></textarea>
							<div class="send"><input type="submit" name="submit" value=" " /></div>
						</div>

				</div>
			</div>
		</div>
	</div>
</form>
</body>
</html>
