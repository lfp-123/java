<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/hib/addUser.action" method="post" onsubmit="">
    <fieldset>
        <legend>新增用户-*为必填项</legend>
        <p>*用户:<input type="text" name="u.username"></p>
        <p>*密码:<input type="password" name="u.password"></p>
        <p>*性别:
            <input type="radio" value="1">男
            <input type="radio" value="0">女
        </p>
        <p>*年龄:<input type="text" ></p>
        <p>备注:<textarea>请填写备注...可选填</textarea></p>
        <p>请选择部门:
            <select name="u.dept.id">
                <c:forEach items="${listDept}" var="d">
                    <option value="${d.id}">${d.deptname}</option>
                </c:forEach>
            </select>
        </p>
        <p><input type="submit" value="add"></p>
    </fieldset>
</form>
</body>
</html>

