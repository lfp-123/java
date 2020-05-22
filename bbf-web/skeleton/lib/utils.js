function showAlert(falg, message){
	var title = '消息提示';
	if (falg == 'FAILED') {
		title = '操作错误';
	}
	if (falg == 'SUCCESSFUL') {
		title = '操作成功';
	}
	if (falg == 'ERROR') {
		title = '系统错误';
	}
	if (falg == 'WARNING') {
		title = '警告';
	}
	if (falg == 'INFO') {
		title = '信息';
	};
	var text =  '<div class="am-modal am-modal-alert" tabindex="-1" id="_myAlert">'+
				'  <div class="am-modal-dialog">'+
				'    <div class="am-modal-hd"> ' + title + ' </div>'+
				'    <div class="am-modal-bd">'+
				'      ' + message +
				'    </div>'+
				'    <div class="am-modal-footer">'+
				'      <span class="am-modal-btn _modal-close">确定</span>'+
				'    </div>'+
				'  </div>'+
				'</div>';
	$('body').append(text);	 
	$('._modal-close').click(function() { 
		$('#_myAlert').modal('close');
		$('#_myAlert').remove();
	});
	$('#_myAlert').modal({relatedTarget: this, closeViaDimmer: 0});
}

// 加载等待
function showLoding(falg, message){
	if (message == null || typeof(message) == 'undefined') {
		message = '正在载入';
	}

	var text =  '<div class="am-modal am-modal-loading am-modal-no-btn" tabindex="-1" id="_myLoading">'+
				'  	<div class="am-modal-dialog">'+
				'    	<div class="am-modal-hd">'+message+'</div>'+
				'    	<div class="am-modal-bd">'+
				'      		<span class="am-icon-spinner am-icon-spin"></span>'+
				'    	</div>'+
				'  	</div>'+
				'</div>';

	if (falg) {
		$('body').append(text);	 
		$('#_myLoading').modal({relatedTarget: this, closeViaDimmer: 0});
	}else{
		$('#_myLoading').modal('close');
		$('#_myLoading').remove();
	}
}

//页面跳转
function forwardLocation(url){
	window.location.href = url;
}


//MAP 转 list
function MapToList(map){
	var list = new Array();
	$.each(map, function(key, value){
		var obj = new Object();
		obj.key = key;
		obj.value = value;
		list.push(obj);
	});
	return list;
}

var WEB_ROOT_URL = getWebRootDir(),//项目部署主路径
base_url = getRootDir(); //相对路径

var ESCAPE_APOS = "QADEV_ESCAPE_APOS";
var ESCAPE_QUOT = "QADEV_ESCAPE_QUOT";
var ESCAPE_LT = "QADEV_ESCAPE_LT";
var ESCAPE_GT = "QADEV_ESCAPE_GT";
var ESCAPE_AMP = "QADEV_ESCAPE_AMP";



String.format = function (src) 
{
if (arguments.length == 0) return null;
var args = Array.prototype.slice.call(arguments, 1);
return src.replace(/\{(\d+)\}/g, function (m, i) {
    return args[i];
});
};

//设置时间值
function setTimeValue(timeSelectId, minTime, maxTime,choosedArr)
{
var $timeNode = $("#" + timeSelectId);
$timeNode.empty();

for(var i = minTime ; i <= maxTime; i ++ )
{
	if(inArrayIndex(i,choosedArr)>=0)
		$timeNode.append("<option value='"+i+"' selected='selected'>"+i+"</option>");
	else
		$timeNode.append("<option value='"+i+"'>"+i+"</option>");
}
}

