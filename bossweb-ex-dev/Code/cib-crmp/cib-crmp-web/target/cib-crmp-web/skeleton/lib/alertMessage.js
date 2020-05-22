/**
 * 
 * 输出各类型不同的提示条
 */

var oTimer = null;
function BeginScroll() {
	window.parent.scrollTo(0,0);
	oTimer = window.setInterval(closeMesagerBox, 10000);
}

function StopScroll() {
	window.clearInterval(oTimer);
}

function closeMesagerBox(){
	jQuery(".am-close").click();
	StopScroll();
}


//普通信息
function showMessage(messageContent, showWidgetId){
	isMessageExist(showWidgetId);
	var htmlContent = "<div class='am-alert' data-am-alert>" +
	"<button type='button' class='am-close'>&times;</button>" +
	"<p>"+messageContent+"</p></div>";
	jQuery("#"+showWidgetId).html(htmlContent);
	BeginScroll();
}

//正确信息
function showSuccMessage(messageContent, showWidgetId){
	isMessageExist(showWidgetId);
	var htmlContent = "<div class='am-alert am-alert-success' data-am-alert>" +
	"<button type='button' class='am-close'>&times;</button>" +
	"<p>"+messageContent+"</p></div>";
	jQuery("#"+showWidgetId).html(htmlContent);
	BeginScroll();
}

//警告信息
function showWarnMessage(messageContent, showWidgetId){
	isMessageExist(showWidgetId);
	var htmlContent = "<div class='am-alert am-alert-warning' data-am-alert>" +
	"<button type='button' class='am-close'>&times;</button>" +
	"<p>"+messageContent+"</p></div>";
	jQuery("#"+showWidgetId).html(htmlContent);
	BeginScroll();
}

//错误信息
function showErrMessage(messageContent, showWidgetId){
	isMessageExist(showWidgetId);
	var htmlContent = "<div class='am-alert am-alert-danger' data-am-alert>" +
	"<button type='button' class='am-close'>&times;</button>" +
	"<p>"+messageContent+"</p></div>";
	jQuery("#"+showWidgetId).html(htmlContent);
	BeginScroll();
}

//判断该类信息是否已存在与页面上
function isMessageExist(showWidgetId){
	if(jQuery("#"+showWidgetId).html() != "" && jQuery("#"+showWidgetId).html() != null){
		jQuery("#"+showWidgetId).html("");
	}
}
