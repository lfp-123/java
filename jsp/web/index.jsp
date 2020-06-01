<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/12/10
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script src="/jsp/js/jquery-3.0.0.min.js" type="text/javascript"></script>
  </head>
  <body>
  <div><p style="font-size: 100px">呵呵</p></div>

  <input id="test"><span></span>
  </body>
</html>
<script>
  $(document).on("click","p",function () {
     var p = $(this).html();
      $("div").html("<input type = 'text' id='inputs' style='font-size: 100px' value='"+p+"'>");
  })
  $(document).on("blur","#inputs",function () {
      var p =$(this).html();
      $("div").html("<p>"+p+"</p>")
  })
//  $("#pinput").blur(function () { //失去焦点触发事件
//      var p =$(this).html();
//      $("div").html("<p>"+"</p>")
//  })
  $("#test").blur(function () {
      var p = $(this).html();
      $.post("/jsp/checkU","un="+p,function (result) {
          $("span").html(result)
          var parseJSON = $.parseJSON(result);
          for(var i=0;i<parseJSON.length;i++){
              $("span").html(parseJSON[i])
          }
          }

      )
  })

</script>
