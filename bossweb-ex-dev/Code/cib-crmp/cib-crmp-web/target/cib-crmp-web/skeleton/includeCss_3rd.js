function includeCss(fileName) {  
 document.write("<link rel='stylesheet' type='text/css' href='"+ROOT_CONTEXT+fileName+"'/>");  
}

document.write("<meta http-equiv='Pragma' content='no-cache'>");
document.write("<meta http-equiv='Cache-Control' content='no-cache'>");
document.write("<meta http-equiv='Expires' content='0'>");

includeCss("/skeleton/plugins/autocomplete/jquery.autocomplete.css");
includeCss("/skeleton/plugins/amaze/assets/css/amazeui.min.css");
includeCss("/skeleton/plugins/amaze/assets/css/admin.css");

includeCss("/skeleton/css/bootstrap.min.css");
includeCss("/skeleton/css/style.css");
includeCss("/skeleton/css/progress.css");
includeCss("/skeleton/plugins/NProgress/nprogress.css");

includeCss("/skeleton/css/H-ui.css");
includeCss("/skeleton/css/H-ui.admin.css");
includeCss("/skeleton/lib/Hui-iconfont/1.0.1/iconfont.css");

includeCss("/skeleton/plugins/ngtable/css/ng-table.css");
includeCss("/skeleton/plugins/angular-tree-control/css/tree-control.css");
includeCss("/skeleton/plugins/multi-select/index.css");

document.write("<link rel='icon' type='image/png' href="+ROOT_CONTEXT+"/skeleton/images/newland.png>");