//map 类
var Map = function(){  

 /** Map 大小 **/
var size = 0;
/** 对象 **/
var entry = new Object();

/** 存 **/
this.put = function (key , value)
{
  if(!this.containsKey(key))
  {
      size ++ ;
  }
  entry[key] = value;
};

/** 取 **/
this.get = function (key)
{
  if( this.containsKey(key) )
  {
      return entry[key];
  }
  else
  {
      return null;
  }
};

/** 删除 **/
this.remove = function ( key )
{
  if( delete entry[key] )
  {
      size --;
  }
};

/** 是否包含 Key **/
this.containsKey = function ( key )
{
  return (key in entry);
};

/** 是否包含 Value **/
this.containsValue = function ( value )
{
  for(var prop in entry)
  {
      if(entry[prop] == value)
      {
          return true;
      }
  }
  return false;
};

/** 所有 Value **/
this.values = function ()
{
  var values = new Array(size);
  for(var prop in entry)
  {
      values.push(entry[prop]);
  }
  return values;
};

/** 所有 Key **/
this.keys = function ()
{
  var keys = new Array(size);
  for(var prop in entry)
  {
      keys.push(prop);
  }
  return keys;
};

/** Map Size **/
this.size = function ()
{
  return size;
};

this.toJson = function(){
	var json = "{";
  	for(var item in entry){  
	  	json += "\"" + item + "\":\"" + entry[item] + "\",";
  	}   
  	if(json.indexOf(",") != -1)
	  	json = json.substring(0,json.length -1);
  	json += "}";
  	return json;
};
};

/**
* 对table 或 select 下的选项进行搜索
* searchId : table中tbody或select Id
* searchContent:要搜索的tr或者option
* value:搜索的值
*/
function searchItem(searchId, searchContent,value)
{
if(!value)  //搜索值为空全部显示
	$("#" + searchId + ">" + searchContent).show();
else{
	value = value.toLowerCase();
	$.each($("#" + searchId + ">" + searchContent),function(index,node){
		if($(node).text() && $(node).text().toLowerCase().indexOf(value) != -1)
			$(node).show();
		else
			$(node).hide();
	});
}
}

//解决ie下不能使用Object.keys方法
Object.keys = Object.keys || (function () {
var hasOwnProperty = Object.prototype.hasOwnProperty,
    hasDontEnumBug = !{toString:null}.propertyIsEnumerable("toString"),
    DontEnums = [
        'toString',
        'toLocaleString',
        'valueOf',
        'hasOwnProperty',
        'isPrototypeOf',
        'propertyIsEnumerable',
        'constructor'
    ],
    DontEnumsLength = DontEnums.length;

return function (o) {
    if (typeof o != "object" && typeof o != "function" || o === null)
        throw new TypeError("Object.keys called on a non-object");
 
    var result = [];
    for (var name in o) {
        if (hasOwnProperty.call(o, name))
            result.push(name);
    }
 
    if (hasDontEnumBug) {
        for (var i = 0; i < DontEnumsLength; i++) {
            if (hasOwnProperty.call(o, DontEnums[i]))
                result.push(DontEnums[i]);
        }   
    }
 
    return result;
};
})();

//阻止浏览器的默认行为 
function stopDefault( e ) { 
if (e&&e.preventDefault )//IE中阻止函数器默认动作的方式  
    e.preventDefault(); 
else
    window.event.returnValue = false; 
return false; 
}

if( document.implementation.hasFeature("XPath", "3.0") )
{
// prototying the XMLDocument
XMLDocument.prototype.selectNodes = function(cXPathString, xNode)
{
  if( !xNode ) { xNode = this; }
  var oNSResolver = this.createNSResolver(this.documentElement)
  var aItems = this.evaluate(cXPathString, xNode, oNSResolver,
               XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null)
  var aResult = [];
  for( var i = 0; i < aItems.snapshotLength; i++)
  {
     aResult[i] =  aItems.snapshotItem(i);
  }
  return aResult;
}

// prototying the Element
Element.prototype.selectNodes = function(cXPathString)
{
  if(this.ownerDocument.selectNodes)
  {
     return this.ownerDocument.selectNodes(cXPathString, this);
  }
  else{throw "For XML Elements Only";}
}

//prototying the XMLDocument
XMLDocument.prototype.selectSingleNode = function(cXPathString, xNode)
{
  if( !xNode ) { xNode = this; }
  var xItems = this.selectNodes(cXPathString, xNode);
  if( xItems.length > 0 )
  {
     return xItems[0];
  }
  else
  {
     return null;
  }
}

// prototying the Element
Element.prototype.selectSingleNode = function(cXPathString)
{
  if(this.ownerDocument.selectSingleNode)
  {
     return this.ownerDocument.selectSingleNode(cXPathString, this);
  }
  else{throw "For XML Elements Only";}
}
}

