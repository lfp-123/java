
var app = angular.module('myApp', []);
app.controller('initController', function($scope, $http) {
	var page = 1;
    $http({
        method: 'POST',
        url: 'rest/showAllOrg'
    }).then(function successCallback(response) {
            $scope.organization = response.data;
            console.log($scope.organization.org_id);
        }, function errorCallback(response) {
            // 请求失败执行代码
        	console.log("can not receive json")
    });
    
    //删除一个组织
    $scope.deleteOrg = function(index,organization) {
		var org_id = organization[index].org_id;
		var data = {"org_id":org_id};
		console.log(data);
		$http({
			method : 'POST',
			url : 'rest/deleteOrg',
			data : data,
			headers : {
				"Content-Type" : "application/json"
			}
		}).then(function successCallback(response) {
			console.log(response.data);
			$scope.organization = response.data;
		}, function errorCallback(response) {
			
		});
	};
	
	//编辑
	$scope.editOrg = function(index,organization) {
		var org_id = organization[index].org_id;
		location.href = "editOrg.html?org_id="+org_id;
	};
	
	//模糊搜索
	$scope.searchOrg = function() {
		console.log($scope.search_message);
		var data = {"search_message":$scope.search_message};
		console.log(data);
		$http({
			method : 'POST',
			url : 'rest/searchOrg',
			data : data,
			headers : {
				"Content-Type" : "application/json ; charset=UTF-8"
			}
		}).then(function successCallback(response) {
			console.log(response.data);
			$scope.organization = response.data;
		}, function errorCallback(response) {
			
		});
	};
	
	//输入框清空时返回所有函数
	$scope.inputChange = function() {
		var input_length = $scope.search_message.length;
		console.log("functon in");
		
		if(input_length ==0){
			$http({
		        method: 'POST',
		        url: 'rest/showAllOrg'
		    }).then(function successCallback(response) {
		            $scope.organization = response.data;
		        }, function errorCallback(response) {
		            // 请求失败执行代码
		        	console.log("can not receive json")
		    });
		}
	};
	
	//上一页
	$scope.previous = function() {
		if(page != 1){
			page--;
		}
		console.log(page);
	};
	
	//下一页
	$scope.next = function() {
		page++;
		console.log(page);
	};
	
});