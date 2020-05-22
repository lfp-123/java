var app = angular.module('orgManage', ["commonServiceModule", "ui.bootstrap", "treeControl"]);
app.controller("orgController", ["$rootScope", "$scope", "$http", "$modal", "commonService", function($rootScope, $scope, $http, $modal, commonService) {
	commonService.reFreshInitData(function(response) {
		console.log(response);
	});
	
	//查询出所有的机构
	$rootScope.getOrgData = function(response) {
		$scope.orgData = response.data;
		if($scope.node) {
			$scope.showSelected($scope.node);
		}
	}
	commonService.httpPost('../rest/showAllOrganization', null, $rootScope.getOrgData);
	
	//从数据字典获取操作员状态
	var successCallback = function(response) {
		$scope.orgStatusDict = response.data;
	}
	commonService.getDictDefByDictClass(1002, false, successCallback);
	
	$scope.predicate = "";
    $scope.comparator = false;
	$scope.treeOptions = {  
	   nodeChildren: "childOrgList",  
	   dirSelectable: true,  
	   multiSelection: false,
	   injectClasses: {  
	       ul: "a1",  
	       li: "a2",  
	       liSelected: "a7",  
	       iExpanded: "a3",  
	       iCollapsed: "a4",  
	       iLeaf: "a5",  
	       label: "a6",  
	       labelSelected: "a8"  
	    },
	    equality: function(a, b,$scope) {
	    	if (!a || !b)
	            return false;
	    	return a.orgId == b.orgId;
	    }
	} 
	
	$scope.showSelected = function(node) {
		angular.forEach($scope.orgStatusDict, function(item) {
			if(item.entryId == node.orgStatus) {
				node.orgStatusName = item.entryName;
				return;
			}
        });
	}
	
	$scope.resetFilter = function() {
		$scope.predicate = "";
	}
	
	$scope.openModel = function(type) {
		if(type == "add") {
			$modal.open({
	        	size: 'md',
	            templateUrl : "add.html",//script标签中定义的id
	            controller : "orgOperationCtrl",
	            backdrop : "static",
	            resolve : {
	                data : function() {//data作为modal的controller传入的参数
	                     return $scope.node;//用于传递数据
	                },
	                orgStatusDict: function() {
	                    return $scope.orgStatusDict;
	                }
	            }
	        });
		} else if (type == "modify") {
			if(!$scope.node) {
				layer.alert('请选择要修改的机构', {shade: false}); 
				return;
			}
			if($scope.node.isSync == 0) {
				layer.alert('同步的机构不支持修改', {shade: false}); 
				return;
			}
			var modalInstance = $modal.open({
	        	size: 'md',
	            templateUrl : "modify.html",//script标签中定义的id
	            controller : "orgOperationCtrl",
	            backdrop : "static",
	            resolve : {
	                data : function() {//data作为modal的controller传入的参数
	                     return $scope.node;//用于传递数据
	                },
	                orgStatusDict: function() {
	                    return $scope.orgStatusDict;
	                }
	            }
	        });
			
			modalInstance.result.then(function (selectedItem) {
	        	if(selectedItem == "cancle") {
	        		reData();
	        	}
	        }, function () {
	        	reData();
	        });
		}
    }
	
	var reData = function(type) {
		$scope.node = JSON.parse(localStorage.getItem("orgSrcData"));
		localStorage.setItem("orgSrcData", JSON.stringify({}));
    	commonService.httpPost('../rest/showAllOrganization', null, $rootScope.getOrgData);
	}
	
	//删除机构
	$scope.organizationDelete = function() {
		if(!$scope.node) {
			layer.alert('请选择要删除的机构', {shade: false}); 
			return;
		}
		if($scope.node.isSync == 0) {
			layer.alert('同步的机构不支持删除', {shade: false}); 
			return;
		}
		var deleteConfirm = confirm("确定是否删除选中内容?");
		if(deleteConfirm == true){
			$scope.node.modifyOperatorId = localStorage.getItem("modifyOpeId");
			var data = $scope.node;
			//根据机构编号删除机构
			$http({
				method : 'POST',
				url : '../rest/removeOrganization',
				data : data,
				headers : {
					"Content-Type" : "application/json"
				}
			}).then(function successCallback(response) {
				layer.alert('删除成功', {shade: false}); 
				$scope.node = {};
				commonService.httpPost('../rest/showAllOrganization', null, $rootScope.getOrgData);
			}, function errorCallback(response) {
				console.log("delete fail");
			});
		}
	};
		
}]);

//模态框对应的Controller
app.controller('orgOperationCtrl', function($rootScope, $scope, $http, $modalInstance, commonService, data, orgStatusDict) {
	$scope.orgStatusDict = orgStatusDict;
	$scope.orgInfo = data;
	$scope.newOrgInfo = {};
	
	function initOrgNames() {
		localStorage.setItem("orgSrcData", JSON.stringify($scope.orgInfo));
		$scope.newOrgInfo.orgStatus = orgStatusDict[0].entryId;
	}
	initOrgNames();
	
	//添加机构
	$scope.addOrg = function() {
		var superOrgId = -1;
		if ($scope.orgInfo && $scope.orgInfo.orgId) {
			superOrgId = $scope.orgInfo.orgId;
		}
		$scope.newOrgInfo.superOrgId = superOrgId;
		$scope.newOrgInfo.modifyOperatorId = localStorage.getItem("modifyOpeId");
		var data = $scope.newOrgInfo;
		if(!data.orgName) {
			layer.alert('请填写机构名', {shade: false}); 
			return;
		}
		//添加机构
		$http({
			method : 'POST',
			url : '../rest/addOrganization',
			data : data,
			headers : {
				"Content-Type" : "application/json"
			}
		}).then(function successCallback(response) {
			if(response.data.code != 0) {
				layer.alert(response.data.msg, {shade: false}); 
			} else {
				layer.alert('添加成功', {shade: false}); 
			}
			commonService.httpPost('../rest/showAllOrganization', null, $rootScope.getOrgData);
			$modalInstance.close();
		}, function errorCallback(response) {
			layer.alert('添加机构异常！', {shade: false}); 
			console.log("add org fail");
		});
	};
					
	//编辑机构
	$scope.organizationUpdate = function() {
		$scope.orgInfo.modifyOperatorId = localStorage.getItem("modifyOpeId");
		var data =$scope.orgInfo;
		if(!data.orgName) {
			layer.alert('请填写机构名', {shade: false}); 
			return;
		}
		//更新机构信息
		$http({
			method : 'POST',
			url : '../rest/editOrganization',
			data : data,
			headers : {
				"Content-Type" : "application/json"
			}
		}).then(function successCallback(response) {
			layer.alert('修改成功', {shade: false}); 
			commonService.httpPost('../rest/showAllOrganization', null, $rootScope.getOrgData);
			$modalInstance.close();
		}, function errorCallback(response) {
			console.log("edit fail");
		});	
	};
	
	$scope.cancel = function() {
    	$modalInstance.close("cancle");
    }
});