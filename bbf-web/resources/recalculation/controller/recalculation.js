var app = angular.module('recalculationApp', ['commonServiceModule', 'ui.bootstrap']);

app.controller('recalculationController', function($scope, $http, $modal, commonService) {
	
	commonService.getDictDefByDictClass(1001, false, function(response) {
		$scope.resourceTypes=response.data;
	});
	commonService.getDictDefByDictClass(1010, false, function(response) {
		$scope.recalcTypes=response.data;
	});
	
	$scope.resetBtn = function() {
		$scope.data.cdrType = undefined;
		$scope.data.recalcType = undefined;
		$scope.data.billMonth = undefined;
		$scope.beginDate = undefined;
		$scope.endDate = undefined;
	};

	$scope.recalculate = function() {
		console.log($scope.data.addBillMonth);
		if($scope.data.addCdrType==""||$scope.data.addCdrType==null){
			alert("请输入资源类型！");
		}else if($scope.data.addRecalcType==""||$scope.data.addRecalcType==null){
			alert("请选择重算类型！");
		}else if($scope.data.addBillMonth==""||$scope.data.addBillMonth==null){
			alert("请输入重算月份！");
		}else if(confirm("确认要重算？")){
			$http({
				method : 'POST',
				url : '../rest/createRecalcCdrTask',
				data : $scope.data,
				headers : {
					"Content-Type" : "application/json ; charset=UTF-8"
				}
			}).then(function successCallback(response) {
				if ("success" == response.data.status) {
					alert(response.data.info);
					$scope.load_page(1);
				} else if ("error" == response.data.status) {
					alert(response.data.info);
				} else {
					alert("重算任务生成失败");
				}
				$("#addModal").modal('hide');
			}, function errorCallback(response) {
				alert("请求失败");
				console.log(response);
			});
		}
	};
	
	$scope.cancel = function() {
		$("#addModal").modal('hide');
	}
	$scope.openModel = function() {
		$scope.data.addCdrType = undefined;
		$scope.data.addRecalcType = undefined;
		$scope.data.addBillMonth = undefined;
		$('#addModal').modal('show');
			
		/*$modal.open({
        	size: 'md',
            templateUrl : "add.html",//script标签中定义的id
            controller : "orgOperationCtrl",
            backdrop : "static",
            resolve : {
                
            }
        });*/
    };
});

//模态框对应的Controller
//app.controller('orgOperationCtrl', function($rootScope, $scope, $http, $modalInstance, commonService) {
//	$scope.orgStatusDict = orgStatusDict;
//	$scope.orgInfo = data;
//	$scope.newOrgInfo = {};
//	
//	function initOrgNames() {
//		$scope.newOrgInfo.orgStatus = orgStatusDict[0].entryId;
//	}
//	initOrgNames();
//	
//	//添加机构
//	$scope.addOrg = function() {
//		var superOrgId = -1;
//		if ($scope.orgInfo) {
//			superOrgId = $scope.orgInfo.orgId;
//		}
//		$scope.newOrgInfo.superOrgId = superOrgId;
//		$scope.newOrgInfo.modifyOperatorId = localStorage.getItem("modifyOpeId");
//		var data = $scope.newOrgInfo;
//		
//		//添加机构
//		$http({
//			method : 'POST',
//			url : '../rest/addOrganization',
//			data : data,
//			headers : {
//				"Content-Type" : "application/json"
//			}
//		}).then(function successCallback(response) {
//			layer.alert('添加成功', {shade: false}); 
//			commonService.httpPost('../rest/showOrgTree', null, $rootScope.getOrgData);
//			$modalInstance.close();
//		}, function errorCallback(response) {
//			console.log("add org fail");
//		});
//	};
//					
//	//编辑机构
//	$scope.organizationUpdate = function() {
//		$scope.orgInfo.modifyOperatorId = localStorage.getItem("modifyOpeId");
//		var data =$scope.orgInfo;
//		//更新机构信息
//		$http({
//			method : 'POST',
//			url : '../rest/editOrganization',
//			data : data,
//			headers : {
//				"Content-Type" : "application/json"
//			}
//		}).then(function successCallback(response) {
//			layer.alert('修改成功', {shade: false}); 
//			commonService.httpPost('../rest/showOrgTree', null, $rootScope.getOrgData);
//			$modalInstance.close();
//		}, function errorCallback(response) {
//			console.log("edit fail");
//		});	
//	};
//	
//	$scope.cancel = function() {
//    	$modalInstance.close();
//    }
//}
