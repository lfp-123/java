function onBodyDown(event) {
	console.log(event.target.id);
	if (!(event.target.id == "orgTreeInput" || event.target.id == "orgTreeDiv" || $(event.target).parents("#orgTreeDiv").length>0)) {
		hideMenu();
	}
}

function hideMenu() {
	$("#orgTreeDiv").slideToggle("fast");
	$("body").unbind("mousedown", onBodyDown);
}
var app = angular.module("operApp", ["ngTable", "commonServiceModule", "ui.bootstrap", "treeControl"]);

app.factory('operatorService', function($http) {
	return {
		searchOperator : function(data) {
			return $http({
				method : 'POST',
				url : '../rest/searchOperator',
				data : data,
				headers : {
					"Content-Type" : "application/json"
				}
			});
		}
	};
});

var operatorController = app.controller("operatorController",["$rootScope","$scope","$http","$modal","NgTableParams","commonService","operatorService", function($rootScope,$scope,$http,$modal,NgTableParams,commonService,operatorService){
	var self = this;
	$scope.operatorInfos = [];
	$scope.operList = [];
	$scope.orgs = [];
	$scope.selectedOrg = [];
	
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
	
	//sm md lg 三种可选大小,这里是模态框的大小
	$scope.openModel = function(type) {
		if(type == "add") {
			$modal.open({
	        	size: 'md',
	            templateUrl : "add.html",//script标签中定义的id
	            controller : "operationCtrl",
	            backdrop : "static",
	            resolve : {
	                data : function() {//data作为modal的controller传入的参数
	                     return [];//用于传递数据
	                },
	                operatorLevel: function() {
	                     return $scope.operatorLevel;//用于传递数据
	                },
	                operatorStatusDict: function() {
	                    return $scope.operatorStatusDict;//用于传递数据
	                }
	            }
	        });
		} else if (type == "modify") {
			if($scope.operatorInfos.length > 1) {
				layer.alert('只能选择一个要修改的操作员', {shade: false}); 
				return;
			}
			if($scope.operatorInfos.length != 1) {
				layer.alert('请选择要修改的操作员', {shade: false}); 
				return;
			}
			if($scope.operatorInfos[0].operatorId == '10000000') {
				layer.alert('超级管理员不允许修改', {shade: false}); 
				return;
			}
	        var modalInstance = $modal.open({
	        	size: 'md',
	            templateUrl : "modify.html",//script标签中定义的id
	            controller : "operationCtrl",
	            backdrop : "static",
	            resolve : {
	                data : function() {//data作为modal的controller传入的参数
	                     return $scope.operatorInfos;//用于传递数据
	                },
	                operatorLevel: function() {
	                     return $scope.operatorLevel;//用于传递数据
	                },
	                operatorStatusDict: function() {
	                    return $scope.operatorStatusDict;//用于传递数据
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
	
	var reData = function() {
		for(var i=0; i<$scope.operList.length; i++){
			if($scope.operatorInfos[0].operatorId == $scope.operList[i].operatorId) {
				$scope.operList[i] = JSON.parse(localStorage.getItem("operSrc"));
				break;
			}
		}
    	$scope.operatorInfos[0] = JSON.parse(localStorage.getItem("operSrc"));
    	localStorage.setItem("operSrc", JSON.stringify({}));
    	var currPage = self.tableParams.page();
    	$rootScope.searchOperator();
    	self.tableParams.page(currPage);
	}
	
	//搜索
	$rootScope.searchOperator = function() {
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
							pageSize: params.count(),             
							pageNo: params.page()
						}
						
						return operatorService.searchOperator(data).then(function successCallback(response) {
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
	$rootScope.searchOperator();
	
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
			$scope.operatorInfos.push(info);
		} else {
			$scope.operatorInfos.splice(pos, 1);
		}
	}
	
	//重置查询条件
	$scope.resetSearch = function(){
		$scope.loginName  = null;
		$scope.searchName = null;
	};
	
	//删除操作员
	$scope.operatorDelete = function() {
		if($scope.operatorInfos.length == 0) {
			layer.alert('请选择要删除的操作员', {shade: false}); 
			return;
		}
		for(var i=0; i<$scope.operatorInfos.length; i++) {
			if($scope.operatorInfos[i].operatorId == '10000000') {
				layer.alert('超级管理员不允许删除', {shade: false}); 
				return;
			}
			if($scope.operatorInfos[i].loginName != '' && $scope.operatorInfos[i].loginName != null) {
				layer.alert('notes用户不允许删除', {shade: false}); 
				return;
			}
		}
		var deleteConfirm = confirm("确定是否删除选中内容?"); 
		var data = $scope.operatorInfos;
		//删除
		if(deleteConfirm == true){
			$http({
				method : 'POST',
				url : '../rest/deleteOperator',
				data : data,
				headers : {
					"Content-Type" : "application/json"
				}
			}).then(function successCallback(response) {
				layer.alert('删除成功', {shade: false}); 
				$scope.operatorInfos = [];
				$rootScope.searchOperator();
			}, function errorCallback(response) {
				layer.alert('删除失败', {shade: false}); 
			});	
		}
	};
	
	$scope.resetPwd = function() {
		if($scope.operatorInfos.length == 0) {
			layer.alert('请选择要重置密码的操作员', {shade: false}); 
			return;
		}
		for(var i=0; i<$scope.operatorInfos.length; i++) {
			if($scope.operatorInfos[i].loginName != '' && $scope.operatorInfos[i].loginName != null) {
				layer.alert('notes用户不允许重置密码', {shade: false}); 
				return;
			}
		}
		var deleteConfirm = confirm("确定是否重置选中操作员的密码?"); 
		var data = $scope.operatorInfos;
		if(deleteConfirm == true){
			$http({
				method : 'POST',
				url : '../rest/resetOperatorPwd',
				data : data,
				headers : {
					"Content-Type" : "application/json"
				}
			}).then(function successCallback(response) {
				layer.alert('重置密码成功', {shade: false}); 
				$scope.operatorInfos = [];
				$rootScope.searchOperator();
			}, function errorCallback(response) {
				layer.alert('重置密码失败', {shade: false}); 
			});	
		}
	}
	
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

//模态框对应的Controller
app.controller('operationCtrl', function($rootScope, $scope, $http, $modalInstance, commonService, data, operatorLevel, operatorStatusDict) {
	$scope.operatorLevel = operatorLevel;
	$scope.operatorStatusDict = operatorStatusDict;
	$scope.operatorInfos = data;
	$scope.operatorInfo = {};
	$scope.orgData = [];
	$scope.orgNames = "";
	
	function initOrgNames() {
		if($scope.operatorInfos.length > 0) {
			angular.forEach($scope.operatorInfos[0].orgList, function(item) {
				$scope.orgNames = item.orgName + " (" + item.orgNameFull + ")";
	        });
			localStorage.setItem("operSrc", JSON.stringify($scope.operatorInfos[0]));
		}
		$scope.operatorInfo.operatorStatus = operatorStatusDict[0].entryId;
		$scope.operatorInfo.operatorLevel = operatorLevel[0].entryId;
	}
	initOrgNames();
	
	//查询出所有的机构
	var getOrgData = function(response) {
		$scope.orgData = response.data;
	}
	commonService.httpPost('../rest/showHeadOrgs', null, getOrgData);
	
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
	
	//添加操作员的同时为操作员添加归属机构
	$scope.showTree = function(){
		if($scope.operatorInfos.length > 0) {
			if($scope.operatorInfos[0].loginName != '' && $scope.operatorInfos[0].loginName != null) {
				return;
			}
			$scope.nodes = $scope.operatorInfos[0].orgList[0];
		}
		var infoObj = $("#orgTreeInput");
		$("#orgTreeDiv").css({width: infoObj.width() + "px"}).slideToggle("fast");
		$("body").bind("mousedown", onBodyDown);
	};
	
	$scope.showSelected = function(node) {
		$scope.orgNames = node.orgNameFull;
		if($scope.operatorInfos.length > 0) {
			$scope.operatorInfos[0].orgList[0] = node;
		} else {
			$scope.operatorInfo.orgList = [];
			$scope.operatorInfo.orgList[0] = node;
		}
		hideMenu();
//		angular.forEach($scope.nodes, function(item) {
//			$scope.orgNames.push(item.orgName);
//        });
	}
	
	//添加操作员 
    $scope.add = function() {
    	var data = $scope.operatorInfo;
    	if(!data.operatorName) {
			layer.alert('请填写操作员名', {shade: false}); 
			return;
		}
    	if(data.operatorLevel == 2 && (data.orgList == null || data.orgList.length == 0)) {
			layer.alert('分行管理员机构必选', {shade: false}); 
			return;
		}
		$http({
			method : 'POST',
			url : '../rest/addOperator',
			data : data,
			headers : {
				"Content-Type" : "application/json"
			}
		}).then(function successCallback(response) {
			layer.alert(response.data.msg, {shade: false}); 
			$rootScope.searchOperator();
			$modalInstance.close();
		}, function errorCallback(response) {
			console.log(response.data);
		});	
    };
    
    //更新操作员
    $scope.operatorUpdate = function() {
		var data = $scope.operatorInfos[0];
		if(!data.operatorName) {
			layer.alert('请填写操作员名', {shade: false}); 
			return;
		}
		if(data.operatorLevel == 2 && (data.orgList == null || data.orgList.length == 0)) {
			layer.alert('分行管理员机构必选', {shade: false}); 
			return;
		}
		//编辑操作员
		$http({
			method : 'POST',
			url : '../rest/editOperator',
			data : data,
			headers : {
				"Content-Type" : "application/json"
			}
		}).then(function successCallback(response) {
			layer.confirm('修改成功', {
			  btn: ['确定'] //按钮
			}, function(index){
				$rootScope.searchOperator();
				$modalInstance.close();
				layer.closeAll('dialog');
			});
			//layer.alert('修改成功', {shade: false}); 
		}, function errorCallback(response) {
			console.log(response.data);
		});	
    }
    
    $scope.cancel = function() {
    	$modalInstance.close("cancle");
    }
});
			    
