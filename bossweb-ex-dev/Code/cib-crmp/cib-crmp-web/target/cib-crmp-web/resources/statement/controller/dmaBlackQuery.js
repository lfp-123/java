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
var app = angular.module('myApp', ['dmaBlackQueryApp','dmaBlackUserQueryApp']);
var dmaBlackQueryApp = angular.module('dmaBlackQueryApp', ['commonServiceModule', 'treeControl']);

dmaBlackQueryApp.controller('dmaBlackQueryController', function($scope, $http, commonService) {
	
	$scope.beginDate = getBeginDate();
	$scope.endDate = yesterdayDate();
	$scope.resetBtn = function() {
		$scope.data.orgName = undefined;
		$scope.data.operatorId = undefined;
		$scope.data.roomId = undefined;
		$scope.data.ip = undefined;
		$scope.beginDate = getBeginDate();
		$scope.endDate = yesterdayDate();
	};
	$scope.validateData = function() {
		if ($scope.data.roomId!=undefined&&!/^[0-9]*$/.test($scope.data.roomId)) {
			alert("请输入数字");
			$scope.data.roomId = undefined;
		}
	}
	// ===下载===
	$scope.download = function() {
		var index = layer.load(1, {
			  shade: [0.5,'#000'],time:180000
			});
		$http({
			method : 'POST',
			url : '../rest/createBlackDmaListExcel',
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
		$scope.conferenceNumber = list[index].conferenceNumber;
		$scope.startTime = list[index].startTime;
		$scope.endTime = list[index].endTime;
		$scope.confUuid = list[index].confUuid;
		$scope.holdingTime = list[index].holdingTime;
		$scope.orgId = list[index].orgId;
		$scope.orgName = list[index].orgName;
		$scope.billMonth = list[index].billMonth;
		if(list[index].otherFee=='0.0'){
			$scope.addFlag = false;
		}else{
			$scope.addFlag = true;
		}
		$('#addModal2').modal('show');
		
	};
	
	$scope.confirm = function() {
		if(typeof($scope.node) != "undefined" && $scope.node != {} && typeof($scope.node.orgId) != "undefined"){
			$scope.orgId = $scope.node.orgId;
		}
		$("#addModal2").modal('hide');
		if(confirm("确认编辑该话单？")){
			var data = {
					"roomId" : $scope.conferenceNumber, 
					"startTime" : $scope.startTime,
					"endTime" : $scope.endTime,
					"confUuid" : $scope.confUuid,
					"holdingTime" : $scope.holdingTime,
					"orgId" : $scope.orgId,
					"addFlag" : $scope.addFlag,
					"billMonth" : $scope.billMonth
			};
			console.log(data);
			$http({
				method : 'POST',
				url : '../rest/addInsuranceFee',
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
					alert("编辑失败");
				}
				$("#addModal2").modal('hide');
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

var dmaBlackUserQueryApp = angular.module('dmaBlackUserQueryApp', ['commonServiceModule', 'treeControl']);

dmaBlackUserQueryApp.controller('dmaBlackUserQueryController', function($scope, $http, commonService) {

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
		$scope.data.roomId = undefined;
		$scope.data.ip = undefined;
		$scope.beginDate = getBeginDate();
		$scope.endDate = yesterdayDate();
	};
	$scope.validateData = function() {
		if ($scope.data.roomId!=undefined&&!/^[0-9]*$/.test($scope.data.roomId)) {
			alert("请输入数字");
			$scope.data.roomId = undefined;
		}
	}
	// ===下载===
	$scope.download = function() {
		var index = layer.load(1, {
			  shade: [0.5,'#000'],time:180000
			});
		$http({
			method : 'POST',
			url : '../rest/createDmaBlackUserExcel',
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
		$scope.conferenceNumber = list[index].conferenceNumber;
		$scope.billMonth = list[index].billMonth;
		$scope.selectedOrg = [];
		$scope.orgId = 0;
		$('#addModal1').modal('show');
		
	};
	
	$scope.confirm = function() {
		$("#addModal1").modal('hide');
		if(typeof($scope.node) != "undefined" && $scope.node != {} && typeof($scope.node.orgId) != "undefined"){
			$scope.orgId = $scope.node.orgId;
		}
		if($scope.orgId==0){
			alert("请选择机构！");
		}else if(confirm("确认添加该部门？")){
			var data = {
				"roomId" : $scope.conferenceNumber,
				"billMonth" : $scope.billMonth,
				"orgId" : $scope.orgId
			};
			$http({
				method : 'POST',
				url : '../rest/editOrgInfo',
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