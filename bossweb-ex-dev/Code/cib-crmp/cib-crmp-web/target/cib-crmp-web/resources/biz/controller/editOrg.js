var app = angular.module('editApp', []);    
app.controller('editController', function($scope, $http) {
	//拼接data
	var url = location.search;
	var requestJson = new Object();   
	if (url.indexOf("?") != -1) {   
		var str = url.substr(1);   
	    strs = str.split("&");   
	    for(var i = 0; i < strs.length; i ++) {   
	    	requestJson[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);   
	    }   
	}   
	console.log(requestJson);
	var data = requestJson;
	
	//根据id显示一个组织
	$http({
        method: 'POST',
        url: 'rest/showOneOrg',
        data : data,
		headers : {
			"Content-Type" : "application/json"
		}
    }).then(function successCallback(response) {
            console.log(response.data);
            $scope.org_name = response.data.org_name;
            $scope.org_desc = response.data.org_desc;
        }, function errorCallback(response) {
            // 请求失败执行代码
        	console.log("can not receive json")
    });
	
	//修改信息
	$scope.orgUpdate = function() {
		var url = location.search;
		var urlArray = url.split("=");
		var org_id = urlArray[1];
		
		var data = {"org_id":org_id
		   		,"org_name":$scope.org_name,"org_desc":$scope.org_desc};
		
		$http({
	        method: 'POST',
	        url: 'rest/editOrg',
	        data : data,
			headers : {
				"Content-Type" : "application/json"
			}
	    }).then(function successCallback(response) {
	    		location.href = "show.html";
	        }, function errorCallback(response) {
	            // 请求失败执行代码
	        	console.log("can not receive json")
	    });
	};
	
});