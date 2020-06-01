<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html><head>
	<script src="/jsp/js/jquery-3.0.0.min.js" type="text/javascript"></script>

</head>
<c:if test="${empty list}">
	<jsp:forward page="/video"></jsp:forward>
</c:if>
<body>
<div style="width:730px; margin:20px auto;">
	<video controls="controls" autoplay>
		<source src="mysql.mp4" type="video/mp4" >示例视频1</source>
		您的浏览器不支持video标签
	</video>
	<p><span style="font-weight:bold; font-size:20px; color:blue;">点赞</span> &nbsp;<span style="background-color:green; padding:5px; border-radius:5px">36</span>
	</p>
	<hr>
	<h3>评论列表</h3>
	<div id = "com">
		<c:forEach var="c" items="${list}">
			<p>${c.name} :&nbsp;${c.content} &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ${c.time}</p>
		</c:forEach>


	</div>
	<hr>
	<div >
		姓名：<input size="10" id="name" name="un" placeholder="请输入姓名"/><br>
		<textarea name="text" id="content" name="text" placeholder="请输入评论内容"></textarea><br>
		<button id="bt">发表评论</button>

	</div>
</div>
</body>
</html>
<script>
	$("#bt").click(function () {
		var name=$("#name").val();
		var content=$("#content").val();
            $.post('${prefix}/video?type=comment&name='+name+'&content='+content,function(result){
                //先解析  json格式转换 js数组
                var com=$.parseJSON(result);
                var str="";
                for(var i=0;i<com.length;i++){
                    str+="<p>"+com[i].name+"说:"+com[i].content+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+com[i].time+"</p>";
                }
                $("#com").html(str);

        })
	})  //获取、失去焦点事件
</script>