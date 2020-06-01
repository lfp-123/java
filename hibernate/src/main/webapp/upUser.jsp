<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="upUser.action?id=${ou.id}" method="post" onsubmit="">
    <fieldset>
        <legend>用户${ou.username}进入修改信息页面</legend>
        <p>*用户:<input type="text" name="u.username" value="${ou.username}"></p>
        <p>*密码:<input type="password" name="u.password" value="${ou.password}"></p>
        <p>*性别:
            <input type="radio" value="1" name="u.info.sex" <c:if test="${ou.info.sex==1}">checked="checked"</c:if>>男
            <input type="radio" value="0" name="u.info.sex"<c:if test="${ou.info.sex==0}">checked="checked"</c:if>>女
        </p>
        <p>*年龄:<input type="text" name="u.info.age" value="${ou.info.age}" ></p>
        <p>备注:<textarea name="info.mess">${ou.info.mess}</textarea></p>
        <p>请选择部门:
            <select name="u.dept.id">
                <c:forEach items="${listDept}" var="d">
                    <option <c:if test="${ou.dept.id}==${d.id}">selected="selected"</c:if> value="${d.id}"</option> ${d.deptname}</option>
                </c:forEach>
            </select>
        </p>
        <p><input type="submit" value="add"></p>
    </fieldset>
</form>
</body>
</html>
