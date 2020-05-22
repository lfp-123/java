var app = angular.module('roleManage', ["ngTable", "commonServiceModule", "ui.bootstrap"]);
app.controller("manageController", ["$rootScope","$scope","$http","$modal","NgTableParams","commonService", function($rootScope,$scope,$http,$modal,NgTableParams,commonService){
	var self = this;
	$scope.roleInfos = [];
	$scope.roleList = [];
	
    //查询出所有的角色
	$http({
		method : 'POST',
		url : '../rest/showAllRole',
		headers : {
			"Content-Type" : "application/json ; charset=UTF-8"
		}
	}).then(function successCallback(response) {
		var len = response.data.length;
		if(len < 10) {
			for(var i=10; i>len; i--) {
				response.data.push({isNull: 'null_tr'});
			}
		}
		console.log(response.data);
		$scope.roleList = response.data;
		self.tableParams = new NgTableParams({count: 10}, {counts:[], dataset: $scope.roleList});
	}, function errorCallback(response) {
		console.log("can not receive response");
	});
	
	//从数据字典获取操作员状态
	var roleStatusNames = {};
	var successCallback = function(response) {
		console.log(response.data)
		$scope.roleStatusDict = response.data;
		if(response.data.length > 0) {
			for(var i=0; i<response.data.length; i++) {
				roleStatusNames[response.data[i].entryId] = response.data[i].entryName;
			}
		}
	}
	commonService.getDictDefByDictClass(1002, false, successCallback);
	
	$scope.getRoleStatusName = function(status) {
		return roleStatusNames[status];
	}
	
	$scope.parseRoleDesc = function(desc) {
		if(desc != null && desc != '' && desc.length > 20) {
			return desc.substring(0, 20) + "...";
		}
		return desc;
	}
	
	//搜索角色
	$rootScope.searchRole = function() {
		var roleName = $scope.searchName;
		if($scope.searchName) {
			roleName = roleName.replace(/%/g, "/%").replace(/_/g, "/_");
		}
		if($scope.searchId != '' && $scope.searchId != undefined) {
			if (!/^\-?\d*$/.test($scope.searchId) || $scope.searchId < 0) {
				layer.alert('角色编号只支持数字', {shade: false}); 
				return;
			}
		}
		var data = {"roleId":$scope.searchId, "roleName":roleName, "roleStatus":0};
		$scope.roleInfos = [];
		$http({
			method : 'POST',
			url : '../rest/searchRole',
			data : data,
			headers : {
				"Content-Type" : "application/json"
			}
		}).then(function successCallback(response) {
			var len = response.data.length;
			if(len == 0) {
				layer.alert('没有角色数据', {shade: false}); 
			}
			if(len < 10) {
				for(var i=10; i>len; i= i-1) {
					response.data.push({isNull: 'null_tr'});
				}
			}
			$scope.roleList = response.data;
			self.tableParams = new NgTableParams({count: 11}, {counts:[], dataset: $scope.roleList});
		}, function errorCallback(response) {
			console.log("search fail");
		});	
	};
	
	$scope.isSelected = function(info) {
		var pos = -1;
		for(var i=0; i<$scope.roleInfos.length; i++){
			if(info.roleId == $scope.roleInfos[i].roleId) {
				pos = i;
				break;
			}
		}
		return pos != -1;
	}
	$scope.selectRole = function(info) {
		if(info.isNull == 'null_tr') {
            return;
        }
        var pos = -1;
        for(var i=0; i<$scope.roleInfos.length; i++){
            if(info.roleId == $scope.roleInfos[i].roleId) {
                pos = i;
                break;
            }
        }
        if(pos == -1) {
            $scope.roleInfos.push(info);
        } else {
            $scope.roleInfos.splice(pos, 1);
        }
	}
	
	//重置查询条件
	$scope.resetSearch = function(){
		$scope.searchId = null;
		$scope.searchName = null;
	};
	
	$scope.openModel = function(type) {
		if(type == "add") {
			$modal.open({
	        	size: 'md',
	            templateUrl : "add.html",//script标签中定义的id
	            controller : "roleOperationCtrl",
	            backdrop : "static",
	            resolve : {
	                data : function() {//data作为modal的controller传入的参数
	                     return [];//用于传递数据
	                },
	                roleStatusDict: function() {
	                    return $scope.roleStatusDict;
	                }
	            }
	        });
		} else if (type == "modify") {
			if($scope.roleInfos.length > 1) {
				layer.alert('只能选择一个要修改的角色', {shade: false}); 
				return;
			}
			if($scope.roleInfos.length != 1) {
				layer.alert('请选择要修改的角色', {shade: false}); 
				return;
			}
			var modalInstance = $modal.open({
	        	size: 'md',
	            templateUrl : "modify.html",//script标签中定义的id
	            controller : "roleOperationCtrl",
	            backdrop : "static",
	            resolve : {
	                data : function() {//data作为modal的controller传入的参数
	                     return $scope.roleInfos;//用于传递数据
	                },
	                roleStatusDict: function() {
	                    return $scope.roleStatusDict;
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
		for(var i=0; i<$scope.roleList.length; i++){
			if($scope.roleInfos[0].roleId == $scope.roleList[i].roleId) {
				$scope.roleList[i] = JSON.parse(localStorage.getItem("roleSrcData"));
				break;
			}
		}
    	$scope.roleInfos[0] = JSON.parse(localStorage.getItem("roleSrcData"));
    	localStorage.setItem("roleSrcData", JSON.stringify({}));
    	var currPage = self.tableParams.page();
    	self.tableParams = new NgTableParams({page: currPage, count: 10}, {counts: [], dataset: $scope.roleList});
	}
	
	//删除角色
	$scope.roleDelete = function() {
		if ($scope.roleInfos.length == 0){
			layer.alert('请选择要删除的角色', {shade: false}); 
			return;
		}
		var deleteConfirm = confirm("确定是否删除选中内容?");
		var data = $scope.roleInfos;
		//删除选中角色
		if(deleteConfirm == true){
			$http({
				method : 'POST',
				url : '../rest/deleteRole',
				data : data,
				headers : {
					"Content-Type" : "application/json"
				}
			}).then(function successCallback(response) {
				if(response.data.code != 0 && response.data.code != '0') {
					layer.confirm(response.data.msg, {
					  btn: ['确定'] //按钮
					}, function(){
						layer.close(layer.index);
						$scope.roleInfos = [];
						$rootScope.searchRole();
					});
				} else {
					layer.confirm('删除成功', {
					  btn: ['确定'] //按钮
					}, function(){
						$scope.roleInfos = [];
						$rootScope.searchRole();
						layer.closeAll('dialog');
					});
				}
			}, function errorCallback(response) {
				console.log(response);
				layer.alert('删除异常！', {shade: false}); 
			});
		}
	};
}]);

//模态框对应的Controller
app.controller('roleOperationCtrl', function($rootScope, $scope, $http, $modalInstance, commonService, data, roleStatusDict) {
	$scope.roleStatusDict = roleStatusDict;
	$scope.roleInfos = data;
	$scope.roleInfo = {};
	
	function initOrgNames() {
		$scope.roleInfo.roleStatus = roleStatusDict[0].entryId;
		if($scope.roleInfos.length == 1) {
			localStorage.setItem("roleSrcData", JSON.stringify($scope.roleInfos[0]));
		}
	}
	initOrgNames();
	
	//添加角色
	$scope.addRole = function() {
		$scope.roleInfo.modifyOperatorId = localStorage.getItem("modifyOpeId");
		var data = $scope.roleInfo;
		if(!data.roleName) {
			layer.alert('请填写角色名', {shade: false}); 
			return;
		}
		
		//添加角色
		$http({
			method : 'POST',
			url : '../rest/addRole',
			data : data,
			headers : {
				"Content-Type" : "application/json"
			}
		}).then(function successCallback(response) {
			layer.alert('添加成功', {shade: false}); 
			$rootScope.searchRole();
			$modalInstance.close();
		}, function errorCallback(response) {
			console.log(response);
		});
					
	};
	
	//编辑角色
	$scope.roleUpdate = function() {
		$scope.roleInfos[0].modifyOperatorId = localStorage.getItem("modifyOpeId");
		var data = $scope.roleInfos[0];
		if(!data.roleName) {
			layer.alert('请填写角色名', {shade: false}); 
			return;
		}
		$http({
			method : 'POST',
			url : '../rest/editRole',
			data : data,
			headers : {
				"Content-Type" : "application/json"
			}
		}).then(function successCallback(response) {
			layer.confirm('修改成功', {
			  btn: ['确定'] //按钮
			}, function(){
				$rootScope.searchRole();
				$modalInstance.close();
				layer.closeAll('dialog');
			});
			//layer.alert('修改成功', {shade: false}); 
		}, function errorCallback(response) {
			console.log(response);
		});	
	};
	
	$scope.cancel = function() {
    	$modalInstance.close("cancle");
    }
});