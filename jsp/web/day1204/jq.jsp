<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/04
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jquery</title>

</head>
<body>
<input type="text">
<input type="text" class="c1" id="text1">
<input type="text" id="d1">
<p class="c1">段落</p>
<script>
    /*
    1  <input type="text">
    2  <input type="text" class="c1">
    3  <input type="text" id="d1">
    4  <p class="c1">段落</p>

    并集：选择器1，选择器2，....
    交集：取元素的公共部分 由两部分构成 第一部分一定标签选择器 第二部分可以类也可以是id选择器
    input.c1  获取input中的c1类标签
    子代后代：元素下所有后代元素  通过" "相连  li .c1  获取li元素所有class是c1的后代元素
    $("input[type='text']")  选择的是input下属性是type 值是text元素
    $("input[value*='abc']")  选择的是input下属性是value 值包含abc元素
    过滤：通过“:”相连  可以通过索引值(下标从0开始)获取元素
    :first
    :last
    :odd    获取奇数
    :even   获取偶数
    :eq()   获取指定索引值的元素
    :gt()   获取大于索引值
    :lt()   获取小于索引值
    * */
    $("input.c1").click(function () {
        $(this).css("border", "10px solid red");
    })
</script>
</body>
</html>
