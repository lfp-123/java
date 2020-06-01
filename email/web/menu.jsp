<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="entity.emailUser" %>
<div class="menu">
	<span>当前用户：<a href="/email/main">${eu.username}</a></span> <span><a href="/email/user?type=queryAll">发送邮件</a></span>
				<span><a href="/email/email?type=yifa">已发邮件</a></span> <span><a href="">退出</a></span>
			</div>