/**
* 返回val在arr中的索引值
* @param val
* @param arr:数组
* @returns
*/
function inArrayIndex(val,arr)
{
if(arr==null||arr==undefined)
	return -1;
if(typeof arr === "string"){
	return arr == val ? 1: -1;
}else{
	var idx = -1;
	for(var i=0; i<arr.length;i++)
	{
		if(val == arr[i])
		{
		     idx = i;
		     break;
		}
	}
	return idx;
}
}  

function moveOptions( sourceSelObjId, targetSelObjId )
{
sourceSelObj = document.getElementById(sourceSelObjId);
targetSelObj = document.getElementById(targetSelObjId);

if( sourceSelObj == null || targetSelObj == null
		|| sourceSelObj.tagName != "SELECT" || targetSelObj.tagName != "SELECT" )
	return;

for( var is = 0; is < sourceSelObj.options.length; is++ )
{
	if( !sourceSelObj.options[is].selected )
		continue;

	var option = sourceSelObj.options[is];

	sourceSelObj.remove( is );
	is--;

	targetSelObj.options[ targetSelObj.options.length ] = option;
}
}

function checkIsInputNum(event)
{
if( !( checkIsAllNum( event.keyCode ) )
		&& event.keyCode!=45
			&& event.keyCode!=189
				&& event.keyCode!=109
					&& event.keyCode != 190
						&& event.keyCode != 110
					)
{
	alert("只能输入数字，\"-\"，\".\"号，请同时检查您输入的是否是一个数字！");
	return false;
}

return true;
}

function isLegalityNum( numString )
{
var  newPar= /^(-|\+)?\d+(\.\d+)?$/ ;

return newPar.test( numString );
}

function checkIsAllNum( kc )
{
if( ( kc >= 48 && kc <= 57 ) || ( kc >= 96 && kc <= 105 ) || event.keyCode == 46  || event.keyCode == 8 || event.keyCode == 13)
	return true;
else
	return false;

return true;
}

function getXMLStr(str)
{
str = replaceAll(str, "&", "&amp;");
str = replaceAll(str, "<", "&lt;");
str = replaceAll(str, ">", "&gt;");
str = replaceAll(str, "'", "&apos;");
str = replaceAll(str, "\"", "&quot;");
return str;
}

function getNoXMLStr(str)
{
str = replaceAll(str, "&lt;", "<");
str = replaceAll(str, "&gt;", ">");
str = replaceAll(str, "&apos;", "'");
str = replaceAll(str, "&quot;", "\"");
str = replaceAll(str, "&amp;", "&");

return str;
}

function getHTMLStr(str)
{
str = replaceAll(str, "<", "&lt;");
str = replaceAll(str, ">", "&gt;");
return str;
}


function encodeAll(str)
{
str = encodeAPOS(str);
str = encodeQUOT(str);
str = encodeLT(str);
str = encodeGT(str);
return encodeAMP(str);
}

function decodeAll(str)
{
str = decodeAPOS(str);
str = decodeQUOT(str);
str = decodeLT(str);
str = decodeGT(str);

return decodeAMP(str);
}

function encodeAPOS(str)
{
return replaceAll(str, "'", "QADEV_ESCAPE_APOS");
}

function decodeAPOS(str)
{
return replaceAll(str, "QADEV_ESCAPE_APOS", "'");
}

function encodeQUOT(str)
{
return replaceAll(str, "\"", "QADEV_ESCAPE_QUOT");
}

function decodeQUOT(str)
{
return replaceAll(str, "QADEV_ESCAPE_QUOT", "\"");
}

function encodeLT(str)
{
return replaceAll(str, "<", "QADEV_ESCAPE_LT");
}

function decodeLT(str)
{
return replaceAll(str, "QADEV_ESCAPE_LT", "<");
}

function encodeGT(str)
{
return replaceAll(str, ">", "QADEV_ESCAPE_GT");
}

function decodeGT(str)
{
return replaceAll(str, "QADEV_ESCAPE_GT", ">");
}

function encodeAMP(str)
{
return replaceAll(str, "&", "QADEV_ESCAPE_AMP");
}

function decodeAMP(str)
{
return replaceAll(str, "QADEV_ESCAPE_AMP", "&");
}

function message(msg)
{
alert(msg);
}

function errorInfo()
{
alert("系统出现错误，请稍后刷新重试!");
}

