<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/jsp/js/jquery-3.0.0.min.js" type="text/javascript"></script>
</head>
<body>
<input type="text" value="1">
<input type="text" class="c1" value="2">
<input type="text" id="d1" value="3">
<p class="c1">段落</p>
<ul>
    <li>测试</li>
    <li>测试</li>
    <li>
        <p id="d2">段落标签</p>
        <p>段落<b class="c1">dafasadasdv</b>标签</p>
    </li>
    <li>测试</li>
    <li>测试</li>
    <li>测试</li>
</ul>
</body>
</html>
<script>
    //    jquery事件的基本语法
    //    $(选择器).事件名(匿名函数)
    //    并集: 选择器1,选择器2
    //    交集: 取元素的公共部分,由两部分组成:第一部分一定是标签选择器,第二部分可以是类可以是ID选择器
    //属性下所有后代通过"  "空格相连
    //     li .c1 :获取li元素下的所有后代标签
    //属性该符号是[]
    // $("input[value='text']"):选择的是input下属性是type值是text的元素
    //        $("input[value=*'abc']"):选择的是input下属性是value值包含abc的元素
    //            //过滤选择器通过 ":" 相连
    //            :first获取第一一个
    //            :last最后一个
    //            :odd索引只是奇数
    //            :even索引值是偶数
    //            :eq()获取指定索引值的元素
    //            :gt()获取大于索引值的元素
    //            :lt()获取小于索引值
    //            :ge()
    //            :le()

    //input标签下 类是c1的元素(交集)
    $("li").click(function (){
        //事件触发的内容
//    js写法    document.getElementById("text1").value
//    jquery写法    $("text1").val();
        //    js写法    document.getElementById("text1").innerHTML
//    jquery写法    $("text1").html();
        //如果想获取当前元素下的元素内容,通过当前对象去.html()或者.val()
        $("li:odd").css("border", "10px solid red")
        // alert($(this.val()));
        //alert("哈哈哈");
        $("li:odd").css("color", "red");
        $("li:even").css("color", "green");
    });
</script>