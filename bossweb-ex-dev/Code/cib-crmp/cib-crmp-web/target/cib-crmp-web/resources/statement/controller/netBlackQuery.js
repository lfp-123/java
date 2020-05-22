var app = angular.module('netBlackQueryApp', ['commonServiceModule']);

app.controller('netBlackQueryController', function($scope, $http, commonService) {

	$scope.beginDate = getBeginDate();
	$scope.endDate = yesterdayDate();
	$scope.resetBtn = function() {
		$scope.data.orgName = undefined;
		$scope.data.operatorId = undefined;
		$scope.beginDate = getBeginDate();
		$scope.endDate = yesterdayDate();
	};
	
	// ===下载===
	$scope.download = function() {
		var index = layer.load(1, {
			  shade: [0.5,'#000']
			});
		$http({
			method : 'POST',
			url : '../rest/createBlackNetListExcel',
			data : $scope.data,
			headers : {
				"Content-Type" : "application/json ; charset=UTF-8"
			}
		}).then(function successCallback(response) {
			if ("success" == response.data.status) {
				var fileName = response.data.fileName;
				downloadTemplate('../rest/downloadExcel', 'fileName', fileName);
			} else {
				alert("Excel生成失败");
			}
		}, function errorCallback(response) {
			alert("请求失败");
			console.log(response);
		});
	};

});

var app = angular.module('netBlackUserQueryApp', ['commonServiceModule']);

app.controller('netBlackUserQueryController', function($scope, $http, commonService) {
	
	$scope.resetBtn = function() {
		$scope.data.orgName = undefined;
		$scope.data.operatorId = undefined;
		$scope.data.beginDate = undefined;
		$scope.data.endDate = undefined;
		$scope.list = [{},{},{},{},{},{},{},{},{},{},{},{},{},{}];
	};
	
	// ===下载===
	$scope.download = function() {
		var index = layer.load(1, {
			  shade: [0.5,'#000']
			});
		$http({
			method : 'POST',
			url : '../rest/createNetBlackUserExcel',
			data : $scope.data,
			headers : {
				"Content-Type" : "application/json ; charset=UTF-8"
			},
			timeout:180000
		}).then(function successCallback(response) {
			if ("success" == response.data.status) {
				var fileName = response.data.fileName;
				downloadTemplate('../rest/downloadExcel', 'fileName', fileName);
				layer.close(index);
			} else {
				layer.close(index);
				alert("Excel生成失败");
			}
		}, function errorCallback(response) {
			layer.close(index);
			alert("数据量太大无法下载，请重新选择下载条件！");
			console.log(response);
		});
	};

});