function includeCss(fileName) {  
 document.write("<link rel='stylesheet' type='text/css' href='"+ROOT_CONTEXT+fileName+"'/>");  
}

includeCss("/skeleton/css/bootstrap.min.css");
includeCss("/skeleton/css/H-ui.modal.css");
includeCss("/skeleton/plugins/amaze/assets/css/amazeui.min.css");
includeCss("/skeleton/plugins/ngtable/css/ng-table.css");
includeCss("/skeleton/plugins/angular-tree-control/css/tree-control.css");

document.write("<link rel='icon' type='image/png' href="+ROOT_CONTEXT+"/skeleton/images/newland.png>");