function inArray(arr,value)
{
if(arr==null||arr==undefined)
	return false;
if(typeof arr === "string"){
	return arr == value ? true:false;
}else{
	for(var i=0;i<arr.length;i++)
	{
		if(arr[i] == value)
			return true;
	}
	return false;
}
}

function replaceAll(str, replaced, replacement)
{
if(str == null)
	return "";
var ret = "";

var index = 0;
while(str.indexOf(replaced, index) >= index)
{
	ret += str.substring(index, str.indexOf(replaced, index)) + replacement;

	index = str.indexOf(replaced, index) + replaced.length;
}

ret += str.substring(index);

return ret;
}

function getXMLDoc()
{
if(document.implementation && document.implementation.createDocument)
{
	return document.implementation.createDocument("", "", null);
}

if(window.ActiveXObject)
{
	return new ActiveXObject("Msxml.DOMDocument");
}

return null;
}

function trim(str)
{
if(str == null || str.length == 0)
	return "";

var startIndex = 0;
for(var i = 0; i < str.length; i++)
{
	if(str.charAt(i) == " " || str.charAt(i) == "\t" || str.charAt(i) == "\n" || str.charAt(i) == "\r")
		startIndex++;
	else
		break;
}

if(startIndex == str.length)
	return "";

var endIndex = str.length - 1;
for(var i = str.length - 1; i > 0; i--)
{
	if(str.charAt(i) == " " || str.charAt(i) == "\t" || str.charAt(i) == "\n" || str.charAt(i) == "\r")
		endIndex--;
	else
		break;
}

return str.substring(startIndex, endIndex + 1);
}

function setTextContent(node, text)
{
while(node.childNodes.length > 0)
{
	node.removeChild(node.firstChild);
}

if(text != "")
{
	var textNode = node.ownerDocument.createTextNode("text");
	textNode.nodeValue = text;
	node.appendChild(textNode);
}
}

function getTextContent(node)
{
var value = "";

for(var i = 0; i < node.childNodes.length; i++)
{
	if(node.childNodes[i].nodeValue != null)
		value += node.childNodes[i].nodeValue;
}

return trim(value);
}

function getDocXML(doc)
{
if(doc.xml)
{
	return doc.xml;
}

return new XMLSerializer().serializeToString(doc);
}

function getSafeParam(param)
{
param = encodeURI(param);
param = replaceAll(param, "+", "%2B");
param = replaceAll(param, "&", "%26");
param = replaceAll(param, "#", "%23");

return param;
}

function isNumber(numStr)
{
if(trim(numStr).length == 0)
{
	return false;
}

if(trim(numStr).length > 1 && trim(numStr).charAt(0) == "0")
{
	return false;
}

for(var i = 0; i < trim(numStr).length; i++)
{
	if(trim(numStr).charAt(i) != "0"
		&& trim(numStr).charAt(i) != "1"
		&& trim(numStr).charAt(i) != "2"
		&& trim(numStr).charAt(i) != "3"
		&& trim(numStr).charAt(i) != "4"
		&& trim(numStr).charAt(i) != "5"
		&& trim(numStr).charAt(i) != "6"
		&& trim(numStr).charAt(i) != "7"
		&& trim(numStr).charAt(i) != "8"
		&& trim(numStr).charAt(i) != "9")
	{
		return false;
	}
}

return true;
}

function newAdjustPRI( selObj, isUp )
{
//check leagle
if( selObj == null || selObj.options == null || selObj.options.length <= 1 || selObj.selectedIndex < 0 )
	return;


if( isUp )
{
	for ( var i=0; i<selObj.options.length; i++ )
	{
		if ( selObj.options[i].selected )
		{
			if ( i == 0 )
				return;

			var	option1 = selObj.options[i];
			var option2 = selObj.options[i - 1];

			changeOption(option1, option2);
		}
	}// for
}
else
{
	for ( var i=selObj.options.length-1; i>=0; i-- )
	{
		if ( selObj.options[i].selected )
		{
			if ( i == (selObj.options.length-1) )
				return;

			var	option1 = selObj.options[i];
			var option2 = selObj.options[i + 1];

			changeOption(option1, option2);
		}
	}// for
}
}

function addOption( selObj, option )
{
selObj.options[selObj.length] = option;
}

function removeOption( selObj, index )
{
for( var i = index; i < selObj.options.length - 1; i++ )
{
	selObj.options[i] = selObj.options[i+1];
}

selObj.options.length--;
}

