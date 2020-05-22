var JS_FILE_MIN = false;//readCookie("JS_FILE_MIN");
if(JS_FILE_MIN=="true" || JS_FILE_MIN==true){
	include("deploy/js/modules/modules-min.js");    
}else{
	include("skeleton/modules/app/apps.js"); 
	include("skeleton/modules/home/controller/homeController.js"); 
	include("skeleton/modules/home/services/homeService.js"); 
	
	include("skeleton/modules/home/controller/headerController.js"); 
	include("skeleton/modules/home/services/headerService.js"); 
}