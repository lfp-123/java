var app = angular.module('callListBlackApp', ['commonServiceModule']);

app.controller('callListBlackController', function($scope, $http, commonService) {
	
	var cdrSearchedFlag = 0;// 0未搜索，1搜索未下载，2搜索已下载
	var dmaSearchedFlag = 0;
	var networkSearchedFlag = 0;
	
	commonService.getDictDefByDictClass(1001, false, function(response) {
		$scope.resourceTypes=response.data;
	});
	
	$scope.resetBtn = function() {
		$scope.data.resourceType = undefined;
		$scope.data.selOrg = undefined;
		$scope.data.operatorId = undefined;
		$scope.data.ip = undefined;
		$scope.data.beginDate = undefined;
		$scope.data.endDate = undefined;
		$scope.list = [{},{},{},{},{},{},{},{},{},{},{},{},{},{}];
	};
	
	// 由资源类型选择要显示的搜索条件
	$scope.resourceTypeChange = function() {
		if ($scope.data.resourceType == 1) {
			document.getElementById ("table-cdr").style="display:";
			document.getElementById ("table-dma").style="display:none";
			document.getElementById ("table-network").style="display:none";
			$scope.list = [{},{},{},{},{},{},{},{},{},{},{},{},{},{}];
		} else if ($scope.data.resourceType == 2) {
			document.getElementById ("table-cdr").style="display:none";
			document.getElementById ("table-dma").style="display:";
			document.getElementById ("table-network").style="display:none";
			$scope.list = [{},{},{},{},{},{},{},{},{},{},{},{},{},{}];
		} else if ($scope.data.resourceType == 3) {
			document.getElementById ("table-cdr").style="display:none";
			document.getElementById ("table-dma").style="display:none";
			document.getElementById ("table-network").style="display:";
			$scope.list = [{},{},{},{},{},{},{},{},{},{},{},{},{},{}];
		}
	}
	
	// ===下载===
	$scope.download = function() {
		$http({
			method : 'POST',
			url : '../rest/createBlackCallListExcel',
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