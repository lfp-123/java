<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/1/8
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/mvc/resources/js/jquery-3.0.0.min.js" type="text/javascript"></script>
</head>
<body>
<form action="/mvc/user/register" method="post" enctype="multipart/form-data">
    <fieldset>
        <legend>注册</legend>

    <p>账号：<input type="text" id="username" name="username"><div id="mess" style="color: red"></div></p>
    <p>密码：<input type="password" id="password" name="password"></p>
    <p>上传头像：<input type="file" name="imageFile"></p>
    <p><input type="submit" value="注册"></p>
    </fieldset>
</form>
</body>
</html>
<script>
    //失去焦点事件
    $("#username").blur(function () {
       var username =  $(this).val();

        $.post('/mvc/user/checkUser','username='+username,function (result) {
          //  console.log(result);
            $("#mess").html(result);

        })
    })
    //返回json格式
    $("#password").blur(function () {
       var username =  $(this).val();
        $.post('/mvc/user/checkUser2','username='+username,function (result) {
            console.log(result)
            var str="";
            for(var i=0;i<result.length;i++){
                str+=result[i].id+"--"+result[i].username+"--"+result[i].password+"--";
            }
            $("#mess").html(str);

        })
    })
</script>
