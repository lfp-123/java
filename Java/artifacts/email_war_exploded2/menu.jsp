<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="entity.emailUser" %>
<div class="menu">
				<%
					emailUser eu = (emailUser)session.getAttribute("eu");
					if(eu==null){
					    response.sendRedirect("index.jsp");
					}
				%>
				<span>当前用户：<a href="main.jsp"><%= eu!=null?eu.getUsername():null%></a></span> <span><a href="newMsg.jsp">发送邮件</a></span>
				<span><a href="yifaMsg.jsp">已发邮件</a></span> <span><a href="">退出</a></span>
			</div>