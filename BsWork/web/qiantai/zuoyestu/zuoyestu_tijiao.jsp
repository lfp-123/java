<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <LINK href="<%=path %>/css/css.css" type=text/css rel=stylesheet>
    <script type="text/javascript" src="<%=path %>/js/popup.js"></script>
    <script type="text/javascript">
            function up()
		    {
		        var pop=new Popup({ contentType:1,isReloadOnClose:false,width:400,height:200});
	            pop.setContent("contentUrl","<%=path %>/upload/upload.jsp");
	            pop.setContent("title","文件上传");
	            pop.build();
	            pop.show();
		    }
		    
		   function check11()
           {
               if(document.formAdd.mingcheng.value=="")
               { 
                   alert("请输入作业名称");
                   return false;
               }
               if(document.formAdd.fujian.value=="")
               { 
                   alert("请上传作业附件");
                   return false;
               }
               document.formAdd.submit();
           }
    </script>
  </head>
  
  <BODY text=#000000  leftMargin=0 topMargin=0>
	<div class="wrap"> 
		<TABLE  cellSpacing=0 cellPadding=0 width="100%" align=center border=0  background="<%=path %>/img/reservation01.gif">
				<TR height="90">
					<TD align="center">
					    <jsp:include flush="true" page="/qiantai/inc/incTop1.jsp"></jsp:include> 
					</TD>
				</TR>
		</TABLE>
		
		
		<TABLE id=guide cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
				<TR>
					<TD align="left">
						<jsp:include flush="true" page="/qiantai/inc/incTop2.jsp"></jsp:include>
					</TD>
				</TR>
		</TABLE>

        <TABLE class=MainTable style="MARGIN-TOP: 0px" cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
				<TR>
				    <TD class=Side vAlign=top align=right width="25%">
						<jsp:include flush="true" page="/qiantai/inc/incLeft.jsp"></jsp:include>
					</TD>
					<td width="1">&nbsp;</td>
					<TD class=Side vAlign=top align=right width="75%">
						<TABLE class=dragTable cellSpacing=0 cellPadding=0 width="100%" border=0>
								<TR>
									<TD class=head>
										<SPAN class=TAG>作业提交</SPAN>
									</TD>
								</TR>
								<TR align="left">
									<TD height="5"></TD>
								</TR>
								<TR align="left" height="500">
									<TD>
									    <form action="<%=path %>/zuoyestu?type=zuoyestu_tijiao" name="formAdd" method="post">
									    <table width="99%" border="0" cellpadding="9" cellspacing="9">
								                <tr align='center'>
												    <td width="15%" bgcolor="#FFFFFF" align="center">
												        作业名称：
												    </td>
												    <td width="85%" bgcolor="#FFFFFF" align="left">
												        <input type="text" name="mingcheng" size="40"/>
												    </td>
												</tr>
												<tr align='center'>
												    <td width="15%" bgcolor="#FFFFFF" align="center">
												       作业附件：
												    </td>
												    <td width="85%" bgcolor="#FFFFFF" align="left">
												        <input type="text" name="fujian" id="fujian" size="30" readonly="readonly"/>
												        <input type="button" value="上传" onclick="up()"/>
												        <input type="hidden" name="fujianYuanshiming" id="fujianYuanshiming"/>
												    </td>
												</tr>
												<tr align='center'>
												    <td width="15%" bgcolor="#FFFFFF" align="center">
												        &nbsp;
												    </td>
												    <td width="85%" bgcolor="#FFFFFF" align="left">
												       <input type="button" value="提交" onclick="check11()"/>&nbsp; 
												       <input type="reset" value="重置"/>&nbsp;
												    </td>
												</tr>
				        			    </table>
				        			    </form>
									</TD>
								</TR>
								<TR align="left">
									<TD height="5"></TD>
								</TR>
						</TABLE>
					</TD>
				</TR>
		</TABLE>
		<jsp:include flush="true" page="/qiantai/inc/incFoot.jsp"></jsp:include>
    </div>
  </BODY>
</html>
