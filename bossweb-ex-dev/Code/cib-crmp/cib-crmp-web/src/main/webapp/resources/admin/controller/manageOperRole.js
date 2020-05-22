var app = angular.module('mangeOperRole', ["ngTable", "commonServiceModule", "treeControl"]).run(function($templateCache) {
	var template =
        '<ul {{options.ulClass}} >' +
        '<li ng-repeat="node in node.{{options.nodeChildren}} | filter:filterExpression:filterComparator {{options.orderBy}}" ng-class="[headClass(node), selectedClass(), unselectableClass()]" {{options.liClass}}' +
        'set-node-to-data ng-click="selectNodeLabel(node)">' +
        '<i class="tree-branch-head" ng-class="iBranchClass()" ng-click="selectNodeHead(node)"></i>' +
        '<i class="f-16 Hui-iconfont Hui-iconfont-avatar"></i>' +
        '<tree-transclude></tree-transclude>' +
        '<treeitem ng-if="nodeExpanded()"></treeitem>' +
        '</li>' +
        '</ul>';
	$templateCache.put("roleTree.html", template);
});
app.controller("operRoleController", ["$scope", "$http", "NgTableParams","commonService", function($scope, $http,  NgTableParams, commonService){
	var self = this;
	$scope.operatorInfos = [];
	$scope.operList = [];
	
	var operStatusNames = {};
	var operLevelNames = {};
	//从数据字典获取操作员状态
	var successCallback = function(response) {
		$scope.operatorStatusDict = response.data;
		if(response.data.length > 0) {
			for(var i=0; i<response.data.length; i++) {
				operStatusNames[response.data[i].entryId] = response.data[i].entryName;
			}
		}
	}
	commonService.getDictDefByDictClass(1002, false, successCallback);
	
	//从数据字典获取操作员等级
	var successCallback1 = function(response) {
		$scope.operatorLevel = response.data;
		if(response.data.length > 0) {
			for(var i=0; i<response.data.length; i++) {
				operLevelNames[response.data[i].entryId] = response.data[i].entryName;
			}
		}
	}
	commonService.getDictDefByDictClass(1003, false, successCallback1);
	
	$scope.getOperStatusName = function(status) {
		return operStatusNames[status];
	}
	
	$scope.getOperLevelName = function(level) {
		return operLevelNames[level];
	}
	
	$scope.getOperOrgName = function(orgList) {
		if(!orgList) {
			return "";
		}
		if(orgList.length != 1) {
			return "";
		}
		return orgList[0].orgName + " (" + orgList[0].orgNameFull + ")";
	}
	
	var search = function(data) {
		return $http({
			method : 'POST',
			url : '../rest/showAllOperGrid',
			data : data,
			headers : {
				"Content-Type" : "application/json"
			}
		});
	}
	
	//搜索操作员
	$scope.searchOperator = function() {
		$scope.operatorInfos = [];
		$scope.nodes = [];
		if (angular.isUndefined($scope.loginName)){
			$scope.loginName = null;
		}
		if (angular.isUndefined($scope.searchName)){
			$scope.searchName = null;
		}
		//搜索操作员，以gridJSON格式返回
		self.tableParams = new NgTableParams({count: 10}, 
				{
					counts: [], 
					getData: function(params) {
						var operatorName = $scope.searchName;
						if($scope.searchName) {
							operatorName = operatorName.replace(/%/g, "/%").replace(/_/g, "/_");
						}
						var data = {
							"loginName":$scope.loginName, 
							"operatorName":operatorName,
							"operatorStatus":0,
							pageSize: params.count(),             
							pageNo: params.page()
						}
						return search(data).then(function successCallback(response) {
							$scope.operatorInfos = [];
							$scope.operList = [];
							var len = response.data.list.length;
							if(len == 0) {
								layer.alert('没有操作员数据', {shade: false}); 
							}
							if(len < 10) {
								for(var i=10; i>len; i--) {
									response.data.list.push({isNull: 'null_tr'});
								}
							}
							$scope.operList = response.data.list;
							params.total(response.data.total);             
							return response.data.list;
						}, function errorCallback(response) {
							console.log("search fail");
							$scope.operList = [];
							params.total(0);
							return [];
						});	
					}
				}
		);
	};
	$scope.searchOperator();
	
	var searchOperatorRole = function(operatorId) {
		return $http({
			method : 'POST',
			url : '../rest/searchOperatorRole',
			data : {'operatorId': operatorId},
			headers : {
				"Content-Type" : "application/json"
			}
		});
	}
	
	$scope.isSelected = function(info) {
		var pos = -1;
		for(var i=0; i<$scope.operatorInfos.length; i++){
			if(info.operatorId == $scope.operatorInfos[i].operatorId) {
				pos = i;
				break;
			}
		}
		return pos != -1;
	}
	$scope.selectOperator = function(info) {
		if(info.isNull == 'null_tr') {
			return;
		}
		var pos = -1;
		for(var i=0; i<$scope.operatorInfos.length; i++){
			if(info.operatorId == $scope.operatorInfos[i].operatorId) {
				pos = i;
				break;
			}
		}
		if(pos == -1) {
//			if($scope.operatorInfos.length == 1) {
//				$scope.operatorInfos[0].roleList = JSON.parse(localStorage.getItem("operRoleSrcData"));
//			}
//			localStorage.setItem("operRoleSrcData", JSON.stringify(info.roleList));
			$scope.operatorInfos = [];
			$scope.operatorInfos.push(info);
			searchOperatorRole(info.operatorId).then(function successCallback(response) {
				$scope.nodes = [];
				if(response.data.length == 0) {
					return;
				}
				$scope.nodes = response.data;
			}, function errorCallback(response) {
				console.log("search fail");
				$scope.nodes = [];
			});
		} else {
			//localStorage.setItem("operRoleSrcData", JSON.stringify([]));
			$scope.operatorInfos.splice(pos, 1);
			$scope.nodes = [];
		}
	}
	
	//重置查询条件
	$scope.resetSearch = function(){
		$scope.loginName = null;
		$scope.searchName = null;
	};
	
	function getRoleData(response) {
		$scope.roleData = response.data;
		if(response.data.length == 0){
			layer.alert('没有角色数据', {shade: false});
		}
	}
	
	$scope.searchRole = function() {
		var data = {"roleStatus": 0};
		commonService.httpPost('../rest/showAllRoleGrid', data, getRoleData);
	}
	$scope.searchRole();
	
	$scope.treeOptions = {  
		nodeChildren: "child",  
		dirSelectable: true,  
		multiSelection: true,
		templateUrl: "roleTree.html",
		injectClasses: {  
	       li: "tree_li",  
	       liSelected: "a5",  
	       label: "a6",  
	       labelSelected: "a8"  
	    },
	    equality: function(a, b,$scope) {
	    	if (!a || !b)
	            return false;
	    	return a.roleId == b.roleId;
	    }
	} 
	
	//点击取消时返回之前选择的
	$scope.cancelSelected = function(){
		if($scope.operatorInfos.length == 1) {
			//$scope.nodes = JSON.parse(localStorage.getItem("operRoleSrcData"));
			searchOperatorRole($scope.operatorInfos[0].operatorId).then(function successCallback(response) {
				$scope.nodes = [];
				if(response.data.length == 0) {
					return;
				}
				$scope.nodes = response.data;
				$scope.operatorInfos[0].roleList = $scope.nodes;
			}, function errorCallback(response) {
				console.log("search fail");
				$scope.nodes = [];
				$scope.operatorInfos[0].roleList = $scope.nodes;
			});
		}
	};
		
	//编辑操作员资源关系
	$scope.updateOperRole = function(){
		if($scope.operatorInfos.length != 1) {
			layer.alert('请选择一个用户', {shade: false});
			return;
		}
		$scope.operatorInfos[0].modifyOperatorId = localStorage.getItem("modifyOpeId");
		var data = $scope.operatorInfos[0];
		data.roleList = $scope.nodes;
		$http({
			method : 'POST',
			url : '../rest/editOperatorRole',
			data : data,
			headers : {
				"Content-Type" : "application/json ; charset=UTF-8"
			}
		}).then(function successCallback(response) {
			layer.alert('修改成功', {shade: false}); 
			localStorage.setItem("operRoleSrcData", JSON.stringify(data.roleList));
			$scope.searchOperator();
			$scope.searchRole();
		}, function errorCallback(response) {
			console.log(response);
		});
					
	};
	
	self.changePage = changePage;
	function changePage (curPage) {
		if (!/^\-?\d*$/.test(curPage)) {
			return;
		}
		if(curPage <= 0) {
			curPage = 1;
		} else {
			var maxPage = Math.floor((self.tableParams.total() - 1) / self.tableParams.count() + 1);
			if(curPage > maxPage) {
				curPage = maxPage;
			}
		}
		self.tableParams.page(curPage);
	}
}]);