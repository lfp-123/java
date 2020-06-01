<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>
<h2>Hello World!</h2>

<form action="/mvc/register" method="post">
    <p>账号：<input type="text" name="username"></p>
    <p>密码：<input type="password" name="password"></p>
    <P>爱好：<br>
        <input type="checkbox" name="ck" value="甜美">甜美</P>
    <input type="checkbox" name="ck" value="少妇">少妇</P>
    <input type="checkbox" name="ck" value="御姐">御姐</P>
    <input type="checkbox" name="ck" value="萝莉">萝莉</P>
    <p><input type="submit"></p>
</form>
</body>
</html>
