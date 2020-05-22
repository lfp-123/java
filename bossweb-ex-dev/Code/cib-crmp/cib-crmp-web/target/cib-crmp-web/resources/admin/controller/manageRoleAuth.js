var app = angular.module('mangeRoleAuth', ["ngTable", "commonServiceModule", "treeControl"]);
app.controller("appCtrl", ["$scope", "$http","NgTableParams","commonService", function($scope, $http, NgTableParams, commonService){
	var self = this;
	
	$scope.roleInfos = [];
	
	var setTaleParams = function(response) {
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
		self.tableParams = new NgTableParams({count: 10}, {counts:[], dataset: $scope.roleList});
	}
//	commonService.httpPost('../rest/showAllRoleGrid', null, setTaleParams);
	$scope.searchRole = function() {
		$scope.roleInfos = [];
		$scope.nodes = [];
		if (angular.isUndefined($scope.searchId)){
			$scope.searchId = null;
		}
		if (angular.isUndefined($scope.searchName)){
			$scope.searchName = null;
		}
		console.log($scope.searchId);
		if($scope.searchId != '' && $scope.searchId != null) {
			if (!/^\-?\d*$/.test($scope.searchId) || $scope.searchId < 0) {
				layer.alert('角色编号只支持数字', {shade: false}); 
				return;
			}
		}
		var roleName = $scope.searchName;
		if($scope.searchName) {
			roleName = roleName.replace(/%/g, "/%").replace(/_/g, "/_");
		}
		var data = {"roleId":$scope.searchId, "roleName":roleName, "roleStatus":0};
		
		commonService.httpPost('../rest/showAllRoleGrid', data, setTaleParams);
	};
	$scope.searchRole();
	
	var roleStatusNames = {};
	var successCallback = function(response) {
		$scope.roleStatusDict = response.data;
		if(response.data.length > 0) {
			for(var i=0; i<response.data.length; i++) {
				console.log(response.data[i].entryd);
				console.log(response.data)
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
	
	//展示所有资源
	$scope.getResourceData = function(response) {
		$scope.resourceData = response.data;
		expandAll(response.data);
		if($scope.node) {
			$scope.showSelected($scope.node);
		}
	}
	commonService.httpPost('../rest/showAuthTree', null, $scope.getResourceData);
	
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
	
	//从数据字典获取资源类型
	var getResourceTypeDict = function(response) {
		$scope.resourceTypeDict = response.data;
	}
	commonService.getDictDefByDictClass(1007, false, getResourceTypeDict);
	
	$scope.predicate = "";
    $scope.comparator = false;
	$scope.treeOptions = {  
	   nodeChildren: "childResourceList",  
	   dirSelectable: true,  
	   multiSelection: true,
	   injectClasses: {  
	       ul: "tree",  
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
		var isAdd = false;
		for(var i=0; i<$scope.nodes.length; i++) {
			if($scope.nodes[i].resourceId == node.resourceId) {
				isAdd = true;
				break;
			}
		}
		if(isAdd) {
			if(node.superResourceId != -1) {
				addSuperResource(node.superResourceId, $scope.resourceData);
			}
			if(node.childResourceList.length > 0) {
				addSubResource(node.childResourceList);
			}
		} else {
			if(node.childResourceList.length > 0) {
				removeSubResource(node.childResourceList);
			}
		}
	}
	
	var removeSubResource = function(datas) {
		for(var i=0; i<datas.length; i++) {
			for(var j=0; j<$scope.nodes.length; j++) {
				if($scope.nodes[j].resourceId == datas[i].resourceId) {
					$scope.nodes.splice(j, 1);
					break;
				}
			}
			if(datas[i].childResourceList.length > 0) {
				removeSubResource(datas[i].childResourceList);
			}
		}
	}
	
	var addSubResource = function(datas) {
		for(var i=0; i<datas.length; i++) {
			var isFind = false;
			for(var j=0; j<$scope.nodes.length; j++) {
				if($scope.nodes[j].resourceId == datas[i].resourceId) {
					isFind = true;
					break;
				}
			}
			if(!isFind) {
				$scope.nodes.push(datas[i]);
			}
			if(datas[i].childResourceList.length > 0) {
				addSubResource(datas[i].childResourceList);
			}
		}
	}
	
	var addSuperResource = function(superResourceId, datas) {
		for(var i=0; i<$scope.nodes.length; i++) {
			if($scope.nodes[i].resourceId == superResourceId) {
				return;
			}
		}
		if(datas.length > 0) {
			var isFind = false;
			for(var i=0; i<datas.length; i++) {
				if(datas[i].resourceId == superResourceId) {
					isFind = true;
					$scope.nodes.push(datas[i]);
					if(datas[i].superResourceId != -1) {
						addSuperResource(datas[i].superResourceId, $scope.resourceData);
					}
				}
				if(!isFind && datas[i].childResourceList.length > 0) {
					addSuperResource(superResourceId, datas[i].childResourceList);
				}
			}
		}
	}
	
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
		if($scope.roleInfos.length == 1) {
			$scope.roleInfos[0].resourceList = JSON.parse(localStorage.getItem("roleAuthSrcData"));
		}
		var pos = -1;
		for(var i=0; i<$scope.roleInfos.length; i++){
			if(info.roleId == $scope.roleInfos[i].roleId) {
				pos = i;
				break;
			}
		}
		if(pos == -1) {
			$scope.roleInfos = [];
			$scope.roleInfos.push(info);
			var nodes = [];
			for(var i=0; i<info.resourceList.length; i++) {
				initRoleAuth(info.resourceList[i], $scope.resourceData, nodes);
			}
			info.resourceList = nodes;
			localStorage.setItem("roleAuthSrcData", JSON.stringify(info.resourceList), info.resourceList);
			$scope.nodes = info.resourceList;
		} else {
			$scope.roleInfos.splice(pos, 1);
			$scope.nodes = [];
			localStorage.setItem("roleAuthSrcData", JSON.stringify([]));
		}
	}
	
	var initRoleAuth = function(info, datas, nodes) {
		if(datas.length > 0) {
			for(var i=0; i<datas.length; i++) {
				if(datas[i].resourceId == info.resourceId) {
					nodes.push(datas[i]);
					break;
				}
				if(datas[i].childResourceList.length > 0) {
					initRoleAuth(info, datas[i].childResourceList, nodes);
				}
			}
		}
	}
	
	//点击取消时返回之前选择的
	$scope.cancelSelected = function(){
		if($scope.roleInfos.length == 1) {
			$scope.nodes = JSON.parse(localStorage.getItem("roleAuthSrcData"));
			$scope.roleInfos[0].resourceList = $scope.nodes;
		}
	};
	
	//重置查询条件
	$scope.resetSearch = function(){
		$scope.searchId = null;
		$scope.searchName = null;
	};
	
	//编辑角色资源关系
	$scope.updateRoleAuth = function(){
		if($scope.roleInfos.length != 1) {
			layer.alert('请选择一个角色', {shade: false}); 
			return;
		}
        var deleteConfirm = confirm("是否修改该组角色资源关系?"); 
        if (deleteConfirm == true){
        	$scope.roleInfos[0].modifyOperatorId = localStorage.getItem("modifyOpeId");
			var data = $scope.roleInfos[0];
			
			$http({
				method : 'POST',
				url : '../rest/addRoleResource',
				data : data,
				headers : {
					"Content-Type" : "application/json ; charset=UTF-8"
				}
			}).then(function successCallback(response) {
				layer.alert('修改成功', {shade: false}); 
				localStorage.setItem("roleAuthSrcData", JSON.stringify(data.resourceList));
				$scope.searchRole();
				commonService.httpPost('../rest/showAuthTree', null, $scope.getResourceData);
			}, function errorCallback(response) {
				console.log(response);
			});
		
        }
					
	};

}]);
	
	
	