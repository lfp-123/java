var app = angular.module('operLog', ['commonServiceModule','angularjs-dropdown-multiselect']);

app.controller("operLogCtrl", function($scope, $http, commonService) {
	
	//加载日志类型字典
	commonService.getDictDefByDictClass(1029, false, function(response) {
		$scope.logTypes = response.data;
		console.log($scope.logTypes);
	});
	
	//加载日志操作字典
	commonService.getDictDefByDictClass(1028, false, function(response) {
		$scope.operTypes = response.data;
		console.log($scope.operTypes);
	});
	
	// 重置
	$scope.resetBtn = function() {
		$scope.data.operateModule = null;
		$scope.beginDate =  null;
	};
	
});