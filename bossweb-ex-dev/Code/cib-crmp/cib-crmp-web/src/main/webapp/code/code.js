//模板页面复制
    function copy() {
        var copy = document.getElementById("text");
        copy.select();
        document.execCommand("Copy");
        alert("复制成功");
    }
//生成器
function buttonBuild() {
    var str = "";
    str += "<tr><th><input class=\"btn btn-primary btn-sm\" type=\"button\" id=\"push\" onclick=\"query()\" value=\"生成\"></th></tr>";
    $("#buttonBuild").html(str);
}
//全选方法
function selectAll() {
    var isCheck = $("#allcheckbox").is(':checked'); //获得全选复选框是否选中
    $("input[name='checkbox']").each(function(i) {
        this.checked = isCheck
    });


}
function selectAllid() {
    var isCheck = $("#allcheckboxid").is(':checked'); //获得全选复选框是否选中
    $("input[name='checkboxid']").each(function(i) {
        this.checked = isCheck
    });

}
//属性选择
function Data(data, type) {
    var str = "";
    var str2 = "";
    if (type == null) {
        type = "query";
    }
    if (type == "update" || type == "query") {
        str += "<tr><th style=\"background: #3F7CE7;color:#FFF;font-size:12px;text-align: center\">字段名"
            + "</th><th style=\"background: #3F7CE7;color:#FFF;font-size:12px;text-align: center\">属性"
            + "</th><th style=\"background: #3F7CE7;color:#FFF;font-size:12px;text-align: center\">"
            + "<label class=\"am-checkbox-inline\"><input type=\"checkbox\" id=\"allcheckbox\" onchange=\"selectAll()\" checked=\"checked\" data-am-ucheck/>值</label>"
            + "</th><th style=\"background: #3F7CE7;color:#FFF;font-size:12px;text-align: center\">"
            + "<label class=\"am-checkbox-inline\"><input type=\"checkbox\" id=\"allcheckboxid\" onchange=\"selectAllid()\" checked=\"checked\" data-am-ucheck/>主键</label></th></tr>";
        for (var i = 0; i < data.modellist.length; i++) {
            str += "<tr><td style=\"text-align: center\">" + data.modellist[i].name
                + "</td><td style=\"text-align: center\">" + data.modellist[i].type
                + "</td><td style=\"text-align: center\"><label class=\"am-checkbox-inline\">"
                + "<input type=\"checkbox\" name=\"checkbox\" value=\"" + data.modellist[i].name + "\" checked=\"checked\" data-am-ucheck/></label>"
                + "</td><td style=\"text-align: center\"><label class=\"am-checkbox-inline\">"
                + "<input type=\"checkbox\" name=\"checkboxid\" value=\"" + data.modellist[i].name + "\" checked=\"checked\" data-am-ucheck/></label></td></tr>";
        }
    } else if (type == "insert" || type == "delete" || type == "entity") {
        str += "<tr><th style=\"background: #3F7CE7;color:#FFF;font-size:12px;text-align: center\">字段名"
            + "</th><th style=\"background: #3F7CE7;color:#FFF;font-size:12px;text-align: center\">属性"
            + "</th><th style=\"background: #3F7CE7;color:#FFF;font-size:12px;text-align: center\">"
            + "<label class=\"am-checkbox-inline\"><input type=\"checkbox\" id=\"allcheckbox\" onchange=\"selectAll()\" checked=\"checked\" data-am-ucheck/>主键</label></th></tr>";
        for (var i = 0; i < data.modellist.length; i++) {
            str += "<tr><td style=\"text-align: center\">" + data.modellist[i].name
                + "</td><td style=\"text-align: center\">" + data.modellist[i].type
                + "</td><td style=\"text-align: center\"> <label class=\"am-checkbox-inline\">"
                + "<input  type=\"checkbox\" name=\"checkbox\" value=\"" + data.modellist[i].name + "\" checked=\"checked\" data-am-ucheck/></label>";
        }
    } else if (type == "entity") {
        str += "<tr><th style=\"background: #3F7CE7;color:#FFF;font-size:12px;text-align: center\">字段名"
            + "</th><th style=\"background: #3F7CE7;color:#FFF;font-size:12px;text-align: center\">属性"
            + "</th><th style=\"background: #3F7CE7;color:#FFF;font-size:12px;text-align: center\">"
            + "<label class=\"am-checkbox-inline\"><input type=\"checkbox\" id=\"allcheckbox\" onchange=\"selectAll()\" checked=\"checked\" data-am-ucheck/>值</label></th></tr>";
        for (var i = 0; i < data.modellist.length; i++) {
            str += "<tr><td style=\"text-align: center\">" + data.modellist[i].name
                + "</td><td style=\"text-align: center\">" + data.modellist[i].type
                + "</td><td style=\"text-align: center\"> <label class=\"am-checkbox-inline\">"
                + "<input  type=\"checkbox\" name=\"checkbox\" value=\"" + data.modellist[i].name + "\" checked=\"checked\" data-am-ucheck/></label>";
        }
    }
    str += "<input type=\"hidden\" name=\"tablename\" value=\"" + data.tablename + "\" checked=\"checked\" />"
    str += "</br>";

    $("#untab").html(str2);
    $("#tab").html(str);
}
//模板选择
function select() {
    var str = "";
    str += "<label class=\"am-radio-inline\">"
        + "<input type=\"radio\"  name=\"Type\" onclick=\"dataAjax()\" value=\"entity\" data-am-ucheck/>对象&nbsp&nbsp"
        + "</label>";
    str += "<label class=\"am-radio-inline\">"
        + "<input type=\"radio\"  name=\"Type\" onclick=\"dataAjax()\" value=\"query\" checked=\"checked\" data-am-ucheck/>查询&nbsp&nbsp"
        + "</label>";
    str += "<label class=\"am-radio-inline\">"
        + "<input type=\"radio\"  name=\"Type\" onclick=\"dataAjax()\" value=\"update\" data-am-ucheck/>更新&nbsp&nbsp"
        + "</label>";
    str += "<label class=\"am-radio-inline\">"
        + "<input type=\"radio\"  name=\"Type\" onclick=\"dataAjax()\" value=\"insert\" data-am-ucheck/>添加&nbsp&nbsp"
        + "</label>";
    str += "<label class=\"am-radio-inline\">"
        + "<input type=\"radio\"  name=\"Type\" onclick=\"dataAjax()\" value=\"delete\" data-am-ucheck/>删除&nbsp&nbsp"
        + "</label>";

    $("#undertab").html(str);
}
//模板展示页面
function show(data) {
    var str = "";
    str += "<input class=\"btn btn-primary btn-sm\" type=\"button\" onClick=\"copy()\" value=\"点击复制代码\" /><br>";
    str += "<div style=\"background:#F0F0F0;\">"
        + "<div class=\"queryArea am-form-group\">"
        + "<textarea   name=\"text\" id=\"text\" readonly=\" readonly\" class=\"source\">" + data.model + "</textarea>"
        + "</div></div>"
    //var str2 = "";
    //str2 += "<input class=\"btn btn-primary btn-sm\" type=\"button\" onClick=\"copy()\" value=\"点击复制代码\" />";
    //$("#buttonBuild").html(str2);
    $("#untab").html(str);
}
//生成模板查询
function query() {
    var checkID = [];
    $("input[name='checkbox']:checked").each(function(i) {
        checkID.push($(this).val());
    });

    var checkID2 = [];
    $("input[name='checkboxid']:checked").each(function(i) {
        checkID2.push($(this).val());
    });
    var tablename = $("input:hidden[name='tablename']").val();
    var type = $("input:radio[name='Type']:checked").val();
    $.ajax({
        url : "../rest/query",
        type : "POST",
        dataType : "json",
        contentType : 'application/json',
        data : JSON.stringify({
            "tablename" : tablename,
            "checkID" : checkID,
            "checkID2" : checkID2,
            "type" : type
        }),
        success : function(data) {

            show(data);
        },
        error : function(msg) {
            alert('AJAX连接异常', msg);
        }
    });
}
//表名查询ajax
function dataAjax() {
    var name = $('#name').val();
    var type = $("input:radio[name='Type']:checked").val();

    $.ajax({
        url : "../rest/getmodel",
        type : "POST",
        dataType : "json",
        data : {
            "name" : name
        },
        success : function(joArray) {
            if (type == null) {
                select();
            }
            if (joArray.msg != null) {
                alert(joArray.msg);
            }
            buttonBuild();
            Data(joArray, type);

        },
        error : function(msg) {
            alert('AJAX连接异常', msg);
        }
    });
}
function login() {
    var jdbcDriver = $("input:radio[name='jdbcDriver']:checked").val();
    var jdbcPort = $("input:text[name='jdbcPort']").val();
    var jdbcName = $("input:text[name='jdbcName']").val();
    var userName = $("input:text[name='userName']").val();
    var password = $("input:text[name='password']").val();
    if (jdbcPort == "" || userName == "" || jdbcName == "" ) {
        alert("不能为空");
    } else {
        $.ajax({
            url : "../rest/login",
            type : "POST",
            dataType : "json",
            data : {
                "jdbcDriver" : jdbcDriver,
                "jdbcPort" : jdbcPort,
                "jdbcName" : jdbcName,
                "userName" : userName,
                "password" : password
            },
            success : function(success) {
                indexWrite(success);
            },
            error : function(msg) {
                alert('AJAX连接异常', msg.msg);
            }
        });
    }

}
function index2() {
    $.ajax({
        url : "../rest/databaseName",
        type : "POST",
        dataType : "json",
        success : function(success) {
            console.log(success)
            indexWrite(success);
        },
        error : function(msg) {
            alert('AJAX连接异常', msg.msg);
        }
    });
}
function otherloginWrite() {
    var str = "";
    var str2 = "";
    str += "<span><h3>修改数据库连接设置</h3></span><br>"
        + "<input type=\"radio\" name=\"jdbcDriver\"  onclick=\"loginWrite()\" value=\"OracleSID\">&nbspOracleSID&nbsp"
        + "<input type=\"radio\" name=\"jdbcDriver\" onclick=\"loginWrite()\" value=\"OracleServiceName\">&nbspOracleServiceName&nbsp"
        + "<input type=\"radio\" name=\"jdbcDriver\" onclick=\"loginWrite()\" value=\"Altibase\">&nbspAltibase&nbsp"
        + "<input type=\"radio\" name=\"jdbcDriver\" onclick=\"otherloginWrite()\" value=\"TimeTen\" checked=\"checked\">&nbspTimeTen&nbsp"
        + "<input type=\"radio\" name=\"jdbcDriver\" value=\"MySQL\">&nbspMySQL&nbsp</br></br>"
        + "<input type=\"radio\" name=\"jdbcPort\" value=\"client\" checked=\"checked\">&nbsp本地连接&nbsp"
        + "<input type=\"radio\" name=\"jdbcPort\" value=\"direct \">&nbsp远程连接&nbsp</br></br>"
        + "数据库名：<input type=\"text\" name=\"jdbcName\"></br></br>"
        + "用户名&nbsp&nbsp&nbsp&nbsp：<input type=\"text\" name=\"userName\"></br></br>"
        + "密码&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp：<input type=\"text\" name=\"password\"></br></br>"
        + "<input class=\"btn btn-primary btn-sm\" type=\"button\" value=\"连接\" onclick=\"login()\">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
        + "<input class=\"btn btn-primary btn-sm\" type=\"button\" value=\"返回\" onclick=\"index2()\">"

    $("#tab").html(str2);
    $("#untab").html(str2);
    $("#login").html(str2);
    $("#content").html(str);
}
function loginWrite() {
    var str = "";
    var str2 = "";
    str += "<span><h3>修改数据库连接设置</h3></span><br>"
        + "<input type=\"radio\" name=\"jdbcDriver\" value=\"OracleSID\" checked=\"checked\">&nbspOracleSID&nbsp"
        + "<input type=\"radio\" name=\"jdbcDriver\" value=\"OracleServiceName\">&nbspOracleServiceName&nbsp"
        + "<input type=\"radio\" name=\"jdbcDriver\" value=\"Altibase\">&nbspAltibase&nbsp"
        + "<input type=\"radio\" name=\"jdbcDriver\" onclick=\"otherloginWrite()\" value=\"TimeTen\">&nbspTimeTen&nbsp"
        + "<input type=\"radio\" name=\"jdbcDriver\" value=\"MySQL\">&nbspMySQL&nbsp</br></br>"
        + "主机端口：<input type=\"text\" name=\"jdbcPort\"></br></br>"
        + "数据库名：<input type=\"text\" name=\"jdbcName\"></br></br>"
        + "用户名&nbsp&nbsp&nbsp&nbsp：<input type=\"text\" name=\"userName\"></br></br>"
        + "密码&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp：<input type=\"text\" name=\"password\"></br></br>"
        + "<input class=\"btn btn-primary btn-sm\" type=\"button\" value=\"连接\" onclick=\"login()\">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
        + "<input class=\"btn btn-primary btn-sm\" type=\"button\" value=\"返回\" onclick=\"index2()\">"

    $("#tab").html(str2);
    $("#untab").html(str2);
    $("#login").html(str2);
    $("#content").html(str);
}
function indexWrite(data) {
    var str = "";
    var str2 = "";
    var str3 = "";
    var str4 = ";"
    str2 = "<input title=\"注意：重新设置后第一次查询表数据会比较缓慢，请谨慎！\" class=\"btn btn-primary btn-sm\" type=\"button\" onclick=\"loginWrite()\" value=\"&nbsp&nbsp更改数据库连接&nbsp&nbsp\">";
    str4 = "<input title=\"退出连接\" class=\"btn btn-primary btn-sm\" type=\"button\" onclick=\"out()\" value=\"退出连接\">";
    if (data.name == "" && data.port == "") {
        str3 += "<span><h4>当前暂无数据库连接</h4></span><br>"
        str4 = "";
    } else {
        str3 += "<span><h4>当前数据库：</h4></span><br>"
        str3 += "<span><h6>端口：&nbsp&nbsp" + data.port + "</h6></span><br>"
        str3 += "<span><h6>数据库名：&nbsp" + data.name + "&nbsp&nbsp&nbsp&nbsp用户名：&nbsp" + data.username + "</h6></span>"
    }
    str += "<input type=\"text\" id=\"name\">&nbsp"
        + "<input class=\"btn btn-primary btn-sm\" type=\"button\" onclick=\"dataAjax()\" value=\"查询\">"
        + "</br> </br> <span id=\"undertab\"></span> <span id=\"buttonBuild\"></span>"

    $("#out").html(str4);
    $("#dataName").html(str3);
    $("#login").html(str2);
    $("#content").html(str);
}
$(document).ready(function index() {
    $.ajax({
        url : "../rest/databaseName",
        type : "POST",
        dataType : "json",
        success : function(success) {
            console.log(success)
            indexWrite(success);
        },
        error : function(msg) {
            alert('AJAX连接异常', msg.msg);
        }
    });



})

function out() {
    $.ajax({
        url : "../rest/login",
        type : "POST",
        dataType : "json",
        data : {
            "jdbcDriver" : "",
            "jdbcPort" : "",
            "jdbcName" : "",
            "userName" : "",
            "password" : ""
        },
        success : function(success) {
            indexWrite(success);
        },
        error : function(msg) {
            alert('AJAX连接异常', msg.msg);
        }
    });


}
window.onbeforeunload =onbeforeunload_handler;
window.onunload = onunload_handler;
function onbeforeunload_handler(){
    out();
}
function onunload_handler(){
    out();
}
