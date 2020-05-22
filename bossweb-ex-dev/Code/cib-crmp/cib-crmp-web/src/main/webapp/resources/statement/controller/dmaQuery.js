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

var app = angular.module('dmaQueryApp', ['commonServiceModule', 'angularjs-dropdown-multiselect', 'treeControl']);

app.controller('dmaQueryController', function($scope, $http, commonService) {
	
	$scope.beginDate = getBeginDate();
	$scope.endDate = yesterdayDate();
	$scope.partCounts = [];
	
	// 绘制下拉机构树
	$scope.operatorLevel = localStorage.getItem("operatorLevel");
	$scope.cdrTypes = [];
	
	$scope.selectedOrg = [];
	var succFun = function(response) {
		var orgs = [];
		parseOrgs(orgs, response.data);
		$scope.orgs = orgs;
	}
	commonService.httpPost('../rest/showOrgTree', null, succFun);
	
	function parseOrgs(orgs, data) {
		if(data.length > 0) {
			for(var i = 0; i<data.length; i ++) {
				if(data[i].childOrgList && data[i].childOrgList.length > 0) {
					parseOrgs(orgs, data[i].childOrgList);
				} else {
					orgs.push({orgId: data[i].orgId, orgNameFull: data[i].orgName + " (" + data[i].orgNameFull + ")", value: data[i].orgName});
				}
			}
		} else {
			orgs.push(data);
		}
	}
	
	$scope.orgsSettings = {
		showCheckAll: true,
		showUncheckAll: true,
		enableSearch: true,
		smartButtonMaxItems: 2,
		clearSearchOnClose: true,
		displayProp: "orgNameFull",
		idProperty: "orgId",
		scrollableHeight: '300px', 
		scrollable: true,
		smartButtonTextConverter: function(itemText, originalItem) {
			return originalItem.value;
		}
	};
	
	$scope.orgTexts = {
		buttonDefaultText: "全部",
		checkAll: "全选",
		uncheckAll: "全不选"
		
	}
	
	commonService.getDictDefByDictClass(1025, true, function(response) {
		$scope.partCounts = [];
		if(response.data && response.data.length > 0) {
			console.log(response.data);
			$scope.partCounts = response.data;
			$scope.selPartCount = response.data[0];
		}
	});
	
	$scope.resetBtn = function() {
		$scope.data.orgName = undefined;
		$scope.selectedOrg = [];
		$scope.data.loginName = undefined;
		$scope.data.roomId = undefined;
		$scope.selPartCount = $scope.partCounts[0];
		$scope.beginDate = getBeginDate();
		$scope.endDate = yesterdayDate();
		$scope.data.operatorName = undefined;
	};
	
	// ===下载===
	$scope.download = function() {
		var index = layer.load(1, {
			  shade: [0.5,'#000'],time:180000
			});
		$http({
			method : 'POST',
			url : '../rest/createDmaListExcel',
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
	
	$scope.validateData = function() {
		if ($scope.data.roomId!=undefined&&!/^[0-9]*$/.test($scope.data.roomId)) {
			alert("请输入数字");
			$scope.data.roomId = undefined;
		}
	}
	
	//机构下拉框
	$scope.orgData = [];
	var getOrgData = function(response) {
		$scope.orgData = response.data;
	}
	commonService.httpPost('../rest/showHeadOrgs', null, getOrgData);
	
	$scope.addInsuranceFee = function(list, index){
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
		if($scope.orgId != 0){
			for(var i=0; i<$scope.orgData.length; i++){
				if($scope.orgId == $scope.orgData[i].orgId) {
					$scope.node = $scope.orgData[i];
					$scope.orgNames = $scope.orgData[i].orgNameFull;
					break;
				}
			}
		}
		$('#addModal').modal('show');
		
	};
	
	$scope.confirm = function() {
		if(typeof($scope.node) != "undefined" && $scope.node != {} && typeof($scope.node.orgId) != "undefined"){
			$scope.orgId = $scope.node.orgId;
		}
		$("#addModal").modal('hide');
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
					alert("保障费增加失败");
				}
				$scope.selectedOrg = [];
				$("#addModal").modal('hide');
				$scope.node = {};
				$scope.orgNames = "";
			}, function errorCallback(response) {
				alert("请求失败");
				$scope.selectedOrg = [];
				console.log(response);
			});
		}
	};
	
	$scope.cancel = function() {
		$scope.selectedOrg = [];
		$("#addModal").modal('hide');
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
		$("#orgTreeDiv").css({width: infoObj.width() + "px"}).slideToggle("fast");
		$("body").bind("mousedown", onBodyDown);
	};
	
	$scope.showSelected = function(node) {
		$scope.orgNames = node.orgNameFull;
		hideMenu();
	}
});