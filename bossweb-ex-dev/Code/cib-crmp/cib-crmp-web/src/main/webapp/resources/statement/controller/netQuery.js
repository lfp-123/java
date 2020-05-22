var app = angular.module('netQueryApp', ['commonServiceModule', 'angularjs-dropdown-multiselect']);

app.controller('netQueryController', function($scope, $http, commonService) {
	
	// 绘制下拉机构树
	$scope.operatorLevel = localStorage.getItem("operatorLevel");
	$scope.sdata = [0];
	$scope.xdata = [''];
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

	$scope.beginDate = getBeginDate();
	$scope.endDate = yesterdayDate();
	$scope.resetBtn = function() {
		$scope.selectedOrg = [];
		$scope.beginDate = getBeginDate();
		$scope.endDate = yesterdayDate();
	};
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
			url : '../rest/createNetListExcel',
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

});