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

function onBodyDown1(event) {
	console.log(event.target.id);
	if (!(event.target.id == "orgTreeInput1" || event.target.id == "orgTreeDiv1" || $(event.target).parents("#orgTreeDiv1").length>0)) {
		hideMenu1();
	}
}

function hideMenu1() {
	$("#orgTreeDiv1").slideToggle("fast");
	$("body").unbind("mousedown", onBodyDown1);
}
var app = angular.module('myApp', ['cdrBlackQueryApp','cdrBlackUserQueryApp']);

var cdrBlackQueryApp = angular.module('cdrBlackQueryApp', ['commonServiceModule', 'treeControl']);

cdrBlackQueryApp.controller('cdrBlackQueryController', function($scope, $http, commonService) {

	$scope.callTypes = [];
	var getCallTypes = function(response) {
		$scope.callTypes = [];
		if(response.data && response.data.length > 0) {
			$scope.callTypes = response.data;
			$scope.selCallType = response.data[0];
		}
	};
	commonService.getDictDefByDictClass(1021, false, getCallTypes);

	$scope.beginDate = getBeginDate();
	$scope.endDate = yesterdayDate();
	
	$scope.resetBtn = function() {
		$scope.data.orgName = undefined;
		$scope.data.operatorId = undefined;
		$scope.data.callNumber = undefined;
		$scope.data.calledNumber = undefined;
		$scope.beginDate = getBeginDate();
		$scope.endDate = yesterdayDate();
		$scope.selCallType = $scope.callTypes[0];
	};
	
	// 查询前先校验日期是否在一个月内
	$scope.validateData = function() {
		var date1 = new Date($scope.beginDate);
		var date2 = new Date($scope.endDate);
		var month1 =date1.getFullYear()+""+date1.getMonth();
		var month2 = date2.getFullYear()+""+date2.getMonth();
		console.log(month1+""+month2);
		if (month1 != month2) {
			alert("请确认查询周期在同一个月内");
			$scope.beginDate = getBeginDate();
			$scope.endDate = yesterdayDate();
		}
	}
	// ===下载===
	$scope.download = function() {
		var index = layer.load(1, {
			  shade: [0.5,'#000'],time:180000
			});
		$http({
			method : 'POST',
			url : '../rest/createBlackCdrListExcel',
			data : $scope.data,
			headers : {
				"Content-Type" : "application/json ; charset=UTF-8"
			},
			timeout:180000
		}).then(function successCallback(response) {
			if ("success" == response.data.status) {
				var fileName = response.data.fileName;
				downloadTemplate('../rest/downloadExcel', 'fileName', fileName);
				layer.close(index);
			} else {
				layer.close(index);
				alert("Excel生成失败");
			}
		}, function errorCallback(response) {
			layer.close(index);
			alert("数据量太大无法下载，请重新选择下载条件！");
			console.log(response);
		});
	};
	
	//机构下拉框
	$scope.orgData = [];
	var getOrgData = function(response) {
		$scope.orgData = response.data;
	}
	commonService.httpPost('../rest/showHeadOrgs', null, getOrgData);
	
	$scope.editUserInfoForDetail = function(list, index){
		$scope.callNumber = list[index].callNumber;
		$scope.calledNumber = list[index].calledNumber;
		$scope.billMonth = list[index].billMonth;
		$scope.callType = list[index].callTypeId;
		$scope.cdrId = list[index].cdrId;
		$('#addModal2').modal('show');
		
	};
	
	$scope.confirm = function() {
		$("#addModal2").modal('hide');
		if($scope.operatorName==""||$scope.operatorName==null){
			alert("请输入用户姓名！");
		}else if(typeof($scope.node) == "undefined" || $scope.node == {} || typeof($scope.node.orgId) == "undefined"){
			alert("请选择机构！");
		}else if(confirm("确认添加该用户？")){
			var data = {
				"callNumber" : $scope.callNumber,
				"billMonth" : $scope.billMonth,
				"operatorName" : $scope.operatorName,
				"orgId" : $scope.node.orgId,
				"callType" : $scope.callType,
				"cdrId" : $scope.cdrId
			};
			console.log(data);
			$http({
				method : 'POST',
				url : '../rest/editUserInfoForDetail',
				data : data,
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
					alert("用户更新失败");
				}
				$("#addModal2").modal('hide');
				$scope.operatorName ="";
				$scope.node = {};
				$scope.orgNames = "";
			}, function errorCallback(response) {
				alert("请求失败");
				console.log(response);
			});
		}
	};
	
	$scope.cancel = function() {
		$("#addModal2").modal('hide');
		$scope.node = {};
		$scope.orgNames = "";
	}
	
	$scope.treeOptions = {  
	   nodeChildren: "childOrgList",  
	   dirSelectable: true,  
	   multiSelection: false,
	   equality: function(a, b,$scope) {
		   if (!a || !b)
			   return false;
		   return a.orgId == b.orgId;
	   }
	} 
	
	$scope.showTree = function(){
		var infoObj = $("#orgTreeInput");
		console.log($scope.orgData);
		$("#orgTreeDiv").css({width: infoObj.width() + "px"}).slideToggle("fast");
		$("body").bind("mousedown", onBodyDown);
	};
	
	$scope.showSelected = function(node) {
		$scope.orgNames = node.orgNameFull;
		hideMenu();
	}
});