function adjustPRI( selObj, isUp )
{
//check leagle
if( selObj == null || selObj.options == null || selObj.options.length <= 1 || selObj.selectedIndex < 0 )
	return;

if( isUp )
{
	for ( var i=0; i<selObj.options.length; i++ )
	{
		if ( selObj.options[i].selected )
		{
			if ( i == 0 )
				return;

			var	t	= selObj.options[i];
			selObj.remove(i);
			selObj.add(t,i-1);
		}
	}// for
}
else
{
	for ( var i=selObj.options.length-1; i>=0; i-- )
	{
		if ( selObj.options[i].selected )
		{
			if ( i == (selObj.options.length-1) )
				return;

			var	t	= selObj.options[i];
			selObj.remove(i);
			selObj.add(t,i+1);
		}
	}// for
}
}

function changeOption(option1, option2)
{
var value1 = option1.value;
var text1 = getTextContent(option1);
var type1 = option1.getAttribute("type");
var dataType1 = option1.getAttribute("dataType");
var desc1 = option1.getAttribute("desc");

var value2 = option2.value;
var text2 = getTextContent(option2);
var type2 = option2.getAttribute("type");
var dataType2 = option2.getAttribute("dataType");
var desc2 = option2.getAttribute("desc");

option1.value = value2;
setTextContent(option1, text2);
if(type2 != null)
	option1.setAttribute("type", type2);
if(dataType2 != null)
	option1.setAttribute("dataType", dataType2);
if(desc2 != null)
	option1.setAttribute("desc", desc2);

option2.value = value1;
setTextContent(option2, text1);
if(type1 != null)
	option2.setAttribute("type", type1);
if(dataType1 != null)
	option2.setAttribute("dataType", dataType1);
if(desc1 != null)
	option2.setAttribute("desc", desc1);

option1.selected = false;
option2.selected = true;
}

//cookie related
function readCookie(name)
{
var cookieValue;
var nameEQ=name+"=";
var ca=document.cookie.split(';');
for(var i=0;i<ca.length;i++)
{
	var c=ca[i];
	while(c.charAt(0)==' ')
	{
		c=c.substring(1,c.length);
	}
	if(c.indexOf(nameEQ)==0){
		
		cookieValue = c.substring(nameEQ.length,c.length);
		//解决在tomcat下cookie前面带引号的问题
		if(cookieValue.indexOf("\"") == 0)
			cookieValue = cookieValue.substring(1,cookieValue.length -1);
		return cookieValue;  
	}
}

return null;
}
function createCookie( value )
{
var expires="";
var date=new Date();
date.setTime(date.getTime()+( 3600 * 24 *1000 * 365 ) );
expires="; expires=" +date.toGMTString();
document.cookie=value+expires+"; path=/";
}

function deleteCookie(name)
{
var expdate = new Date();
expdate.setTime(expdate.getTime() - (86400 * 1000 * 1));

document.cookie = name + "=" + escape("") + "; expires=" + expdate.toGMTString() + "; path=/";
}

function encodeCookie(value)
{
value = replaceAll(value, "=", "$replace_equality$");
value = replaceAll(value, ";", "$replace_semicolon$");

return value;
}

function decodeCookie(value)
{
value = replaceAll(value, "$replace_equality$", "=");
value = replaceAll(value, "$replace_semicolon$", ";");

return value;
}

function isLeapYear(year){
if((year %4==0 && year %100!=0) || (year %400==0))
	return true;
else
	return false;
}

function callDays(year,month){
var days=0;
switch(month){
case 1: case 3: case 5: case 7: case 8: case 10: case 12: days=31;break;
case 4: case 6: case 9: case 11: days=30;break;
case 2: if(isLeapYear(year)) days=29;
else days=28;
break;
}
return days;
}

function arrayContainsString(arr,str){
if(!arr||arr==null||arr.length==0)
	return false;
for(var i=0;i<arr.length;i++)
	if(arr[i]==str)
		return true;
}

//发送邮件
function showSendMailWin()
{
$("#send_mail_win").css('display','block');
$("#send_mail_win_back").css('display','block');
$("#sendMailReceivers").val("");
$("sendMailContent").val("");
}


function executeSendMailCancel()
{
$("#send_mail_win").hide();
$("#send_mail_win_back").hide();
}


