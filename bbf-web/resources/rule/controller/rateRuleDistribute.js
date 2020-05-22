var app = angular.module("RateRuleDistribute", [ "ngTable","commonServiceModule","treeControl","angularjs-dropdown-multiselect"]);
app.controller("RateRuleDistributeCtrl",["$scope", "$timeout","$http","$filter","NgTableParams","commonService", function($scope, $timeout,$http,$filter,NgTableParams,commonService){
		
	var initialData={};
	var self = this;
	//选择的规则关系
	$scope.relaSelected = [] ;
	//选择的规则的ID
	$scope.ruleSelected = [] ; 
	//机构树的过滤条件
	$scope.predicate = "";
	//多机构树的过滤条件
	$scope.predicate2 = "";
	// 多单位查询条件
	$scope.queryRule = {};
	// 多单位查询部分-获取被选中行的规则信息
	$scope.startRuleInfos = [];
	// 验证时间类型yyyy-MM-dd正则表达式
	var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;
	//关系查询条件
	$scope.searchRela={};
	//规则查询条件
	$scope.selectRule={"rateStatus":1};
	
	//操作员ID
 	$scope.operatorId = localStorage.getItem("modifyOpeId");
 		
 	//加载资费类型数据字典
	commonService.getDictDefByDictClass(1001, false, function(response) {
		$scope.feeTypes=response.data;
		console.log($scope.feeTypes);
	});
	//加载规则状态数据字典
	commonService.getDictDefByDictClass(1004, false, function(response) {
		console.log(response.data);
		$scope.statusTypes = response.data;
	});
	//加载规则收费类型数据字典
	commonService.getDictDefByDictClass(1006, false, function(response) {
		console.log(response.data);
		$scope.rateTypes = response.data;
	});
	//加载规则范围数据字典
	commonService.getDictDefByDictClass(1019, false, function(response) {
		console.log(response.data);
		$scope.scopeTypes = response.data;
	});
	
 	//重置规则关系查询信息
	$scope.reset = function() {
		$scope.selectedOrg = [];
		$scope.searchRela={};
		$("#searchlogmin").val(null);
		$("#searchlogmax").val(null);
	};
	
 	//条件查询资费规则关系
	$scope.searchRateRuleRela = function() {
		var orgIdList=[];
        if($scope.selectedOrg.length > 0) {
			for(var k = 0; k<$scope.selectedOrg.length; k ++) {
				orgIdList.push($scope.selectedOrg[k].orgId);
			}
		}
        $scope.selectdata={};
        $scope.searchRela.inureDate = document.getElementById("searchlogmin").value;
		$scope.searchRela.expireDate = document.getElementById("searchlogmax").value;
		angular.copy($scope.searchRela,$scope.selectdata);
		$scope.selectdata.orgIdList=orgIdList;
		$scope.selectdata.modifyOperator=$scope.operatorId;
        console.log($scope.selectdata);
		$http({
			method : 'POST',
			url : '../rest/selectRateRuleRela',
			data : $scope.selectdata,
			headers : {
				"Content-Type" : "application/json ; charset=UTF-8"
			}
		}).then(function successCallback(response) {
			console.log(response.data);
			if(angular.equals(response.data,[])){
				layer.alert('暂无符合的数据！', {shade: false});
				$scope.rateRuleRelaList = [{},{},{},{},{},{},{},{},{},{}];
				loadTable($scope.rateRuleRelaList);
			}
			else if(response.data.length % 10 != 0 ){
				console.log(response.data.length);
				$scope.rateRuleRelaList = response.data;
				for(var i = response.data.length % 10 ; i<10 ; i++){
					$scope.rateRuleRelaList.push({});
				}
				loadTable($scope.rateRuleRelaList);
			}else{
				loadTable(response.data);
			}
		}, function errorCallback(response) {
			layer.alert('查询失败', {shade: false}); 
		});	
	};
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
					orgs.push({orgId: data[i].orgId, orgNameFull: data[i].orgNameFull, value: data[i].orgName});
					parseOrgs(orgs, data[i].childOrgList);
				} else {
					orgs.push({orgId: data[i].orgId, orgNameFull: data[i].orgNameFull, value: data[i].orgName});
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
 	
	
	//获取所有规则关系
	$http({
	method : 'POST',
	url : '../rest/selectRateRuleRela',
	data:initialData,
	headers : {
		"Content-Type" : "application/json ; charset=UTF-8"
	}
}).then(function successCallback(response) {
	console.log(response.data);
	if(angular.equals(response.data,[])){
		layer.alert('暂无数据！', {shade: false});
		$scope.rateRuleRelaList = [{},{},{},{},{},{},{},{},{},{}];
		loadTable($scope.rateRuleRelaList);
	}
	else if(response.data.length % 10 != 0 ){
		$scope.rateRuleRelaList = response.data;
		for( i = response.data.length % 10 ; i<10 ; i++){
			$scope.rateRuleRelaList.push({});
		}
		loadTable($scope.rateRuleRelaList);
	}else{
		loadTable(response.data);
	}
	
}, function errorCallback(response) {
	layer.alert('数据初始化失败', {shade: false}); 
});
	
	//加载页面表格数据
 	function loadTable(dataList){
     	self.tableParams = new NgTableParams({count: 10}, {
     		counts: [],
     		dataset: dataList
    	});
     }
     //规则关系表复选框状态验证
	$scope.isRelaSelected = function(rateRuleRelaId) {
		if(typeof(rateRuleRelaId) == "undefined")
			return;
		var pos = -1;
		for(var i=0; i<$scope.relaSelected.length; i++){
			if(rateRuleRelaId == $scope.relaSelected[i]) {
				pos = i;
				break;
			}
		}
		return pos != -1;
	}
	//选择一个关系
	$scope.selectARela = function(rateRuleRelaId) {
		if(typeof(rateRuleRelaId) == "undefined"||rateRuleRelaId==0)
			return;
		var pos = -1;
		for(var i=0; i<$scope.relaSelected.length; i++){
			if(rateRuleRelaId == $scope.relaSelected[i]) {
				pos = i;
				break;
			}
		}
		if(pos == -1) {
			  $scope.relaSelected.push(rateRuleRelaId) ;  
		} else {
			$scope.relaSelected.splice(pos,1) ;
		}
		console.log($scope.relaSelected);
	}
    
	//检查规则是否已选择
	$scope.isRuleSelected = function(rateRule) {
		if(angular.equals(rateRule,{}))
			return;
		var pos = -1;
		for(var i=0; i<$scope.ruleSelected.length; i++){
			if(rateRule.rateItemId == $scope.ruleSelected[i].rateItemId) {
				pos = i;
				break;
			}
		}
		return pos != -1;
	}
	//选择一个规则
	$scope.selectARule = function(rateRule) {
		if(angular.equals(rateRule,{})||rateRule.scope==1)
			return;
		var pos = -1;
		for(var i=0; i<$scope.ruleSelected.length; i++){
			if(rateRule.rateItemId == $scope.ruleSelected[i].rateItemId) {
				pos = i;
				break;
			}
		}
		if(pos == -1) {
			  $scope.ruleSelected.push(rateRule) ;  
		} else {
			$scope.ruleSelected.splice(pos,1) ;
		}
		console.log($scope.ruleSelected);
	}

	// 加载页面时，加载机构树
	$http({
		method : 'POST',
		url : '../rest/showOrgTree',
		headers : {
			"Content-Type" : "application/json ; charset=UTF-8"
		}
	}).then(function successCallback(response) {
		 console.log(response.data);
		 $scope.treedata=response.data;
		 $scope.orgTree =response.data;//多单位
		 loadTable2( [{},{},{},{},{}]);
		 loadTable3( [{},{},{},{},{}]);
	}, function errorCallback(response) {
		console.log("can not receive response");
		layer.alert('机构加载失败', {shade: false}); 
	});
	
//单位树定义
	$scope.treeOptions = {
		    nodeChildren: "childOrgList",
		    dirSelectable: true,
		}
	
	$scope.showSelected = function(sel) {
//		$scope.childOrgInfos=[];
//		document.getElementById("orgTree").style.height = "100%";
//		document.getElementById("childOrg").style.height = "0%";
        console.log(sel.orgId);
//        $scope.selectoperator(sel.orgId);
//        if(sel.orgId==5387){
//        	document.getElementById("orgTree").style.height = "50%";
//        	document.getElementById("childOrg").style.height = "45%";
//        	$scope.selectChildOrg(sel.orgId);
//        }
        $scope.objectId=sel.orgId;
        $scope.relaSelected = [];
        $scope.objectType=1;
        $scope.selectRela(sel.orgId);
    };
    
  //重置树检索
    $scope. resetFilter=function(){
    	$scope.predicate = "";
    };
  //重置树检索
    $scope. resetFilter2=function(){
    	$scope.predicate2 = "";
    };
//------------------------------------------------------------------------------------------
//多单位树定义
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

	//查询子机构
 	$scope.selectChildOrg = function(orgId) {
    	console.log(orgId);
    	$scope.orgdata={superOrgId:orgId}
 		$http({
			method : 'POST',
			url : '../rest/showChildOrg',
			data : $scope.orgdata,
			headers : {
				"Content-Type" : "application/json; charset=UTF-8"
			}
		}).then(function successCallback(response) {
			if(angular.equals(response.data,[])){
				layer.alert('暂无分配信息', {shade: false}); 
				$scope.childOrgList = [{},{},{},{},{}];
				loadTable6($scope.childOrgList);
			}
			else if(response.data.length % 5 != 0 ){
				$scope.childOrgList = response.data;
				for( i = response.data.length % 5 ; i<5 ; i++){
					$scope.childOrgList.push({});
				}
				console.log($scope.childOrgList);
				loadTable6($scope.childOrgList);
			}else{
				loadTable6(response.data);
			}
			
		}, function errorCallback(response) {
			layer.alert('查询失败', {shade: false}); 
		});	
 	}
 	
    $scope.childOrgInfos=[];
    //判断子机构是否已选择
    $scope.isChildOrgSelected=function(info){
    	if(angular.equals(info,{})){
			return;
			}
		var pos = -1;
		for(var i=0; i<$scope.childOrgInfos.length; i++){
			if(info.orgId == $scope.childOrgInfos[i].orgId) {
				pos = i;
				break;
			}
		}
		return pos != -1;
    }
    
    $scope.searchChildOrgRela=function(info){
    	if(angular.equals(info,{})){
			return;
			}
		var pos = -1;
		for(var i=0; i<$scope.childOrgInfos.length; i++){
			if(info.orgId == $scope.childOrgInfos[i].orgId) {
				pos = i;
				break;
			}
		}
		if(pos == -1) {
			$scope.childOrgInfos = [];
			$scope.childOrgInfos.push(info);
		} else {
			$scope.childOrgInfos.splice(pos, 1);
		}
 		$scope.objectType=1;
		$scope.objectId=info.orgId;
		$scope.relaSelected = [];
		$scope.selectRela(info.orgId);
    }
 	//加载页面子机构数据
 	function loadTable6(dataList){
     	self.tableParams6 = new NgTableParams({count: 5}, {
     		counts: [],
     		dataset: dataList
    	});
     }
 	
 	
  //加载页面员工表格数据
 	function loadTable2(dataList){
     	self.tableParams2 = new NgTableParams({count: 5}, {
     		counts: [],
     		dataset: dataList
    	});
     }
  //查询机构内的员工
	$scope.selectoperator = function(orgId) {
		$scope.operatorInfos = [];
		var orgIdData=orgId;
		$scope.objectType=1;
		$scope.objectId=orgId;
		var orgData={"orgId":orgIdData};
		console.log(orgIdData);
		$http({
			method : 'POST',
			url : '../rest/findOrgIdAndOrgNameByOrgId',
			data : orgData,
			headers : {
				"Content-Type" : "application/json; charset=UTF-8"
			}
		}).then(function successCallback(response) {
			
			if(angular.equals(response.data,[])){
				$scope.operatorList = [{},{},{},{},{}];
				loadTable2($scope.operatorList);
			}
			else if(response.data.length % 5 != 0 ){
				console.log(response.data.length);
				$scope.operatorList = response.data;
				for( i = response.data.length % 5 ; i<5 ; i++){
					$scope.operatorList.push({});
				}
				console.log($scope.operatorList);
				loadTable2($scope.operatorList);
			}else{
				loadTable2(response.data);
			}
			
		}, function errorCallback(response) {
			layer.alert('查询员工失败', {shade: false}); 
		});	
	}
	
	//加载页面已分配规则表格数据
 	function loadTable3(dataList){
     	self.tableParams3 = new NgTableParams({count: 5}, {
     		counts: [],
     		dataset: dataList
    	});
     }
 	$scope.operatorInfos = [];
 	$scope.isOperatorSelected = function(info) {
		if(angular.equals(info,{})){
			return;
			}
		var pos = -1;
		for(var i=0; i<$scope.operatorInfos.length; i++){
			if(info.operatorId == $scope.operatorInfos[i].operatorId) {
				pos = i;
				break;
			}
		}
		return pos != -1;
	}
 
 	
 	//查询员工规则
 	$scope.selectOperatorRuleRela = function(info) {
 		if(angular.equals(info,{})){
			return;
			}
		var pos = -1;
		for(var i=0; i<$scope.startRuleInfos.length; i++){
			if(info.operatorId == $scope.operatorInfos[i].operatorId) {
				pos = i;
				break;
			}
		}
		if(pos == -1) {
			$scope.operatorInfos = [];
			$scope.operatorInfos.push(info);
		} else {
			$scope.operatorInfos.splice(pos, 1);
		}
 		$scope.objectType=2;
		$scope.objectId=info.operatorId;
		$scope.relaSelected = [];
		$scope.selectRela(info.operatorId);
 	}
 	
	//通过对象ID查询已分配规则
	$scope.selectRela = function(objectId) {
		$scope.objectdata={"objectId":objectId,"objectType":$scope.objectType};
		console.log($scope.objectdata);
		$http({
			method : 'POST',
			url : '../rest/getRelaByobjectId',
			data : $scope.objectdata,
			headers : {
				"Content-Type" : "application/json; charset=UTF-8"
			}
		}).then(function successCallback(response) {
			if(angular.equals(response.data,[])){
				layer.alert('暂无分配信息', {shade: false}); 
				$scope.relaList = [{},{},{},{},{}];
				loadTable3($scope.relaList);
			}
			else if(response.data.length % 5 != 0 ){
				$scope.relaList = response.data;
				for( i = response.data.length % 5 ; i<5 ; i++){
					$scope.relaList.push({});
				}
				console.log($scope.relaList);
				loadTable3($scope.relaList);
			}else{
				loadTable3(response.data);
			}
			
		}, function errorCallback(response) {
			layer.alert('查询失败', {shade: false}); 
		});	
	}
	
	//加载页面规则表格数据
 	function loadTable4(dataList){
     	self.tableParams4 = new NgTableParams({count: 5}, {
     		counts: [],
     		dataset: dataList
    	});
     }
 	
 	//加载页面规则表格数据(多机构)
 	function loadTable5(dataList){
     	self.tableParams5 = new NgTableParams({count: 10}, {
     		counts: [],
     		dataset: dataList
    	});
     }
	 
 	
	 //条件查询规则
		$scope.searchCostRule = function() {
			$scope.ruleSelected = [] ; 
			$scope.selectRuledata={};
			$scope.selectRule.inureDate = document.getElementById("searchrulelogmin").value;
			$scope.selectRule.expireDate = document.getElementById("searchrulelogmax").value;
			angular.copy($scope.selectRule,$scope.selectRuledata);
			if(typeof($scope.selectRuledata.inureDate) != "undefined" && $scope.selectRuledata.inureDate.match(reg) == null){
				$scope.selectRuledata.inureDate = undefined;
			}
			if(typeof($scope.selectRuledata.expireDate) != "undefined" && $scope.selectRuledata.expireDate.match(reg) == null){
				$scope.selectRuledata.expireDate = undefined;
			}
			console.log($scope.selectRuledata);
			$http({
				method : 'POST',
				url : '../rest/selectRateRuleToDistribute',
				data : $scope.selectRuledata,
				headers : {
					"Content-Type" : "application/json; charset=UTF-8"
				}
			}).then(function successCallback(response) {
				if(angular.equals(response.data,[])){
					layer.alert('暂无符合的可分配规则', {shade: false}); 
					$scope.ruleList = [{},{},{},{},{}];
					loadTable4($scope.ruleList);
				}
				else if(response.data.length % 5 != 0 ){
					$scope.ruleList = response.data;
					for( i = response.data.length % 5 ; i<5 ; i++){
						$scope.ruleList.push({});
					}
					console.log($scope.ruleList);
					loadTable4($scope.ruleList);
				}else{
					loadTable4(response.data);
				}
			}, function errorCallback(response) {
				layer.alert('规则查询失败', {shade: false}); 
			});	
		};
		//初始化查询规则
		$scope.searchCostRule();
		
		$scope.resetSearch = function() {
			$scope.selectRule={"rateStatus":1};
			$("#searchrulelogmin").val(null);
			$("#searchrulelogmax").val(null);
		};
		
		//移除已分配规则
		 $scope.deleteRateRuleRela = function() {
				var checkedRelaArray = new Array();
				checkedRelaArray = $scope.relaSelected;
				console.log(checkedRelaArray);
				var operatorId=$scope.operatorId;
				if (checkedRelaArray.length != 0){
					var data = {"rateRuleRelaIdArray":checkedRelaArray,"operatorId":operatorId};
					var deleteConfirm = confirm("确定是否移除选中内容?");
					//删除选中分配
					if(deleteConfirm == true){
						$http({
							method : 'POST',
							url : '../rest/deleteRateRuleRela',
							data : data,
							headers : {
								"Content-Type" : "application/json; charset=UTF-8"
							}
						}).then(function successCallback(response) {
							$scope.relaSelected = [] ; 
							$scope.selectRela($scope.objectId);
						}, function errorCallback(response) {
							layer.alert('移除失败', {shade: false}); 
						});
					}
				}
				else
					layer.alert('请勾选已分配规则', {shade: false}); 
			};
		 
			//分配新规则
			$scope.addRateRuleRela=function() {
				var relaList = new Array();
				var nowDate = new Date();
				console.log($scope.objectId);
				if(	typeof($scope.objectId)== "undefined" ){
					layer.alert('请选择分配对象', {shade: false}); 
					return;
				}
				if($scope.ruleSelected.length<1){
					layer.alert('请勾选规则', {shade: false}); 
					return;
				}
				for(var i = 0; i < $scope.ruleSelected.length; i++ ) {
					if($scope.objectType==2 && $scope.ruleSelected[i].cdrType==4){
						layer.alert('网络专线类资费规则不能分配给员工', {shade: false});
						return;
					}
					var rela = {"objectType":$scope.objectType,"objectId":$scope.objectId,"rateItemId":"","cdrType":"",
								"rateType":"","priority":"",
								"inureDate":"","expireDate":"","status":"1","modifyOperator":$scope.operatorId,"createDate":nowDate};
					rela.rateItemId =  $scope.ruleSelected[i].rateItemId;
					rela.cdrType =	$scope.ruleSelected[i].cdrType;
					rela.rateType =  $scope.ruleSelected[i].rateType;
					rela.priority =	$scope.ruleSelected[i].priority;
					rela.inureDate =  new Date($scope.ruleSelected[i].inureDate).getTime();
					rela.expireDate =  new Date($scope.ruleSelected[i].expireDate).getTime();
					relaList.push(rela);
				}
				console.log(relaList);
				var data = {"relaLists":relaList,"operatorId":$scope.operatorId,"objectId":$scope.objectId};
				console.log(data);
				$http({
					method : 'POST',
					url : '../rest/addRateRuleRela',
					data : data,
					headers : {
						"Content-Type" : "application/json; charset=UTF-8"
					}
				}).then(function successCallback(response) {
					layer.alert(response.data.status, {shade: false}); 
					$scope.relaSelected = []; 
					$scope.selectRela($scope.objectId);
				}, function errorCallback(response) {
					layer.alert('添加失败', {shade: false}); 
				});
			}
			//启用规则关系
			$scope.startUsing = function() {
				var checkedRelaArray = new Array();
				checkedRelaArray = $scope.relaSelected;
				console.log(checkedRelaArray);
				var operatorId=$scope.operatorId;
				if (checkedRelaArray.length != 0){
					var data = {"rateRuleRelaIdArray":checkedRelaArray,"operatorId":operatorId,"status":"1"};
						$http({
							method : 'POST',
							url : '../rest/updateRateRuleRelaStatus',
							data : data,
							headers : {
								"Content-Type" : "application/json; charset=UTF-8"
							}
						}).then(function successCallback(response) {
							$scope.selectRela($scope.objectId);
							layer.alert('启用成功', {shade: false}); 
						}, function errorCallback(response) {
							layer.alert('启用失败', {shade: false}); 
						});
					}
			};
			//禁用规则关系
			$scope.forbidden = function() {
				var checkedRelaArray = new Array();
				checkedRelaArray = $scope.relaSelected;
				console.log(checkedRelaArray);
				var operatorId=$scope.operatorId;
				if (checkedRelaArray.length != 0){
					var data = {"rateRuleRelaIdArray":checkedRelaArray,"operatorId":operatorId,"status":"2"};
						$http({
							method : 'POST',
							url : '../rest/updateRateRuleRelaStatus',
							data : data,
							headers : {
								"Content-Type" : "application/json; charset=UTF-8"
							}
						}).then(function successCallback(response) {
							$scope.selectRela($scope.objectId);
							layer.alert('禁用成功', {shade: false}); 
						}, function errorCallback(response) {
							layer.alert('禁用失败', {shade: false}); 
						});
					}
			};
		//导出规则关系
		$scope.ruleRelaExport = function() {
	        $scope.exportdata={};
			angular.copy($scope.selectdata,$scope.exportdata);
			$scope.exportdata.modifyOperator=$scope.operatorId;
			console.log($scope.exportdata);
			$http({
				method : 'POST',
				url : '../rest/exportRateRuleRela',
				data : $scope.exportdata,
				headers : {
					"Content-Type" : "application/json ; charset=UTF-8"
				}
			}).then(function successCallback(response) {
				if(response.data.status=="success"){
					$scope.download(response.data.filename); 
				}else{
					layer.alert(response.data.info, {shade: false}); 
				}	
			}, function errorCallback(response) {
				layer.alert('导出失败', {shade: false});
			});
		};
		//下载文件
		$scope.download = function(filename) {
			downloadTemplate('../rest/downloadExcel', 'fileName', filename);
		};
//----------------------------------------------------------------------------------------------
//多机构
		 //重置条件查询规则（多机构）
		$scope.resetStartRule = function() {
			$scope.queryRule = {};
			$("#startlogmin").val(null);
			$("#startlogmax").val(null);
		};
		
		// 条件查询规则（多机构）
		$scope.queryRuleByParam = function() {
			console.log($scope.queryRule);
			$scope.startRuleInfos=[];
			$scope.nodes = [];
			$scope.queryRule.rateStatus = 1;
			$scope.queryRule.inureDate = document.getElementById("startlogmin").value;
			$scope.queryRule.expireDate = document.getElementById("startlogmax").value;
			if(typeof($scope.queryRule.inureDate) != "undefined" && $scope.queryRule.inureDate.match(reg) == null){
				$scope.queryRule.inureDate = undefined;
			}
			if(typeof($scope.queryRule.expireDate) != "undefined" && $scope.queryRule.expireDate.match(reg) == null){
				$scope.queryRule.expireDate = undefined;
			}
			console.log($scope.queryRule);
			$http({
				method : 'POST',
				url : '../rest/selectRateRuleToDistribute',
				data : $scope.queryRule,
				headers : {
					"Content-Type" : "application/json ; charset=UTF-8"
				}
			}).then(function successCallback(response) {
				if(angular.equals(response.data,[])){
					layer.alert('暂无符合的可分配规则', {shade: false}); 
					$scope.ruleToMoreList = [{},{},{},{},{},{},{},{},{},{}];
					loadTable5($scope.ruleToMoreList);
				}
				else if(response.data.length % 10 != 0 ){
					$scope.ruleToMoreList = response.data;
					for( i = response.data.length % 10 ; i<10 ; i++){
						$scope.ruleToMoreList.push({});
					}
					console.log($scope.ruleToMoreList);
					loadTable5($scope.ruleToMoreList);
				}else{
					loadTable5(response.data);
				}
			}, function errorCallback(response) {
				layer.alert('规则查询失败！', {shade: false});
			});
		};
		//多单位规则初始化查询
		$scope.queryRuleByParam();
		$scope.isSelected = function(info) {
			if(angular.equals(info,{}))
				return;
			var pos = -1;
			for(var i=0; i<$scope.startRuleInfos.length; i++){
				if(info.rateItemId == $scope.startRuleInfos[i].rateItemId) {
					pos = i;
					break;
				}
			}
			return pos != -1;
		}
		
		//选中一行规则
		$scope.selectStartRule = function(info) {
			if(angular.equals(info,{})||info.scope==1)
				return;
			console.log($scope.startRuleInfos);
			var pos = -1;
			for(var i=0; i<$scope.startRuleInfos.length; i++){
				if(info.rateItemId == $scope.startRuleInfos[i].rateItemId) {
					pos = i;
					break;
				}
			}
			if(pos == -1) {
				$scope.startRuleInfos = [];
				$scope.startRuleInfos.push(info);
				$scope.queryRuleByOrg(info.rateItemId);
			} else {
				$scope.startRuleInfos.splice(pos, 1);
				$scope.nodes = [];
				localStorage.setItem("orgSelectedTreeData", "");
			}
			console.log($scope.startRuleInfos);
		}
		
		// 查询拥有某个规则的单位
		$scope.queryRuleByOrg=function(rateItemId) {
			var relaSearchDate={"objectType":1,"rateItemId":rateItemId};
			$http({
				method : 'POST',
				url : '../rest/loadRateRuleRelaByRuleItemId',
				data : relaSearchDate,
				headers : {
					"Content-Type" : "application/json ; charset=UTF-8"
				}
			}).then(function successCallback(response) {
				console.log(response.data);
				$scope.nodes = JSON.parse(JSON.stringify(response.data).replace(/objectId/g, "orgId"));
				console.log($scope.nodes);
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
	        var deleteConfirm = confirm("是否对选择的机构分配这条规则?"); 
	        if (deleteConfirm == true){
	        	console.log($scope.startRuleInfos);
	        	console.log($scope.nodes);
	        	var rule = {"rateItemId":$scope.startRuleInfos[0].rateItemId,"modifyOperator":$scope.operatorId};
				var data = new Array();
				data.push(rule);
				var info=$scope.startRuleInfos[0];
				for(var i = 0; i < $scope.nodes.length; i++ ) {
					var rela = {"rateItemId":$scope.startRuleInfos[0].rateItemId,"cdrType":$scope.startRuleInfos[0].cdrType,
							"rateType":$scope.startRuleInfos[0].rateType,"priority":$scope.startRuleInfos[0].priority,
							"status":1,"modifyOperator":$scope.operatorId,"createDate":new Date()};
					rela.inureDate =  new Date($scope.startRuleInfos[0].inureDate).getTime();
					rela.expireDate = new Date($scope.startRuleInfos[0].expireDate);
					rela.objectId = $scope.nodes[i].orgId;
					rela.objectType=1;
					console.log(rela);
					data.push(rela);
				}
				if(!angular.equals(data,[])){
					
					$http({
						method : 'POST',
						url : '../rest/addRateRuleRelaByRuleId',
						data : data,
						headers : {
							"Content-Type" : "application/json ; charset=UTF-8"
						}
					}).then(function successCallback(response) {
						layer.alert('规则分配成功!', {shade: false});
						$scope.queryRuleByParam();
						$scope.selectStartRule(info);
					}, function errorCallback(response) {
						layer.alert('规则分配失败，请检查!', {shade: false});
					});
				}
				
	        }
		};
}]);