var cdrBlackUserQueryApp = angular.module('cdrBlackUserQueryApp', ['commonServiceModule', 'treeControl']);

cdrBlackUserQueryApp.controller('cdrBlackUserQueryController', function($scope, $http, commonService) {
	
	$scope.callTypes = [];
	var getCallTypes = function(response) {
		$scope.callTypes = [];
		if(response.data && response.data.length > 0) {
			$scope.callTypes = response.data;
			$scope.selCallType = response.data[0];
		}
	};
	commonService.getDictDefByDictClass(1021, false, getCallTypes);
	
	//机构下拉框
	$scope.orgData = [];
	var getOrgData = function(response) {
		$scope.orgData = response.data;
	}
	commonService.httpPost('../rest/showHeadOrgs', null, getOrgData);

	$scope.beginDate = getBeginDate();
	$scope.endDate = yesterdayDate();
	$scope.resetBtn = function() {
		$scope.data.orgName = undefined;
		$scope.data.operatorId = undefined;
		$scope.data.ip = undefined;
		$scope.data.callNumber = undefined;
		$scope.beginDate = getBeginDate();
		$scope.endDate = yesterdayDate();
		$scope.selCallType = $scope.callTypes[0];
	};
	
	// 查询前先校验日期是否在一个月内
	$scope.validateData = function() {
		var date1 = new Date($scope.beginDate);
		var date2 = new Date($scope.endDate);
		var month1 =date1.getFullYear()+""+date1.getMonth();
		var month2 = date2.getFullYear()+""+date2.getMonth();
		console.log(month1+""+month2);
		if (month1 != month2) {
			alert("请确认查询周期在同一个月内");
			$scope.beginDate = getBeginDate();
			$scope.endDate = yesterdayDate();
		}
	}
	
	// ===下载===
	$scope.download = function() {
		var index = layer.load(1, {
			  shade: [0.5,'#000'],time:180000
			});
		$http({
			method : 'POST',
			url : '../rest/createCdrBlackUserExcel',
			data : $scope.data,
			headers : {
				"Content-Type" : "application/json ; charset=UTF-8"
			},
			timeout:180000
		}).then(function successCallback(response) {
			if ("success" == response.data.status) {
				var fileName = response.data.fileName;
				downloadTemplate('../rest/downloadExcel', 'fileName', fileName);
				layer.close(index);
			} else {
				layer.close(index);
				alert("Excel生成失败");
			}
		}, function errorCallback(response) {
			layer.close(index);
			alert("数据量太大无法下载，请重新选择下载条件！");
			console.log(response);
		});
	};
	
	$scope.editUserInfo = function(list, index){
		$scope.callNumber = list[index].callNumber;
		$scope.billMonth = list[index].billMonth;
		$scope.callType = list[index].callTypeId;
		console.log($scope.callType);
		$('#addModal1').modal('show');
		
	};
	
	$scope.confirm = function() {
		console.log($scope.callNumber);
		$("#addModal1").modal('hide');
		if($scope.operatorName==""||$scope.operatorName==null){
			alert("请输入用户姓名！");
		}else if(typeof($scope.node) == "undefined" || $scope.node == {} || typeof($scope.node.orgId) == "undefined"){
			alert("请选择机构！");
		}else if(confirm("确认添加该用户？")){
			var data = {
				"callNumber" : $scope.callNumber,
				"billMonth" : $scope.billMonth,
				"operatorName" : $scope.operatorName,
				"orgId" : $scope.node.orgId,
				"callType" : $scope.callType
			};
			$http({
				method : 'POST',
				url : '../rest/editUserInfo',
				data : data,
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
					alert("用户更新失败");
				}
				$("#addModal1").modal('hide');
				$scope.operatorName ="";
				$scope.node = {};
				$scope.orgNames = "";
			}, function errorCallback(response) {
				alert("请求失败");
				console.log(response);
			});
		}
	};
	
	$scope.cancel = function() {
		$("#addModal1").modal('hide');
		$scope.operatorName ="";
		$scope.node = {};
		$scope.orgNames = "";
	}
	
	$scope.treeOptions = {  
	   nodeChildren: "childOrgList",  
	   dirSelectable: true,  
	   multiSelection: false,
	   equality: function(a, b,$scope) {
	    	if (!a || !b)
	            return false;
	    	return a.orgId == b.orgId;
	   }
	} 
	
	$scope.showTree = function(){
		var infoObj = $("#orgTreeInput1");
		$("#orgTreeDiv1").css({width: infoObj.width() + "px"}).slideToggle("fast");
		$("body").bind("mousedown", onBodyDown1);
	};
	
	$scope.showSelected = function(node) {
		$scope.orgNames = node.orgNameFull;
		hideMenu1();
	}
});