<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/25
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
<head>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <title>Title</title>
</head>
<body>
<h2> ${u.username}登陆成功！！！</h2>
<h2>展示用户信息</h2>
<a href="showDept.action">新增用户</a>
<table style="font-size: 30px" cellpadding="5" cellspacing="0" border="1px">
    <tr>
        <td>全选<input type="checkbox" id="all" onclick="checkAll()"></td>
        <td>编号</td>
        <td>用户</td>
        <td>密码</td>
        <td>时间</td>
        <td>性别</td>
        <td>年龄</td>
        <td>部门</td>
        <td>备注</td>
        <td>操作</td>
    </tr>
    <form action ="delAll.action" onclick="" method="post" id="users">
<c:forEach var="u" items="${list}">
    <tr>
        <td><input type="checkbox" name="ck"  value="${u.id}" ></td>
         <td>${u.id}</td>
        <td><a href="showOneUser.action?id=${u.id}"> ${u.username}</a></td>
        <td>${u.password}</td>
        <%--<td>***${f:substring(u.password,f:length(u.password-3),f:length(u.password.length))}</td>--%>
        <td>${u.createtime}</td>
        <td>
            <c:if test="${u.info.sex==1}">男</c:if>
            <c:if test="${u.info.sex==0}">女</c:if>
        </td>
        <td>${u.info.age}</td>
        <td>${u.dept.deptname}</td>
        <td>${u.info.mess}</td>
        <td><a href="del.action?id=${u.id}" onclick="return del()">删除</a> </td>
    </tr>
</c:forEach>
        <input type="submit" value="删除勾选" >
    </form>
</table>
<a href="showUser.action?pageIndex=${pageIndex-1}">上一页</a>
<a href="showUser.action?pageIndex=${pageIndex+1}">下一页</a>
</body>
</html>
<script>
    function del() {
        return confirm("确定删除吗？")
    }
    function checkAll() {
    var all = document.getElementById("all");
    var cks = document.getElementsByName("ck");
        for(var i=0;i<cks.length;i++){
            cks[i].checked = all.checked;
        }
    }



   

</script>
