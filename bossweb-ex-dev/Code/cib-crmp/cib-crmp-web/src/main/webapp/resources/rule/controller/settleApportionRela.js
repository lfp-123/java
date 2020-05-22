app = angular.module("relaManage", [ "ngTable","commonServiceModule","treeControl","angularjs-dropdown-multiselect"]);
app.controller("saRelaController",["$rootScope","$scope", "$timeout","$http","$filter","NgTableParams","commonService", function($rootScope, $scope, $timeout,$http,$filter,NgTableParams,commonService){
	
	var self = this;
	// 关系查询选中的单位Id集合
	$scope.selectedOrg = [];
	// 规范分配查询条件
	$scope.search = {};
	// 摊分规则查询条件
	$scope.query = {};
	// 多单位查询条件
	$scope.queryRule = {};
	// 获取选中的规则配置的ID
	$scope.selectedRule = [] ; 
	// 获取被选中行的规则信息
	$scope.ruleInfos = [];
	// 获取选中的组织规则的ID
	$scope.selectedOrgRela = [] ; 
	// 获取被选中行的规则分配信息
	$scope.relaInfos = [];
	// 当前被选择的机构Id
	var checkedOrgId = "";
	// 已启用的规则配置
	$scope.ruleList = {};
	// 多单位查询部分-获取被选中行的规则信息
	$scope.startRuleInfos = [];
 	// 操作员ID
 	var operatorId = localStorage.getItem("modifyOpeId");
	// 验证时间类型yyyy-MM-dd正则表达式
	var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;
	// 启用状态ID
	var startStatus = null;
	// 禁用状态ID
	var stopStatus = null;
	// 机构树的过滤条件
	$scope.predicate = "";
	
	//加载资费类型数据字典
	commonService.getDictDefByDictClass(1001, false, function(response) {
		console.log(response.data);
		//根据需求暂时去掉IP电话会议
		$scope.feeTypes = [];
		for(var i=0;i<response.data.length;i++){
		/*	if(response.data[i].entryId !=2){*/
				console.log(response.data[i]);
				$scope.feeTypes.push(response.data[i]);
		/*	}*/
		}
		console.log($scope.feeTypes);
	});
	
	//加载规则类型数据字典
	commonService.getDictDefByDictClass(1004, false, function(response) {
		console.log(response.data);
		$scope.statusTypes = response.data;
		for(var i=0; i< $scope.statusTypes.length; i++){
			if($scope.statusTypes[i].entryName == "启用") {;
				startStatus = $scope.statusTypes[i].entryId;
			}
			if($scope.statusTypes[i].entryName == "禁用") {
				stopStatus = $scope.statusTypes[i].entryId;
			}
		}
	});
	
	// 查询默认条件
	$scope.defCondition = {};
	
	// 查询默认摊分启用规则条件
	$scope.defRuleCondition = { status:startStatus };
	
	
	var succFun = function(response) {
		var orgs = [];
		parseOrgs(orgs, response.data);
		$scope.orgs = orgs;
	}
	
	commonService.httpPost('../rest/showOrgTree', null, succFun);
		
/*	function parseOrgs(orgs, data) {
		if(data.length > 0) {
			for(var i = 0; i<data.length; i ++) {
				if(data[i].childOrgList && data[i].childOrgList.length > 0) {
					orgs.push({orgId: data[i].orgId, orgNameFull: data[i].orgName + " (" + data[i].orgNameFull + ")", value: data[i].orgName});
					parseOrgs(orgs, data[i].childOrgList);
				} else {
					orgs.push({orgId: data[i].orgId, orgNameFull: data[i].orgName + " (" + data[i].orgNameFull + ")", value: data[i].orgName});
				}
			}
		} else {
			orgs.push(data);
		}
	}*/
	
	function parseOrgs(orgs, data) {
		for(var i = 0; i<data.length; i ++) {
			orgs.push({orgId: data[i].orgId, orgNameFull: data[i].orgNameFull, value: data[i].orgName});
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
	
 	//加载摊分规则分配记录表格数据
 	function loadRelaTable(dataList){
     	self.relaTableParams = new NgTableParams({count: 10}, {
     		counts: [],
     		dataset: dataList
    	});
     };
     
    // 加载页面时，加载机构树
 	$http({
 		method : 'POST',
 		url : '../rest/showOrgTree',
 		headers : {
 			"Content-Type" : "application/json ; charset=UTF-8"
 		}
 	}).then(function successCallback(response) {
 		$scope.treedata =response.data;
 		//多单位中的树
 		$scope.orgTree =response.data;
 		/*expandAll(response.data);
 		expandAllOrg(response.data);*/
 		console.log(response.data);
 	}, function errorCallback(response) {
 		console.log("can not receive response");
 	});
 	
 	/*expanded-nodes="expandedNodes"
 	function expandAll(datas) {
		if(datas.length > 0) {
			for(var i=0; i<datas.length; i++) {
				$scope.expandedNodes.push(datas[i]);
				if(datas[i].childOrgList.length > 0) {
					expandAll(datas[i].childOrgList);
				}
			}
		}
	}*/
 	
 	/*expanded-nodes="expandedOrgNodes"
	function expandAllOrg(datas) {
		if(datas.length > 0) {
			for(var i=0; i<datas.length; i++) {
				$scope.expandedOrgNodes.push(datas[i]);
				if(datas[i].childOrgList.length > 0) {
					expandAllOrg(datas[i].childOrgList);
				}
			}
		}
	}*/
 	
	$scope.treeOptions = {
	    nodeChildren: "childOrgList",
	    dirSelectable: true,
	}
	 	
 	$scope.ruleOrgTreeOptions = {
	    nodeChildren: "childOrgList",
	    dirSelectable: true,
	    multiSelection: true,
	    equality: function(a, b,$scope) {
	    	if (!a || !b)
	            return false;
	    	return a.orgId == b.orgId;
	    }
 	 }
    		
 	//加载摊分规则分配记录表格数据
 	function loadRuleTable(dataList){
     	self.ruleTableParams = new NgTableParams({count: 5}, {
     		counts: [],
     		dataset: dataList
    	});
     };
    		
 	//加载被选择单位所拥有的规则记录表格数据
 	function loadOrgRuleTable(dataList){
     	self.orgRuleTableParams = new NgTableParams({count: 5}, {
     		counts: [],
     		dataset: dataList
    	});
     };
     
 	//多单位查询部分-加载启用的摊分规则
  	function loadStartRules(dataList){
      	self.startRuleTableParams = new NgTableParams({count: 10}, {
      		counts: [],
      		dataset: dataList
     	});
      };
    
 	//获取选中规则的ID
     $scope.choiceOrgRela = function(info){
 		if(angular.equals(info,{})){
			return;
		}
        var pos = -1;
 		for(var i=0; i<$scope.relaInfos.length; i++){
 			if(info.apportionRelaId == $scope.relaInfos[i].apportionRelaId) {
 				pos = i;
 				break;
 			}
 		}
 		return pos != -1;
     };
     
	//获取选中要删除的组织规则的ID
    $scope.selectOrgRela = function(info){
		if(angular.equals(info,{}) || typeof(info.apportionRelaId) == "undefined"){
			return;
		}
    	var pos = -1;
		for(var i=0; i<$scope.relaInfos.length; i++){
			if(info.apportionRelaId == $scope.relaInfos[i].apportionRelaId) {
				pos = i;
				break;
			}
		}
		if(pos == -1) {
			$scope.relaInfos.push(info);
			$scope.selectedOrgRela.push(info.apportionRelaId);
		} else {
			$scope.relaInfos.splice(pos, 1);
			$scope.selectedOrgRela.splice(pos, 1);
		}
		console.log($scope.selectedOrgRela);
		console.log($scope.relaInfos);
    };
    
	//获取选中规则的ID
    $scope.choiceRule = function(info){
		if(angular.equals(info,{})){
			return;
		}
        var pos = -1;
		for(var i=0; i<$scope.ruleInfos.length; i++){
			if(info.apportionItemId == $scope.ruleInfos[i].apportionItemId) {
				pos = i;
				break;
			}
		}
		return pos != -1;
    };
    
    //选中一行规则
	$scope.selectRule = function(info) {
		if(angular.equals(info,{}) || angular.equals(info.scope,1)){
			return;
		}
		var pos = -1;
		for(var i=0; i<$scope.ruleInfos.length; i++){
			if(info.apportionItemId == $scope.ruleInfos[i].apportionItemId) {
				pos = i;
				break;
			}
		}
		if(pos == -1) {
			$scope.ruleInfos.push(info);
			$scope.selectedRule.push(info.apportionItemId);
		} else {
			$scope.ruleInfos.splice(pos, 1);
			$scope.selectedRule.splice(pos, 1);
		}
		console.log($scope.selectedRule);
		console.log($scope.ruleInfos);
	}
	
	// 规则分配模块查询
	// 查询重置
	$scope.reset = function() {
		$scope.search = angular.copy($scope.defCondition);
		//重置单位查询
		$scope.selectedOrg = [];
		$("#logmin").val("");
		$("#logmax").val("");
	};

	// 查询数据
	var queryData = function(data) {
		/*if(typeof(data.inureDate) != "undefined" && data.inureDate.match(reg) == null){
			data.inureDate = undefined;
		}
		if(typeof(data.expireDate) != "undefined" && data.expireDate.match(reg) == null){
			data.expireDate = undefined;
		}*/
		if(!angular.equals($("#logmin").val(),"")){
			data.inureDate = $("#logmin").val();
		}
		if(!angular.equals($("#logmax").val(),"")){
			data.expireDate = $("#logmax").val();
		}
		var orgIdList = new Array();
		if(!angular.equals($scope.selectedOrg,[])){
			for(var i = 0; i < $scope.selectedOrg.length; i++ ) {
				orgIdList.push($scope.selectedOrg[i].orgId);
			}
			data.orgIdList = orgIdList;
		}
		console.log(data);
		var resultData = {
			apportionRuleName : typeof(data.apportionRuleName) != "undefined" ? data.apportionRuleName :null,
			status : typeof(data.status) != "undefined" ? data.status :null,
			inureDate : typeof(data.inureDate) != "undefined" ? data.inureDate :null,
			expireDate : typeof(data.expireDate) != "undefined" ? data.expireDate :null,
			orgIdList : orgIdList,
			operatorId : operatorId
		};
		console.log(data);
		return resultData;
	};
	
	// 条件查询
	$scope.searchByParam = function() {
		var data = queryData($scope.search);
		$http({
			method : 'POST',
			url : '../rest/loadComQueryRelaByOrgParam',
			data : data,
			headers : {
				"Content-Type" : "application/json ; charset=UTF-8"
			}
		}).then(function successCallback(response) {
			console.log(response.data);
			if(angular.equals(response.data,[])){
				layer.alert('没有符合条件的规则！', {shade: false});
				var relaList = [{},{},{},{},{},{},{},{},{},{}];
				loadRelaTable(relaList);
			}
			else if(response.data.length % 10 != 0){
				var relaList = response.data;
				for(i = response.data.length % 10 ; i<10 ; i++){
					relaList.push({});
				}
				console.log(relaList);
				loadRelaTable(relaList);
			}else{
				loadRelaTable(response.data);
			}
		}, function errorCallback(response) {
	 		layer.alert('can not receive response', {shade: false});
		});
	};
	
	//加载摊分规则分配记录
	$scope.searchByParam();

	
	// 导出
	$scope.exportRela = function() {
		
		var exportConfirm = confirm("是否导出当前条件下的规则配置?");
		//删除选中角色
		if(exportConfirm == true){
			var param = queryData($scope.search);
			//操作员ID
			param.operatorId = operatorId;
			console.log(param);
			$http({
				method : 'POST',
				url : '../rest/exportSettleApportionRelaByParam',
				data : param,
				headers : {
					"Content-Type" : "application/json ; charset=UTF-8"
				}
			}).then(function successCallback(response) {
				console.log(response);
				if(angular.equals(response.data.result,"true")) {
					$scope.download(response.data.filename);
				}else{
					layer.alert(response.data.error, {shade: false});
				}
			}, function errorCallback(response) {
				layer.alert('导出失败!', {shade: false});
			});
		}

	};
	
	
	//下载
	$scope.download = function(filename) {
		downloadTemplate('../rest/downloadExcel', 'fileName', filename);
	};

	//重置机构拥有规则的选中控制
	function resetOrgRule(){
		// 获取选中的组织规则的ID
		$scope.selectedOrgRela = [] ; 
		// 获取被选中行的规则分配信息
		$scope.relaInfos = [];
	}
	
	//组织机构树的点击事件
	$scope.showSelected = function(node) {
		console.log(node);
		checkedOrgId = node.orgId;
		console.log(checkedOrgId);
		var data = {objectId : checkedOrgId};
		$http({
			method : 'POST',
			url : '../rest/loadSettleApportionRelaByOrgId',
			data : data,
			headers : {
				"Content-Type" : "application/json ; charset=UTF-8"
			}
		}).then(function successCallback(response) {
			console.log(response.data);
			resetOrgRule();
			fillRelaTable(response.data);
		}, function errorCallback(response) {
			layer.alert('can not receive response', {shade: false});
		});
	};
	
	//初始化组织机构关系Table
	fillRelaTable([]);
	
	function fillRelaTable(data){
		if(angular.equals(data,[])){
			var relaList = [{},{},{},{},{}];
			loadOrgRuleTable(relaList);
		}
		else if(data.length % 5 != 0){
			var relaList = data;
			for(i = data.length % 5 ; i < 5 ; i++){
				relaList.push({});
			}
			console.log(relaList);
			loadOrgRuleTable(relaList);
		}else{
			loadOrgRuleTable(data);
		}
	}
	
	//重置树的过滤条件
	$scope.resetFilter = function() {
		$scope.predicate = "";
	}
	
	//规则关系-删除
	$scope.delRela = function() {
		if(angular.equals(checkedOrgId,"")) {
			layer.alert('还未选择单位，请选择!', {shade: false}); 
			return;
		}
		if($scope.selectedOrgRela.length == 0) {
			layer.alert('请勾选要删除的分配规则！', {shade: false}); 
			return;
		}
		var data = {"relaIdJson":$scope.selectedOrgRela,"operatorId":operatorId,"orgId":checkedOrgId};
		var deleteConfirm = confirm("确定是否删除选中内容?");
		console.log(data);
		//删除选中角色
		if(deleteConfirm == true){
			$http({
				method : 'POST',
				url : '../rest/deleteSettleApportionRela',
				data : data,
				headers : {
					"Content-Type" : "application/json ; charset=UTF-8"
				}
			}).then(function successCallback(response) {
				layer.alert('删除成功！', {shade: false});
				// 清空选中的规则的ID
				$scope.selectedOrgRela = [] ; 
				// 清空被选中行的规则分配信息
				$scope.relaInfos = [];
				fillRelaTable(response.data);
			}, function errorCallback(response) {
				layer.alert('删除失败！请检查！', {shade: false});
			});
		}
	};
	
	//规则关系-新增
	$scope.addRela = function() {
		if(angular.equals(checkedOrgId,"")) {
			layer.alert('还未选择单位，请选择!', {shade: false}); 
			return;
		}
		if($scope.selectedRule.length == 0) {
			layer.alert('请选择要分配的规则！', {shade: false}); 
			return;
		}
		var relaList = new Array();
		for(var i = 0; i < $scope.selectedRule.length; i++ ) {
			var rela = {"objectId":checkedOrgId,"apportionItemId":"","inureDate":"","expireDate":"","status":startStatus,"operatorId":operatorId};
			console.log($scope.ruleInfos[i]);
			rela.apportionItemId = $scope.ruleInfos[i].apportionItemId;
			rela.inureDate = $scope.ruleInfos[i].inureDate;
			rela.expireDate = $scope.ruleInfos[i].expireDate;
			console.log(rela);
			relaList.push(rela);
		}
		console.log(relaList);
		if(!angular.equals(relaList,[])){
			var data = {"relaLists":relaList,"operatorId":operatorId,"orgId":checkedOrgId};
			console.log(data);
			$http({
				method : 'POST',
				url : '../rest/addSettleApportionRela',
				data : data,
				headers : {
					"Content-Type" : "application/json ; charset=UTF-8"
				}
			}).then(function successCallback(response) {
				layer.alert('分配成功!', {shade: false});
				fillRelaTable(response.data);
			}, function errorCallback(response) {
				layer.alert('分配失败，请检查!', {shade: false});
			});
		}
	};
	
	// 启用关系规则
	$scope.startRela = function() {
		if(angular.equals(startStatus,null)) {
			layer.alert('启用状态异常！', {shade: false}); 
			return;
		}
		if(angular.equals(checkedOrgId,"")) {
			layer.alert('还未选择单位，请选择!', {shade: false}); 
			return;
		}
		if($scope.selectedOrgRela.length == 0) {
			layer.alert('请勾选要启用的分配规则！', {shade: false}); 
			return;
		}
		var data = {"relaIdArray":$scope.selectedOrgRela,"relaStatus":startStatus,"operatorId":operatorId,"orgId":checkedOrgId};
		console.log(data);
		$http({
			method : 'POST',
			url : '../rest/modifySettleApportionRelaStatus',
			data : data,
			headers : {
				"Content-Type" : "application/json ; charset=UTF-8"
			}
		}).then(function successCallback(response) {
			layer.alert('分配规则启用成功！', {shade: false});
			fillRelaTable(response.data);
		}, function errorCallback(response) {
			layer.alert('规则启用失败！请检查', {shade: false}); 
		});
	};
	
	// 禁用关系规则
	$scope.stopRela = function() {
		if(angular.equals(stopStatus,null)) {
			layer.alert('禁用状态异常！', {shade: false}); 
			return;
		}
		if(angular.equals(checkedOrgId,"")) {
			layer.alert('还未选择单位，请选择!', {shade: false}); 
			return;
		}
		if($scope.selectedOrgRela.length == 0) {
			layer.alert('请勾选要禁用的规则！', {shade: false}); 
			return;
		}
		var data = {"relaIdArray":$scope.selectedOrgRela,"relaStatus":stopStatus,"operatorId":operatorId,"orgId":checkedOrgId};	
		console.log(data);
		$http({
			method : 'POST',
			url : '../rest/modifySettleApportionRelaStatus',
			data : data,
			headers : {
				"Content-Type" : "application/json ; charset=UTF-8"
			}
		}).then(function successCallback(response) {
			layer.alert('规则禁用成功！', {shade: false});
			fillRelaTable(response.data);
		}, function errorCallback(response) {
			layer.alert('规则禁用失败！请检查', {shade: false});
		});
	};
	
	//所有已启用摊分规则
	//查询已启用摊分规则重置条件
	$scope.resetRule = function() {
		$scope.query = angular.copy($scope.defRuleCondition);
		$("#rulelogmin").val("");
		$("#rulelogmax").val("");
	};
	
	//重置选中条件
	function resetRuleChoice(){
		// 获取选中的规则的ID
		$scope.selectedRule = []; 
		// 获取被选中行的规则信息
		$scope.ruleInfos = [];
	}
	
	// 条件查询
	$scope.queryByParam = function() {
		console.log($scope.query);
		$scope.query.status = startStatus == null ? 1 : startStatus;
		//展现全局规则
		$scope.query.scope = 1;
	/*	if(typeof($scope.query.inureDate) != "undefined" && $scope.query.inureDate.match(reg) == null){
			$scope.query.inureDate = undefined;
		}
		if(typeof($scope.query.expireDate) != "undefined" && $scope.query.expireDate.match(reg) == null){
			$scope.query.expireDate = undefined;
		}*/
		if(!angular.equals($("#rulelogmin").val(),"")){
			$scope.query.inureDate = $("#rulelogmin").val();
		}
		if(!angular.equals($("#rulelogmax").val(),"")){
			$scope.query.expireDate = $("#rulelogmax").val();
		}
		console.log($scope.query);
		$http({
			method : 'POST',
			url : '../rest/loadSettleApportionRuleByParam',
			data : $scope.query,
			headers : {
				"Content-Type" : "application/json ; charset=UTF-8"
			}
		}).then(function successCallback(response) {
			console.log(response.data);
			resetRuleChoice();
			if(angular.equals(response.data,[])){
				layer.alert('没有符合条件的规则！', {shade: false});
				var ruleList = [{},{},{},{},{}];
				loadRuleTable(ruleList);
			}
			else if(response.data.length % 5 != 0){
				var ruleList = response.data;
				for(i = response.data.length % 5 ; i < 5 ; i++){
					ruleList.push({});
				}
				loadRuleTable(ruleList);
			}else{
				loadRuleTable(response.data);
			}
		}, function errorCallback(response) {
			layer.alert('查询失败！', {shade: false});
		});
	};
	
	//所有已启用摊分规则-加载摊分规则配置记录
	$scope.queryByParam();
	
	//多单位查询部分
	// 查询重置
	$scope.resetStartRule = function() {
		$scope.queryRule = angular.copy($scope.defCondition);
		$("#startlogmin").val("");
		$("#startlogmax").val("");
	};
	
	function resetStartRule(){
		// 已启用的规则配置
		$scope.ruleList = {};
		// 多单位查询部分-获取被选中行的规则信息
		$scope.startRuleInfos = [];
		// 清空树节点
		$scope.nodes = [];
	}
	
	// 条件查询
	$scope.queryRuleByParam = function() {
		console.log($scope.queryRule);
		$scope.queryRule.status = startStatus == null ? 1 : startStatus;
		//展现全局规则
		$scope.queryRule.scope = 1;
/*		if(typeof($scope.queryRule.inureDate) != "undefined" && $scope.queryRule.inureDate.match(reg) == null){
			$scope.queryRule.inureDate = undefined;
		}
		if(typeof($scope.queryRule.expireDate) != "undefined" && $scope.queryRule.expireDate.match(reg) == null){
			$scope.queryRule.expireDate = undefined;
		}*/
		if(!angular.equals($("#startlogmin").val(),"")){
			$scope.queryRule.inureDate = $("#startlogmin").val();
		}
		if(!angular.equals($("#startlogmax").val(),"")){
			$scope.queryRule.expireDate = $("#startlogmax").val();
		}
		console.log($scope.queryRule);
		$http({
			method : 'POST',
			url : '../rest/loadSettleApportionRuleByParam',
			data : $scope.queryRule,
			headers : {
				"Content-Type" : "application/json ; charset=UTF-8"
			}
		}).then(function successCallback(response) {
			console.log(response.data);
			resetStartRule();			
			if(angular.equals(response.data,[])){
				layer.alert('没有符合条件的规则！', {shade: false});
				var ruleList = [{},{},{},{},{},{},{},{},{},{}];
				loadStartRules(ruleList);
			}
			else if(response.data.length % 10 != 0){
				var ruleList = response.data;
				for(i = response.data.length % 10; i<10 ; i++){
					ruleList.push({});
				}
				loadStartRules(ruleList);
			}else{
				loadStartRules(response.data);
			}
		}, function errorCallback(response) {
			layer.alert('查询失败！', {shade: false});
		});
	};
	
	//多单位查询部分-加载摊分规则配置记录
	$scope.queryRuleByParam();
	
	//重置树的过滤条件
	$scope.resetOrgFilter = function() {
		$scope.filterOrg = "";
	}
	
	$scope.isSelected = function(info) {
		var pos = -1;
		for(var i=0; i<$scope.startRuleInfos.length; i++){
			if(info.apportionItemId == $scope.startRuleInfos[i].apportionItemId) {
				pos = i;
				break;
			}
		}
		return pos != -1;
	}
	
	//选中一行规则
	$scope.selectStartRule = function(info) {
		//全局规则默认不选中
		if(angular.equals(info,{}) || angular.equals(info.scope,1)){
			return;
		}
		console.log($scope.startRuleInfos);
		var pos = -1;
		for(var i=0; i<$scope.startRuleInfos.length; i++){
			if(info.apportionItemId == $scope.startRuleInfos[i].apportionItemId) {
				pos = i;
				break;
			}
		}
		if(pos == -1) {
			$scope.startRuleInfos = [];
			$scope.startRuleInfos.push(info);
			queryRuleByOrg(info.apportionItemId);
		} else {
			$scope.startRuleInfos.splice(pos, 1);
			$scope.nodes = [];
			localStorage.setItem("orgSelectedTreeData", "");
		}
		console.log($scope.startRuleInfos);
	}
	
	// 查询拥有某个规则的单位
	function queryRuleByOrg(apportionItemId) {
		$http({
			method : 'GET',
			url : '../rest/loadSettleApportionRelaByRuleId?apportionItemId='+apportionItemId +'&time='+ new Date().getTime(),
			headers : {
				"Content-Type" : "application/json ; charset=UTF-8"
			}
		}).then(function successCallback(response) {
			console.log(response.data);
			$scope.nodes = JSON.parse(JSON.stringify(response.data).replace(/objectId/g, "orgId"));
			localStorage.setItem("orgSelectedTreeData", JSON.stringify($scope.nodes));
		}, function errorCallback(response) {
			layer.alert('查询失败！', {shade: false});
		});
	};
	
	//点击取消时返回之前选择的
	$scope.cancelSelected = function(){
		if($scope.startRuleInfos.length == 1) {
			$scope.nodes = JSON.parse(localStorage.getItem("orgSelectedTreeData"));
		}
	};
	
	// 更新规则关系
	$scope.updateRuleRela = function(){
		if($scope.startRuleInfos.length != 1) {
			layer.alert('请选择一条规则', {shade: false}); 
			return;
		}
        var deleteConfirm = confirm("是否将选中的规则分配给这些机构?"); 
        if (deleteConfirm == true){
        	console.log($scope.startRuleInfos);
        	console.log($scope.nodes);
			var data = new Array();
			for(var i = 0; i < $scope.nodes.length; i++ ) {
				var rela = {"apportionItemId":$scope.startRuleInfos[0].apportionItemId,"status":startStatus,"operatorId":operatorId};
				rela.inureDate = new Date($scope.startRuleInfos[0].inureDate);
				rela.expireDate = new Date($scope.startRuleInfos[0].expireDate);
				console.log($scope.nodes[i].orgId);
				rela.objectId = $scope.nodes[i].orgId;
				console.log(rela);
				data.push(rela);
			}
			if(angular.equals(data,[])){
				var rela = {"apportionItemId":$scope.startRuleInfos[0].apportionItemId,"operatorId":operatorId};
				data.push(rela);
			}
			console.log(data);
			$http({
				method : 'POST',
				url : '../rest/addSettleApportionRelaByRuleId',
				data : data,
				headers : {
					"Content-Type" : "application/json ; charset=UTF-8"
				}
			}).then(function successCallback(response) {
				layer.alert('规则分配成功!', {shade: false});
				$scope.queryRuleByParam();
			}, function errorCallback(response) {
				layer.alert('规则分配失败，请检查!', {shade: false});
			});
        }
	};
	 
}]);	