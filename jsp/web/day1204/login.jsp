<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/3
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!--
action 数据提交服务器的位置
method 请求方式 get/post
get/post区别
enctype 是传值方式 application/x-www-form-urlencoded默认值代表传的值都是String
                    multipart/form-data 代表传递的附件形式
-->
<form action="shows.jsp" method="get" enctype="application/x-www-form-urlencoded">
    <fieldset style="width: 300px;">
        <legend>登陆</legend>
      <p><label for="un">用户: </label><input id="un" name ="username"type="text" ></p>
      <p><label for="uw">密码: </label><input id="uw" name="password" type="password"></p>
        <p>性别：
            <input type="radio" name = "gender" value="男">男
            <input type="radio" name = "gender" value="女">女
        </p>
        <p>
            爱好：
            <Input type="checkbox" name="ints"value="篮球">篮球
            <Input type="checkbox" name="ints"value="足球">足球
            <Input type="checkbox" name="ints"value="英语">英语
            <Input type="checkbox" name="ints"value="数学">数学

        </p>
        <p>出生年月： <input type="date" name = "dates" value=""> </p>
        <p>祖籍：
            <select name="address" values="">
                <option>江西</option>
                <option>广东</option>
                <option>上海</option>
                <option>南京</option>
            </select>
        </p>
        <p>
            上传头像
            <input type="file" name="" id="">
        </p>
        <p>隐藏域<input type="hidden" value="指定的id" name="hi"></p>
        <p>
            <textarea name="bz">默认值请任意填写。。。。。</textarea>
        </p>

        <p>
            <input type="button" value="普通按钮">   <!-- 普通按钮 用于自定义功能 一般用于自定义功能实现 -->
            <input type="reset" name="" value="重置按钮">
            <input type="submit" name="" value="提交">
        </p>
        <a href="shows.jsp? username= 100&password = 122233&ints =篮球& address=江西 " > 传递id</a>
    </fieldset>

</form>

</body>
</html>
<script>

</script>