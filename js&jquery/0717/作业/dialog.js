$(document).ready(function(){
    function success(content,align,valign) {
        var align1="",align2="";
        if(valign == "top") align2 += "top:0px;";
        if(valign == "bottom") align2 += "top:calc(100% - 48px);";

        if(align == "left") align1 = "style='left:0px;"+align2+"'";
        if(align == "right") align1 = "style='left:calc(100% - 250px);"+align2+"'";
        if(align == "center") align1 = align2;
        var arr = new Array();
        //var content = "这是一则成功消息";
        arr.push('<div class="dialog_success_window">');
        arr.push('<span class="dialog_success_logo">i</span>');
        arr.push(content);
        arr.push('<a href="javascript:;" class="dialog_close_logo">×</a>');
        arr.push('</div>');
        return arr.join("");
    }
    $(".dialog_Info").click(function(){
        if($(".dialog_success_window").length>0) return;
        var content = $(this).attr("content");
        var str = success(content);

        var align = $(this).attr("dialog_align");
        var valign = $(this).attr("dialog_valign");
        var str = success(content,align,valign);

        $("body").append(str)
        //三秒后自动删除
        setTimeout(function () {
            $(".dialog_close_logo").click();},3000);


    });
    $(document).on("click",".dialog_close_logo", function(){
        $(".dialog_success_window").fadeOut(500,function(){
            $(".dialog_success_window").remove();
        });
    });
});