function executeSendMailSubmit()
{
var sendMailReceivers = $("#sendMailReceivers").val();
if(sendMailReceivers == "")
{
	alert("请填写收件人");
	return;
}
var usrArray = sendMailReceivers.split(";");
for(var i = 0 ; i< usrArray.length ; i++){
	if(usrArray[i].indexOf("@")!=-1)
		if(!isEmail(usrArray[i])){
			alert("邮箱格式不正确，请重新填写");
			$("#sendMailReceivers").focus();
			return;
		}
}

if($("#sendMailContent").val() == "")
{
	alert("请填写邮件正文");
	return;
}

var sendMailContent = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\"/>";
sendMailContent += "<style type=\"text/css\">table{border:1px #E1E1E1 solid;}td{border:1px #E1E1E1 solid;padding:10px;}</style></head>"
sendMailContent += "<body><table>";
sendMailContent += "<tr><td>邮件正文 </td><td>" + replaceAll(getXMLStr($("#sendMailContent").val()), "\n", "<br>") + "</td>";
sendMailContent += "<tr><td>bug编号</td><td><a href=\"" + WEB_ROOT_URL + "taskManagement.html?operation=read&taskid="+taskId+"\">"+taskId+"</a></td>";
sendMailContent += "<tr><td>bug描述</td><td>" + replaceAll($("#input_taskDescription").val(),"../attachment/download_json.jsp", WEB_ROOT_URL + "attachment/download_json.jsp") +"</td>";
sendMailContent += "</table></body></html>";

var params = "sendMailReceivers=" + getSafeParam(sendMailReceivers);
params += "&sendMailSubject=" + getSafeParam("[Cynthia]有数据需要您的处理意见，请关注并处理");
params += "&sendMailContent=" + getSafeParam(sendMailContent);

$("#mail_send_ok").disabled = true;

$.ajax({
	url : "../mail/executeSendMail.jsp",
	data : params,
	type : 'POST',
	success : send_mail_success
});
}

function send_mail_success()
{
alert("邮件发送成功!");
executeSendMailCancel();
$("#mail_send_ok").disabled = false;
}


//end of send mail


function isEmail(strEmail)
{
if (strEmail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1)
	return true;
else
	return false;
}

function isSysFilter(filterId)
{
if((filterId == "119891")||(filterId == "119892")||(filterId == "119893")||(filterId=="119695"))
	return true;
return false;
}


function request(paras){
var url = window.location.href;
if(url.indexOf("#") >=0)
	url = url.substring(0, url.indexOf("#"));
var paraString = url.substring(url.indexOf("?")+1,url.length).split("&");
var paraObj = {} ;
for (var i=0; j=paraString[i]; i++){
	paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length);
}
var returnValue = paraObj[paras.toLowerCase()];
if(typeof(returnValue)=="undefined"){
	return "";
}else{
	return decodeURIComponent(returnValue);
}
}

/*************信息提示框****************/
//loading框
function showLoading(isShow,id)
{
var layOutDiv = id+"layout";
if(isShow){
	if($("#"+layOutDiv+"").length == 0){
		var info="<div id=\""+layOutDiv+"\" style=\"display: none; text-align:center; ;z-index: 999999;\"><img width='40' height='40' src=\"img/refresh.gif\"/></div>";
		$("#"+id+"").append(info);
	}
	$("#"+layOutDiv+"").fadeIn("fast");
}else{
	$("#"+layOutDiv+"").fadeOut("fast");
}
}

function closeInfoWin(type) {  
$("#warning-info").hide();
}  

/*function showInfoWin(type, message){
$("#closeAlertBtn").alert('close');
$("#messager").remove();
if(type == "error"){
	$(".tab-box:first").after('<div id="messager"><p class="bg-danger">'+message+'<button id="closeAlertBtn" type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></p></div>');
}
if(type == "success"){
	$(".tab-box:first").after('<div id="messager"><p class="bg-success">'+message+'<button id="closeAlertBtn" type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></p></div>');
}
if(type == "warning"){
	$(".tab-box:first").after('<div id="messager"><p class="bg-warning">'+message+'<button id="closeAlertBtn" type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></p></div>');
}
BeginScroll();
}*/

var oTimer = null;
function BeginScroll() {
window.parent.scrollTo(0,0);
oTimer = window.setInterval(closeMesagerBox, 5000);
}

