var app = angular.module('queryNotActiveUserReport', ['commonServiceModule','angularjs-dropdown-multiselect']);

app.controller("queryNotActiveUserReportCtrl", function($scope, $http, commonService) {
	
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
	
	// 设置日期
	var nowdays = new Date();
	var year = nowdays.getFullYear();
	var month = nowdays.getMonth();
	
	if(month == 0){
		month = 12;
		year = year-1;
	}
	if (month < 10) {
		month = "0" + month;
	}
	
	$scope.beginDate = (year + "" + month) - 0;
	$scope.endDate = (year + "" + month) - 0;
	
	// 重置
	$scope.resetBtn = function() {
		$scope.data.thresholdValue = null;
		$scope.data.unitName = null;
		$scope.selectedOrg = [];
		$scope.beginDate = (year + "" + month) - 0;
		$scope.endDate = (year + "" + month) - 0;
	};
	
	// 导出
	$scope.exportBtn = function() {
		var myForm = document.createElement("form");
		myForm.action = "../rest/exportExcel?businessType=4";
		myForm.method = "POST";
		myForm.style.display = "none";
		$scope.data.businessType = "4";
		for (var item in $scope.data) {
			var opt = document.createElement("input");
			opt.type = "text";
			opt.id = item;
			opt.name = item;
			opt.value = $scope.data[item];
			myForm.appendChild(opt);
		}
		document.body.appendChild(myForm);
		myForm.submit();
	};
	
	// 预览
	$scope.previewBtn = function() {
		$http({
	        method: 'POST',
	        url: "../rest/previewReport?businessType=4",
	        data: $scope.data
	    }).then(function successCallback(response) {
	    	var index = layer.open({
	    		type: 2,
	    		title: "预览",
	    		content: "../pages/queryFeeListReport.html"
	    	});
	    	layer.full(index);
	    }, function errorCallback(response) {
	        layer.alert("加载失败");
	    });
	};
	
	$scope.validateData = function() {
		if ($scope.data.thresholdValue == undefined) return;
		if (!/^\-?\d*$/.test($scope.data.thresholdValue) 
				&& !/^[+-]?(0|([1-9]\d*))(\.\d+)?$/g.test($scope.data.thresholdValue)) {
			layer.alert("请输入整数或小数");
			$scope.resetBtn();
		}
	}
	
});