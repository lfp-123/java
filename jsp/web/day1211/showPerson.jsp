<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/11
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<script src="/jsp/js/jquery-3.0.0.min.js" type="text/javascript"></script>
<head>
    <title>Title</title>
</head>
<body>
    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <td>编号</td>
            <td>姓名</td>
            <td>密码</td>
        </tr>
        <c:forEach var="p" items="${ps}">
            <tr>
                <td>${p.id}</td>
                <td>${p.username}</td>
                <td>${p.password}</td>
            </tr>
        </c:forEach>
    </table>
    [当前页：${pageIndex}/总页数：${totalPage}]
    <c:if test="${pageIndex gt 1}">
    <a href="show?pageIndex=1">首页</a>
    <a href="/jsp/show?pageIndex=${pageIndex-1}">上一页</a>
    </c:if>
    <c:if test="${pageIndex lt  totalPage}">
        <a href="/jsp/show?pageIndex=${pageIndex+1}">下一页</a>
        <a href="/jsp/show?pageIndex=${totalPage}">尾页</a>
    </c:if>

<input type="text"size="2"id="page">页<button >跳转</button>
    切换每页条数（默认为5）
<select>
    <option value="5" <c:if test="${pageCount eq 5}">selected</c:if>  >5</option>
    <option value="10" <c:if test="${pageCount eq 10}">selected</c:if> >10</option>
    <option value="15" <c:if test="${pageCount eq 15}">selected</c:if> >15</option>
    <option value="20" <c:if test="${pageCount eq 20}">selected</c:if> >20</option>
</select>
<script>
    $("button").click(function () {
        var page=$("#page").val();
        if(page<=0){
            page =1;
        }
        if(page>${totalPage}){
                page = ${totalPage};
        }
        location.href='/jsp/show?pageIndex='+page;
    })
    $("select").change(function () {
        var count = $(this).val();
        location.href='/jsp/show?pageCount='+count;
    })
</script>
</body>
</html>