function StopScroll() {
window.clearInterval(oTimer);
}

function closeMesagerBox(){
$("#closeAlertBtn").alert('close');
$("#messager").remove();
StopScroll();
}

/*************信息提示框结束****************/

function logout()
{
if(!window.confirm("确定要退出ECOP吗？"))
	return;
window.location = base_url + '/login/logout.jsp';
}

/**
* 返回根目录路径,方便不同路径下的ajax请求
* @returns {String}
*/
function getRootDir()
{
return WEB_ROOT_URL;
}

function isChinese(str)
{
	var reg = /^[\u0391-\uFFE5]+$/;
	return reg.test(str);
}

function getLengthOfStr(str)
{
var length = 0;
for(var i=0;i<str.length;i++)
{
	if(isChinese(str.charAt(i)))
	{
		length += 2;
	}
	else
	{
		length++;
	}
}
return length;
}

function encodeAllUrl(str)
{
str = encodeAPOS(str);
str = encodeQUOT(str);
str = encodeLT(str);
str = encodeGT(str);
str = encodeAMP(str);
str = replaceAll(str, "\n", "");
str = replaceAll(str, "\r\n", "");
str = replaceAll(str, "\r", "");
str = replaceAll(str, "\t", "");
str = replaceAll(str, "\\", "");
return str;
}



function getWebRootDir()
{
if(!WEB_ROOT_URL){
	WEB_ROOT_URL = readCookie('webRootDir');
}
	
return WEB_ROOT_URL;
}


$(function(){
deleteCookie('webRootDir');
});


//加载等待...
function MyLoading(falg,msg) {
var loading = "<div id='dlading' style='position:fixed;left:0;width:100%;height:100%;top:0;background:#FFFFFF;opacity:0.6;filter:alpha(opacity=60);z-index:9999999;'>";
loading += "<div style='border:2px solid #B7CDFC;cursor:point;position:relative;top:45%;margin:0 auto;width:150px;height:30px;line-height:28px;font-size:14px;background:#fff;padding-left:5px;'>";
loading += "<h6  style='margin-top: 0px;display:inline;line-height:28px;height:28px;float:left'><img src='images/loading.gif' width='16' height='16' valign='middle' style='margin-top:6px;float:left' />&nbsp;"+msg+"</h6></div></div>";
if (falg) {
    $("body", document).append(loading);

} else {
    if ($("#dlading") != undefined) {
        $("#dlading").remove();
    }
}
}
/**确认提示框*/
function showInfoWin(title,msg,width){
var width=350;
if(typeof(width)!="undefined"){
	width = width;
}
$('body').addClass('modal-open');
$('body').append('<div class="modal-backdrop fade in" id="_modal-shadowbox"></div>');
text = '<div id="_modal-box" class="modal fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false" style="display: block; ">'
       +'<div class="modal-dialog modal-sm">'
	   +'<div class="modal-content" style="margin-top: 150px;width: '+width+'px;">'
	   +'<div class="modal-header">'
	   +'<h4 class="modal-title" id="myModalLabel">'
	   +title
	   +'</h4></div>'
	   +'<div class="modal-body"><h7>'
	   +msg
	   +'</div></h7>'
	   +'<div class="modal-footer">'
	   +'<button type="button" class="btn btn-default _modal-close" data-dismiss="modal">确定</button>'
	   +'</div>'
	   +'</div>'
	   +'</div>'
	   +'</div>'
$('body').append(text);	 
$('._modal-close').click(function() { 
	$('body').removeClass('modal-open');
	$('#_modal-shadowbox').remove(); 
	$('#_modal-box').remove(); 
});
}


function MapToList(map){
var list = new Array();
$.each(map, function(key, value){
	var obj = new Object();
	obj.key = key;
	obj.value = value;
	list.push(obj);
});
return list;
}


if (!Array.prototype.indexOf)
{
Array.prototype.indexOf = function(elt /*, from*/)
{
var len = this.length >>> 0;  
 var from = Number(arguments[1]) || 0;
from = (from < 0)
     ? Math.ceil(from)
     : Math.floor(from);
if (from < 0)
  from += len; 
 for (; from < len; from++)
{
  if (from in this &&
      this[from] === elt)
    return from;
}
return -1;
};
}

