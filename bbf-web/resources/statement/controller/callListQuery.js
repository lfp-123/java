var app = angular.module('callListApp', ['commonServiceModule']);

app.controller('callListController', function($scope, $http, commonService) {
	
	commonService.getDictDefByDictClass(1001, false, function(response) {
		$scope.resourceTypes = [];
		if(response.data && response.data.length > 0) {
			console.log(response.data);
			$scope.resourceTypes = response.data;
			$scope.resourceType = response.data[0];
		}
//		console.log(response.data);
//		$scope.resourceTypes=response.data;
//		$scope.data.resourceType = response.data[1];
	});
	
	$scope.resetBtn = function() {
		$scope.data.orgName = undefined;
		$scope.data.operatorId = undefined;
		$scope.data.ip = undefined;
		$scope.data.beginDate = undefined;
		$scope.data.endDate = undefined;
		$scope.list = [{},{},{},{},{},{},{},{},{},{},{},{},{},{}];
	};
	
	// 由资源类型选择要显示的搜索条件
//	$scope.resourceTypeChange = function() {
//		if ($scope.data.resourceType == 1) {
//			document.getElementById("workNumber").style = "display:";
//			document.getElementById("ipAdress").style = "display:none";
//			document.getElementById("table-cdr").style = "display:";
//			document.getElementById("table-dma").style = "display:none";
//			document.getElementById("table-network").style = "display:none";
//			$scope.list = [{},{},{},{},{},{},{},{},{},{},{},{},{},{}];
//		} else if ($scope.data.resourceType == 2) {
//			document.getElementById("workNumber").style = "display:";
//			document.getElementById("ipAdress").style = "display:";
//			document.getElementById("table-cdr").style = "display:none";
//			document.getElementById("table-dma").style = "display:";
//			document.getElementById("table-network").style = "display:none";
//			$scope.list = [{},{},{},{},{},{},{},{},{},{},{},{},{},{}];
//		} else if ($scope.data.resourceType == 3) {
//			document.getElementById("workNumber").style = "display:none";
//			document.getElementById("ipAdress").style = "display:none";
//			document.getElementById("table-cdr").style = "display:none";
//			document.getElementById("table-dma").style = "display:none";
//			document.getElementById("table-network").style = "display:";
//			$scope.list = [{},{},{},{},{},{},{},{},{},{},{},{},{},{}];
//		}
//	}
	
	

	// ===下载===
	$scope.download = function() {
		$http({
			method : 'POST',
			url : '../rest/createCallListExcel',
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

function resourceTypeChange(obj) {
	if (obj.value == "number:1") {
		document.getElementById("workNumber").style = "display:";
		document.getElementById("ipAdress").style = "display:none";
		document.getElementById("table-cdr").style = "display:";
		document.getElementById("table-dma").style = "display:none";
		document.getElementById("table-network").style = "display:none";
	} else if (obj.value == "number:2") {
		document.getElementById("workNumber").style = "display:";
		document.getElementById("ipAdress").style = "display:";
		document.getElementById("table-cdr").style = "display:none";
		document.getElementById("table-dma").style = "display:";
		document.getElementById("table-network").style = "display:none";
	} else if (obj.value == "number:3") {
		document.getElementById("workNumber").style = "display:none";
		document.getElementById("ipAdress").style = "display:none";
		document.getElementById("table-cdr").style = "display:none";
		document.getElementById("table-dma").style = "display:none";
		document.getElementById("table-network").style = "display:";
	}
	document.getElementById("resetBtn").click();
}