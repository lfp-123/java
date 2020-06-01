<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/4
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Title</title>
</head>
<body>
<input type = "text"  value="1">
<input type = "text" class= "cl" value="2">
<input type = "text" id = "cl" value="3">
<p id= " cl"> 段落</p>
<ul>
    <li>是是是是</li>
    <li>是是是是</li>
    <li>是是是是</li>
    <li>是是是是</li>
    <li>是是是是</li>
    <li>是是是是</li>
</ul>
</body>
<script>
    /*
 jquery事件的基本语法
 $(选择器).事件名（匿名函数）
 选择器 类似css  id        类      标签
               #id 名称  .类名   标签名
               并集 选择器1，选择器2...
              交集获取元素的公共部分 由两部分组成 第一部分一定是标签选择器 第二部分可以类可以是id选择器
            $ 等价于jQuery
  */

    $(".cl").click(function (){
        alert("11111");
        alert($(this).val());
    })
    $("li").onclick(function (){
       $(this).css("border","1px solid red");
    })

</script>
</html>
