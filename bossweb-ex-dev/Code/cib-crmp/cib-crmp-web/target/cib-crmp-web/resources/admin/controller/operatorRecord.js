var app = angular.module('operRecord', ['commonServiceModule','angularjs-dropdown-multiselect']);

app.controller("operRecordCtrl", function($scope, $http, commonService) {
	
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
	
	//加载日志状态字典
	commonService.getDictDefByDictClass(1026, false, function(response) {
		$scope.syncStatusTypes = response.data;
		console.log($scope.syncStatusTypes);
	});
	
	$scope.beginDate =  new moment(new Date()).format('YYYYMM');
/*	$scope.endDate =  new moment(new Date()).format('YYYYMM') - 1;*/
	
	// 重置
	$scope.resetBtn = function() {
		$scope.data.unitName = null;
		$scope.data.notesId = null;
		$scope.data.logStatusType = null;
		$scope.selectedOrg = [];
		$scope.beginDate =  new moment(new Date()).format('YYYYMM');
		/*$scope.endDate =  new moment(new Date()).format('YYYYMM') - 1;*/
	};
	
});