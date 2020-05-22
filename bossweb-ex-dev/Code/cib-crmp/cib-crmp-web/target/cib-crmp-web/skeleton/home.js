var JS_FILE_MIN = false;//readCookie("JS_FILE_MIN");
if(JS_FILE_MIN=="true" || JS_FILE_MIN==true){
	includeJs("/deploy/js/modules/modules-min.js");    
}else{
	includeJs("/skeleton/modules/app/apps.js"); 
	includeJs("/skeleton/modules/home/controller/homeController.js"); 
	includeJs("/skeleton/modules/home/services/homeService.js"); 
	
	includeJs("/skeleton/modules/home/controller/headerController.js"); 
	includeJs("/skeleton/modules/home/services/headerService.js"); 
	
}