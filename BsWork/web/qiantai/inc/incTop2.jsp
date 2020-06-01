<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <script type="text/javascript">
         function admin()
         {
            var url="<%=path %>/login.jsp";
            window.open(url,"_blank");
         } 
                  
         function zuoyestu_tijiao()
         {
            <c:if test="${sessionScope.user==null}">
                  alert("请先登录");
            </c:if>
            <c:if test="${sessionScope.user!=null && sessionScope.user.type==2}">
                  var url="<%=path %>/qiantai/zuoyestu/zuoyestu_tijiao.jsp";
                  window.location.href=url;
            </c:if>
         }
         
         function zuoyestu_mine()
         {
            <c:if test="${sessionScope.user==null}">
                  alert("请先登录");
            </c:if>
            <c:if test="${sessionScope.user!=null && sessionScope.user.type==2}">
                  var url="<%=path %>/zuoyestu?type=zuoyestu_mine";
                  window.location.href=url;
            </c:if>
         }
         
         
         function liuyanAll()
         {
            <c:if test="${sessionScope.userType==null}">
                  alert("请先登录");
            </c:if>
            
            <c:if test="${sessionScope.userType!=null}">
                var url="<%=path %>/liuyan?type=liuyanAll";
				var targetWinName="newWin";
				var features="width="+screen.width-200+" ,height="+screen.height-150+" ,toolbar=no, top=0, left=0, menubar=no, scrollbars=no, resizable=no,location=no, status=no"
				var new_win=window.open(url,targetWinName,features);
            </c:if>
         } 
      </script>
  </head>
  
  <body >
       <A  href="<%=path %>/qiantai/default.jsp">首 页</A> &nbsp;&nbsp;
       
       <c:if test="${sessionScope.userType!=null && sessionScope.userType==1}">
       <a href="<%=path %>/qiantai/zuoye/zuoye_fabu.jsp">发布作业</A> &nbsp;&nbsp;
       <a href="<%=path %>/zuoye?type=zuoye_mana">作业管理</A> &nbsp;&nbsp;
       <a href="<%=path %>/zuoyestu?type=zuoyestu_all">批阅作业</A> &nbsp;&nbsp;
       </c:if>
       
       
       <c:if test="${sessionScope.userType!=null && sessionScope.userType==2}">
       <a href="<%=path %>/zuoye?type=zuoye_all">下载作业</A> &nbsp;&nbsp;
       <a href="<%=path %>/zuoyestu?type=zuoyestu_mime">我的作业</A> &nbsp;&nbsp;
       <a href="<%=path %>/qiantai/zuoyestu/zuoyestu_tijiao.jsp">提交作业</A> &nbsp;&nbsp;
       </c:if>
       
       <A href="<%=path %>/doc?type=docAll">教学资料</A> &nbsp;&nbsp;
       <a href="#" onclick="liuyanAll()">网站留言板</A> &nbsp;&nbsp;
	   <a href="#" onclick="admin()">后台管理</a> &nbsp;&nbsp;
  </body>
</html>
