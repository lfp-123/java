var app = angular.module('resManage', ["commonServiceModule", "ui.bootstrap", "treeControl"]);
app.controller("resController", ["$rootScope", "$scope", "$http", "$modal", "commonService", function($rootScope, $scope, $http, $modal, commonService){
	//展示所有资源
	$rootScope.getResourceData = function(response) {
		console.log(response.data);
		$scope.resourceData = response.data;
		expandAll(response.data);
		if($scope.node) {
			$scope.showSelected($scope.node);
		}
	}
	commonService.httpPost('../rest/showAuthTree', {}, $rootScope.getResourceData);
	
	function expandAll(datas) {
		if(datas.length > 0) {
			for(var i=0; i<datas.length; i++) {
				$scope.expandedNodes.push(datas[i]);
				if(datas[i].childResourceList.length > 0) {
					expandAll(datas[i].childResourceList);
				}
			}
		}
	}
	
	//从数据字典获取操作员状态
	var successCallback = function(response) {
		$scope.resourceTypeDict = response.data;
	}
	commonService.getDictDefByDictClass(1007, false, successCallback);
	
	$scope.predicate = "";
    $scope.comparator = false;
	$scope.treeOptions = {  
	   nodeChildren: "childResourceList",  
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
	    	return a.resourceId == b.resourceId;
	    }
	} 
	
	$scope.showSelected = function(node) {
		angular.forEach($scope.resourceTypeDict, function(item) {
			if(item.entryId == node.resourceType) {
				node.resourceTypeName = item.entryName;
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
	            controller : "resourceOperationCtrl",
	            backdrop : "static",
	            resolve : {
	                data : function() {//data作为modal的controller传入的参数
	                     return $scope.node;//用于传递数据
	                },
	                resourceTypeDict: function() {
	                    return $scope.resourceTypeDict;
	                }
	            }
	        });
		} else if (type == "modify") {
			if(!$scope.node) {
				layer.alert('请选择要修改的资源', {shade: false}); 
				return;
			}
	        $modal.open({
	        	size: 'md',
	            templateUrl : "modify.html",//script标签中定义的id
	            controller : "resourceOperationCtrl",
	            backdrop : "static",
	            resolve : {
	                data : function() {//data作为modal的controller传入的参数
	                     return $scope.node;//用于传递数据
	                },
	                resourceTypeDict: function() {
	                    return $scope.resourceTypeDict;
	                }
	            }
	        });
		}
    }
		
	//删除资源
	$scope.resourceDelete = function() {
		if(!$scope.node) {
			layer.alert('请选择要删除的资源', {shade: false}); 
			return;
		}
		var deleteConfirm = confirm("确定是否删除选中内容?");
		if(deleteConfirm == true){
			$scope.node.modifyOperatorId = localStorage.getItem("modifyOpeId");
			var data = $scope.node;
			//更新机构信息
			$http({
				method : 'POST',
				url : '../rest/removeResource',
				data : data,
				headers : {
					"Content-Type" : "application/json"
				}
			}).then(function successCallback(response) {
				layer.alert('删除成功', {shade: false}); 
				$scope.node = [];
				commonService.httpPost('../rest/showAuthTree', {}, $rootScope.getResourceData);
			}, function errorCallback(response) {
				console.log("edit fail");
			});
		}
	};
			
}]);

//模态框对应的Controller
app.controller('resourceOperationCtrl', function($rootScope, $scope, $http, $modalInstance, commonService, data, resourceTypeDict) {
	$scope.resourceTypeDict = resourceTypeDict;
	$scope.resourceInfo = data;
	$scope.newResourceInfo = {};
	
	function initOrgNames() {
		$scope.newResourceInfo.resourceType = resourceTypeDict[0].entryId;
	}
	initOrgNames();
	
	//添加资源
	$scope.addRes = function() {
		var superResourceId = -1;
		if ($scope.resourceInfo) {
			superResourceId = $scope.resourceInfo.resourceId;
		}
		$scope.newResourceInfo.superResourceId = superResourceId;
		$scope.newResourceInfo.modifyOperatorId = localStorage.getItem("modifyOpeId");
		var data = $scope.newResourceInfo;
		
		//添加资源
		$http({
			method : 'POST',
			url : '../rest/addResource',
			data : data,
			headers : {
				"Content-Type" : "application/json"
			}
		}).then(function successCallback(response) {
			layer.alert('添加成功', {shade: false}); 
			commonService.httpPost('../rest/showAuthTree', {}, $rootScope.getResourceData);
			$modalInstance.close();
		}, function errorCallback(response) {
			console.log("add fail");
		});	
		
	};
	
	//编辑资源信息
	$scope.resourceUpdate = function() {
		$scope.resourceInfo.modifyOperatorId = localStorage.getItem("modifyOpeId");
		var data =$scope.resourceInfo;
		
		$http({
			method : 'POST',
			url : '../rest/editResource',
			data : data,
			headers : {
				"Content-Type" : "application/json"
			}
		}).then(function successCallback(response) {
			layer.alert('修改成功', {shade: false}); 
			commonService.httpPost('../rest/showAuthTree', {}, $rootScope.getResourceData);
			$modalInstance.close();
		}, function errorCallback(response) {
			console.log("edit fail");
		});	
	};
	
	$scope.cancel = function() {
    	$modalInstance.close();
    }
});