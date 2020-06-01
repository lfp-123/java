<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/16
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/jsp/js/jquery-3.0.0.min.js" type="text/javascript"></script>
</head>
<body>
<form action="${prefix}/register" method="post" enctype="multipart/form-data">
    <div id ="mess"></div>
    <p>用户:<input type="text" name="un"></p>
    <p>密码:<input type="password" name="pw"></p>
    <p>上传头像:<input type="file" name="myFile"></p>
    <p><input type="submit" value="注册"></p>
</form>
<button>异步请求</button>
<div>

</div>
</body>
</html>
<script>
    $("input[type='text']").blur(function () {
        var un = $(this).val();
        $.ajax({
            type:"get", //指定请求方式
            url:"${prefix}/ajax",  //指定请求地址
            data:"un="+un, //指定传递的数据 &id=10 拼接数据
            dataType:"text",  //指定返回的数据类型 text json
            success:function (result) {
                if(result=='用户名已存在！'){
                    $("#mess").css("color", "red").html(result)
                    //请求成功之后所执行的函数，也称为回调函数，参数代表请求响应结果
                }else {
                    $("#mess").css("color", "green").html(result)
                }

            },
            error:function () {
                //请求失败所执行的函数
                alert("请求失败！！")
            }
        })
    })  //获取、失去焦点事件
    ${"button"}.click(function () {
        alert("1111111")
        $.post('${prefix}/json',function (result) {
            alert("11111111");
        })
    })
</script>
