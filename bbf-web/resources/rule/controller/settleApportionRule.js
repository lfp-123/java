app = angular.module("ruleManage", [ "ngTable","commonServiceModule"]);
app.controller("saRController",["$scope", "$timeout","$http","$filter","NgTableParams","commonService", function($scope, $timeout,$http,$filter,NgTableParams,commonService){
	
	var self = this;
	// 表格数据
    $scope.ruleList = {};
    // 新增数据
    $scope.data = {};
    // 更新数据
    $scope.updData = {};
    // 搜索框数据
 	$scope.search = {};
	// 获取选中的规则的ID
	$scope.selected = []; 
	// 获取被选中行的规则信息
	$scope.ruleInfos = [];
 	// 操作员ID
 	var operatorId = localStorage.getItem("modifyOpeId");
 	// 查询默认条件
	$scope.defCondition = {};
	// 验证时间类型yyyy-MM-dd正则表达式
	var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;
	// 启用状态ID
	var startStatus = null;
	// 禁用状态ID
	var stopStatus = null;
	
	//加载资费类型数据字典
	commonService.getDictDefByDictClass(1001, false, function(response) {
		console.log(response.data);
		//根据需求暂时去掉IP电话会议
		$scope.feeTypes = [];
		for(var i=0;i<response.data.length;i++){
			/*if(response.data[i].entryId !=2){*/
				console.log(response.data[i]);
				$scope.feeTypes.push(response.data[i]);
			/*}*/
		}
		console.log($scope.feeTypes);
	});
	
	//加载资费类型数据字典
	commonService.getDictDefByDictClass(1019, false, function(response) {
		console.log(response.data);
		$scope.ruleScope = response.data;
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
		
		//默认增加的规则状态为禁用
		$scope.data = {
			cdrType : 1,
			status : stopStatus,
			inureDate : $filter('date')(new Date(), "yyyy-MM-01"),
			expireDate : "3000-01-01",
			scope : 0
		};
		
	});
		
 	//加载页面表格数据
 	function loadTable(dataList){
     	self.tableParams = new NgTableParams({count: 10}, {
     		counts: [],
     		dataset: dataList
    	});
     };

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
		if(pos == -1) {
			$scope.ruleInfos.push(info);
			$scope.selected.push(info.apportionItemId);
		} else {
			$scope.ruleInfos.splice(pos, 1);
			$scope.selected.splice(pos, 1);
		}
		console.log($scope.selected);
		console.log($scope.ruleInfos);
	}
 	
	//提交数据检查
	function checkDataSubmission(data){
		console.log(data);
		if(angular.equals(data,{})){
			layer.alert("规则信息不能为空,请检查!");
			return false;
		} 
		if(getBLen(data.apportionRuleName) > 32 || typeof(data.apportionRuleName) == "undefined" || angular.equals(data.apportionRuleName,"")){
			layer.alert("摊分规则名称为空或名称过长,请检查!");
			return false;
		}
		if(typeof(data.cdrType) == "undefined" || angular.equals(data.cdrType,"") ){
			layer.alert("资费不能为空,请检查!");
			return false;
		}
		console.log(data.ratio);
		if(typeof(data.ratio) == "undefined" || angular.equals(data.ratio,"") || !/^(0.\d+|0|1)$/.test(data.ratio)){
			layer.alert("摊分比例不能为空,格式保留小数点后2位,请检查!");
			return false;
		}
		if(typeof(data.status) == "undefined" || angular.equals(data.status,"")){
			layer.alert("规则状态不能为空,请检查!");
			return false;
		}
		if(typeof(data.inureDate) == "undefined" || angular.equals(data.inureDate,"")){
			layer.alert("生效时间不能为空,请检查!");
			return false;
		}
		if(typeof(data.expireDate) == "undefined" || angular.equals(data.expireDate,"")){
			layer.alert("失效时间不能为空,请检查!");
			return false;
		}
		if(typeof(data.apportionDesc) != "undefined" && getBLen(data.apportionDesc) > 512){
			layer.alert("备注说明过长，请删减!");
			return false;
		}
		return true;
	}
	
	var getBLen = function(str) {
		if (str == null) return 0;
	    return str.replace(/[^\x00-\xff]/g,"**").length;
	}
	
	//重置选中条件
	function resetChoice(){
		// 获取选中的规则的ID
		$scope.selected = []; 
		// 获取被选中行的规则信息
		$scope.ruleInfos = [];
	}

	
    // 条件查询
	$scope.searchByParam = function() {
	/*	//使用ng-model方式取值和这个插件冲突
		if(typeof($scope.search.inureDate) != "undefined" && $scope.search.inureDate.match(reg) == null){
			$scope.search.inureDate = undefined;
		}
		if(typeof($scope.search.expireDate) != "undefined" && $scope.search.expireDate.match(reg) == null){
			$scope.search.expireDate = undefined;
		}*/
		if(!angular.equals($("#logmin").val(),"")){
			$scope.search.inureDate = $("#logmin").val();
		}
		if(!angular.equals($("#logmax").val(),"")){
			$scope.search.expireDate = $("#logmax").val();
		}
		console.log($scope.search);
		$http({
			method : 'POST',
			url : '../rest/loadSettleApportionRuleByParam',
			data : $scope.search,
			headers : {
				"Content-Type" : "application/json ; charset=UTF-8"
			}
		}).then(function successCallback(response) {
			console.log(response.data);
			//重置选中条件
			resetChoice();
			if(angular.equals(response.data,[])){
				layer.alert('没有符合条件的规则！', {shade: false});
				$scope.ruleList = [{},{},{},{},{},{},{},{},{},{}];
				loadTable($scope.ruleList);
			}
			else if(response.data.length % 10 != 0 ){
				console.log(response.data.length);
				$scope.ruleList = response.data;
				for(i = response.data.length % 10 ; i<10 ; i++){
					$scope.ruleList.push({});
				}
				console.log($scope.ruleList);
				loadTable($scope.ruleList);
			}else{
				loadTable(response.data);
			}
			
		}, function errorCallback(response) {
			layer.alert('查询失败！', {shade: false});
		});
	};
	
	$scope.searchByParam();
	
	// 查询重置
	$scope.reset = function() {
		$scope.search = angular.copy($scope.defCondition);
		$("#logmin").val("");
		$("#logmax").val("");
	};
	
	// 导出
	$scope.exportRule = function() {
		
		var exportConfirm = confirm("是否导出当前条件下的规则配置?");
		//导出选中角色
		if(exportConfirm == true){
			var param = $scope.search;
			//操作员ID
			param.operatorId = operatorId;
			console.log(param);
			$http({
				method : 'POST',
				url : '../rest/exportSettleApportionRuleByParam',
				data : param,
				headers : {
					"Content-Type" : "application/json ; charset=UTF-8"
				}
			}).then(function successCallback(response) {
				console.log(response.data.result);
				console.log(angular.equals(response.data.result,"true"));
				if(angular.equals(response.data.result,"true")) {
					$scope.download(response.data.filename);
				}else{
					layer.alert(response.data.error, {shade: false});
				}
			}, function errorCallback(response) {
				layer.alert('导出失败！'+response.data.error, {shade: false});
			});
		}

	};
	
	// 模版下载
	$scope.downloadRuleTemplate= function(){
		var form = document.createElement('form');
		document.body.appendChild(form);
		form.style.display = "none";
		form.action = '../rest/downloadTemplate';
		form.id = 'excel';
		form.method = 'post';
		var newElement = document.createElement("input");
		newElement.setAttribute("type", "hidden");
		newElement.name = 'filePath';
		newElement.value = '/resources/rule/template/SettleRuleTemplate.xls';
		form.appendChild(newElement);
		form.submit();
	}
	
	//下载
	$scope.download = function(filename) {
		downloadTemplate('../rest/downloadExcel', 'fileName', filename);
	};
	
	// 导入
	$scope.importRule = function() {
		var fileName = $('#upfile').val();
		if(!angular.equals(fileName,"") && angular.equals(fileName.substring(fileName.indexOf(".")+1,fileName.length),"xls")){
			var form = new FormData();
	        var file = document.getElementById("fileUpload").files[0];
	        console.log(file);
	        form.append('file', file);
	        form.append('operatorId', operatorId);
	        console.log(form);
	      	$http({
	      		method : 'POST',
	      		url : '../rest/importSettleApportionRule',
	      		 data: form,
	             headers: {'Content-Type': undefined},
	             transformRequest: angular.identity
	      	}).then(function successCallback(response) {
	      		console.log(response.data);
	      		/*if(response.data.indexOf("fileUpMaxError")!=-1){
	      			layer.alert('文件大小超出上传限制,请检查！', {shade: false});
	      			return;
	      		}*/
	      		$('#importModal').modal('hide');
	      		$scope.searchByParam();
	      		if(angular.equals(response.data.result,"true")){
	      			layer.alert(response.data.msg, {shade: false});
	      		}else{
	      			layer.alert(response.data.error, {shade: false});
	      		}
	      	}, function errorCallback(response) {
	      		console.log(response);
	      		$('#importModal').modal('hide');
	      		layer.alert('请求失败,请检查！', {shade: false});
	      	});
		}else{
			layer.alert('请选择要导入的文件(仅支持Excel文件)！', {shade: false});
		}
	};
	
	// 新增
	$scope.addRule = function() {
		$scope.addData = $scope.data;
		$scope.addData.inureDate = $("#addmin").val();
		$scope.addData.expireDate = $("#addmax").val();
		//选择为全局规则，并设为1
		if($scope.addData.scope == true){
			$scope.addData.scope = 1;
		}else{
			$scope.addData.scope = 0;
		}
		//操作员ID
		$scope.addData.operatorId = operatorId;
		if(typeof($scope.addData.apportionDesc) == "undefined"){
			$scope.addData.apportionDesc = null;
		}
		
		if(checkDataSubmission($scope.addData) == true){
			console.log($scope.addData);
			$http({
				method : 'POST',
				url : '../rest/addSettleApportionRule',
				data : $scope.addData,
				headers : {
					"Content-Type" : "application/json ; charset=UTF-8"
				}
			}).then(function successCallback(response) {
				if(typeof(response.data[0].result) == "undefined"){
					layer.alert('新增成功！', {shade: false});
					$scope.searchByParam();
					//重置新增内容
					$scope.data = {
						cdrType : 1,
						status : stopStatus,
						inureDate : $filter('date')(new Date(), "yyyy-MM-01"),
						expireDate : "3000-01-01",
						scope : 0
					};
					$('#addModal').modal('hide');
				}else{
					layer.alert('新增失败生失效时间冲突，请重新选择', {shade: false});
				}
			}, function errorCallback(response) {
				layer.alert('新增失败！请检查', {shade: false});
			});
		}
	};
	
	// 新增取消，清空填写数据
	$scope.cancelAdd = function() {
		//重置新增内容
		$scope.data = {
			cdrType : 1,
			status : stopStatus,
			inureDate : $filter('date')(new Date(), "yyyy-MM-01"),
			expireDate : "3000-01-01",
			scope : 0
		};
	}
	
	//预更新
	$scope.preUpdRule = function() {
		if($scope.ruleInfos.length > 1) {
			layer.alert('只能选择一条要修改的规则！', {shade: false}); 
			return;
		}
		if($scope.ruleInfos.length != 1) {
			layer.alert('请选择要修改的规则！', {shade: false}); 
			return;
		}
		angular.copy($scope.ruleInfos[0],$scope.updData);
		if($scope.ruleInfos[0].scope == 1) {
			$scope.scope=true;
		}
		$scope.updData.inureDate = $filter('date')(new Date($scope.ruleInfos[0].inureDate), "yyyy-MM-dd");
		$scope.updData.expireDate = $filter('date')(new Date($scope.ruleInfos[0].expireDate), "yyyy-MM-dd");
		$scope.updData.createTime = $filter('date')(new Date($scope.ruleInfos[0].createTime), "yyyy-MM-dd");
		$('#editModal').modal('show');
	};
	
	// 更新
	$scope.updRule = function() {
		
		var data = $scope.updData;
		$scope.updData.inureDate = $("#updmin").val();
		$scope.updData.expireDate = $("#updmax").val();
		console.log(data);
		if(checkDataSubmission(data) == true){
			$http({
				method : 'POST',
				url : '../rest/updateSettleApportionRule',
				data : data,
				headers : {
					"Content-Type" : "application/json ; charset=UTF-8"
				}
			}).then(function successCallback(response) {
				console.log(response.data);
				if(angular.equals(response.data.result,"true")){
					layer.alert('修改成功！', {shade: false});
					$('#editModal').modal('hide');
					$scope.searchByParam();
				}else{
					layer.alert(response.data.error, {shade: false});
				}
			}, function errorCallback(response) {
				layer.alert('can not receive response！', {shade: false});
			});
		}
	};
	
	// 删除
	$scope.deleteRule = function() {
		if($scope.selected.length == 0) {
			layer.alert('请勾选要删除的规则！', {shade: false}); 
			return;
		}
		var deleteConfirm = confirm("确定是否删除选中的规则?");
		//删除选中角色
		if(deleteConfirm == true){
			var data = {"ruleIdJson":$scope.selected,"operatorId":operatorId};
			console.log(data);
			$http({
				method : 'POST',
				url : '../rest/deleteSettleApportionRule',
				data : data,
				headers : {
					"Content-Type" : "application/json ; charset=UTF-8"
				}
			}).then(function successCallback(response) {
				layer.alert('删除成功！', {shade: false});
				$scope.searchByParam();
			}, function errorCallback(response) {
				layer.alert('删除失败！请检查！', {shade: false});
			});
		}
	};
	
	// 启用规则
	$scope.startRule = function() {
		if(angular.equals(startStatus,null)) {
			layer.alert('启用状态异常！', {shade: false}); 
			return;
		}
		if($scope.selected.length == 0) {
			layer.alert('请勾选要启用的规则！', {shade: false}); 
			return;
		}
		var data = {"ruleIdArray":$scope.selected,"ruleStatus":startStatus,"operatorId":operatorId};
		console.log(data);
		$http({
			method : 'POST',
			url : '../rest/modifySettleApportionRuleStatus',
			data : data,
			headers : {
				"Content-Type" : "application/json ; charset=UTF-8"
			}
		}).then(function successCallback(response) {
			layer.alert('规则启用成功！', {shade: false});
			$scope.searchByParam();
		}, function errorCallback(response) {
			layer.alert('规则启用失败！请检查', {shade: false}); 
		});
	};
	
	// 禁用规则
	$scope.stopRule = function() {
		if(angular.equals(stopStatus,null)) {
			layer.alert('禁用状态异常！', {shade: false}); 
			return;
		}
		if($scope.selected.length == 0) {
			layer.alert('请勾选要禁用的规则！', {shade: false}); 
			return;
		}
		var data = {"ruleIdArray":$scope.selected,"ruleStatus":stopStatus,"operatorId":operatorId};
		console.log(data);
		$http({
			method : 'POST',
			url : '../rest/modifySettleApportionRuleStatus',
			data : data,
			headers : {
				"Content-Type" : "application/json ; charset=UTF-8"
			}
		}).then(function successCallback(response) {
			console.log(response.data);
			if(angular.equals(response.data.result,"false")){
				layer.alert(response.data.error, {shade: false});
			}else{
				layer.alert('规则禁用成功！', {shade: false});
			}
			$scope.searchByParam();
		}, function errorCallback(response) {
			layer.alert('规则禁用失败！请检查', {shade: false});
		});
	};
	 
}]);	
    
	function doChange(file){
	    var fileUpload = $.trim($("#fileUpload").val());    //获取上传文件
	    var selectedFile = document.getElementById('fileUpload').files[0];
	    var name = selectedFile.name;
	    $('#upfile').val(name);     //赋值给自定义input框
	}　
 
	app.filter("lengthFilter",lengthFilter);
	function lengthFilter(){
       return function(value){ 	   
    	   if(typeof(value) != "undefined"){
    		   if(value.length > 10){
    	    		  value = value.substr(0, 7);
    	    		  return value + "...";
    	    	  }
    	   }
          return value;
      }
    };
  
