var app = angular.module("RateRuleConfigurate", [ "ngTable","commonServiceModule"]);
app.controller("RateRuleConfigurateCtrl",["$scope", "$timeout","$http","$filter","NgTableParams","commonService", function($scope, $timeout,$http,$filter,NgTableParams,commonService){
		
	var self = this;
	$scope.rateRuleList = [];
	$scope.addmdata = {};
	$scope.search = {};
	$scope.editmdata = {};
	$scope.selected = [] ; //选中规则ID数组
 	$scope.rateRuleInfos=[]; //选中规则数组
 	$scope.otherFeeName="其他费用(元):";
 	// 验证时间类型yyyy-MM-dd正则表达式
	var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;

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
	//加载收费类型数据字典
	commonService.getDictDefByDictClass(1006, false, function(response) {
		console.log(response.data);
		$scope.rateTypesAll = response.data;
	});
	//加载规则范围数据字典
	commonService.getDictDefByDictClass(1019, false, function(response) {
		console.log(response.data);
		$scope.scopeTypes = response.data;
	});

	var data={};
	$http({
	method : 'POST',
	url : '../rest/selectRateRule',
	data : data,
	headers : {
		"Content-Type" : "application/json ; charset=UTF-8"
	}
}).then(function successCallback(response) {
	
	if(angular.equals(response.data,[])){
		layer.alert('暂无符合规则！', {shade: false});
		$scope.rateRuleList = [{},{},{},{},{},{},{},{},{},{}];
		loadTable($scope.rateRuleList);
	}
	else if(response.data.length % 10 != 0 ){
		console.log(response.data.length);
		$scope.rateRuleList = response.data;
		for(var i = response.data.length % 10 ; i<10 ; i++){
			$scope.rateRuleList.push({});
		}
		console.log($scope.rateRuleList);
		loadTable($scope.rateRuleList);
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
     
 	//复选框状态判断
	$scope.isSelected = function(info) {
		if(angular.equals(info,{}))
			return;
		var pos = -1;
		for(var i=0; i<$scope.rateRuleInfos.length; i++){
			if(info.rateItemId == $scope.rateRuleInfos[i].rateItemId) {
				pos = i;
				break;
			}
		}
		return pos != -1;
	}
	//规则选中
	$scope.selectARateRule = function(info) {
		if(angular.equals(info,{}))
			return;
		var pos = -1;
		for(var i=0; i<$scope.rateRuleInfos.length; i++){
			if(info.rateItemId == $scope.rateRuleInfos[i].rateItemId) {
				pos = i;
				break;
			}
		}
		if(pos == -1) {
			$scope.rateRuleInfos.push(info);
			  $scope.selected.push(info.rateItemId) ;  
		} else {
			$scope.rateRuleInfos.splice(pos, 1);
			$scope.selected.splice(pos,1) ;
		}
	}
 	
    
    //条件查询
	$scope.searchCostRule = function() {
		$scope.selectdata={};
		$scope.search.inureDate = document.getElementById("searchlogmin").value;
		$scope.search.expireDate = document.getElementById("searchlogmax").value;
		angular.copy($scope.search,$scope.selectdata);
		if(typeof($scope.selectdata.inureDate) != "undefined" && $scope.selectdata.inureDate.match(reg) == null){
			$scope.selectdata.inureDate = undefined;
		}
		if(typeof($scope.selectdata.expireDate) != "undefined" && $scope.selectdata.expireDate.match(reg) == null){
			$scope.selectdata.expireDate = undefined;
		}
		$scope.selectdata.operatorId=$scope.operatorId;
		console.log($scope.selectdata);
		$http({
			method : 'POST',
			url : '../rest/selectRateRule',
			data : $scope.selectdata,
			headers : {
				"Content-Type" : "application/json; charset=UTF-8"
			}
		}).then(function successCallback(response) {
			$scope.resetSelect();
			if(angular.equals(response.data,[])){
				layer.alert('暂无规则！', {shade: false});
				$scope.rateRuleList = [{},{},{},{},{},{},{},{},{},{}];
				loadTable($scope.rateRuleList);
			}
			else if(response.data.length % 10 != 0 ){
				console.log(response.data.length);
				$scope.rateRuleList = response.data;
				for(var i = response.data.length % 10 ; i<10 ; i++){
					$scope.rateRuleList.push({});
				}
				console.log($scope.rateRuleList);
				loadTable($scope.rateRuleList);
			}else{
				loadTable(response.data);
			}
		}, function errorCallback(response) {
			layer.alert('查询失败', {shade: false}); 
		});	
	};
//重置选中
$scope.resetSelect=function(){
	$scope.selected = [] ; //选中规则ID数组
 	$scope.rateRuleInfos=[]; //选中规则数组
}
//重置查询条件
$scope.reset = function() {
	$scope.search={};
	$("#searchlogmin").val(null);
	$("#searchlogmax").val(null);
};
//显示资费规则详情
$scope.loadDetail=function(rateRule){
	$scope.rateRuleDetail=rateRule;
	$scope.selectARateRule(rateRule);
	$('#detailModal').modal('show');
}

	$scope.openAddModel = function(){
		$scope.addscope=false;
		$scope.addmdata={};
		$scope.rateTypes=[];
		$scope.otherFeeName="其他费用(元):";
		for(var i=0;i<$scope.rateTypesAll.length;i++){
			if($scope.rateTypesAll[i].entryId==1 || $scope.rateTypesAll[i].entryId==3 || $scope.rateTypesAll[i].entryId==6 ||
					$scope.rateTypesAll[i].entryId==8|| $scope.rateTypesAll[i].entryId==10){
				$scope.rateTypes.push($scope.rateTypesAll[i]);
			}
		}
		$scope.addmdata.cdrType=1;
		$scope.addmdata.rateType=$scope.rateTypes[0].entryId;
		$scope.addmdata.fee=0;
		$scope.addmdata.otherFee=0;
		$scope.addmdata.inureDate=$filter('date')(new Date(), "yyyy-MM-01");
		$scope.addmdata.expireDate=$filter('date')(new Date("3000-01-01"), "yyyy-MM-dd");
		$scope.addmdata.rateStatus=$scope.statusTypes[1].entryId;
		document.getElementById("fee").disabled="";
		document.getElementById("otherFee").disabled="disabled";
		document.getElementById("addscope").disabled="";
		$('#addModal').modal('show');
	}
//添加资费规则
	$scope.addCostRule = function() {
		$scope.adddata={};
		$scope.addmdata.scope=$scope.addscope;
		$scope.addmdata.inureDate = document.getElementById("addlogmin").value;
		$scope.addmdata.expireDate = document.getElementById("addlogmax").value;
		angular.copy($scope.addmdata,$scope.adddata);
		if(typeof($scope.adddata.inureDate) != "undefined" && $scope.adddata.inureDate.match(reg) == null){
			$scope.adddata.inureDate = undefined;
		}
		if(typeof($scope.adddata.expireDate) != "undefined" && $scope.adddata.expireDate.match(reg) == null){
			$scope.adddata.expireDate = undefined;
		}
		
		if(typeof($scope.adddata.otherFee) == "undefined" || $scope.adddata.otherFee == null || angular.equals($scope.adddata.otherFee,"")){
			$scope.adddata.otherFee=0;
		}
		//优先级与全局设置
		if($scope.adddata.rateType==8){
			if($scope.adddata.scope==true){
				$scope.adddata.scope=1;
			}else{
				$scope.adddata.scope=0;
			}
			$scope.adddata.priority=-1;
		}else if ($scope.adddata.rateType==10){
			$scope.adddata.scope=1;
			$scope.adddata.priority=-1;
		}else{
			if($scope.adddata.scope==true){
				$scope.adddata.scope=1;
			}else{
				$scope.adddata.scope=0;
			}
			if($scope.adddata.rateType==3){
				$scope.adddata.priority=3;
			}else if($scope.adddata.rateType==4){
				$scope.adddata.priority=4;
			}else if($scope.adddata.rateType==5){
				$scope.adddata.priority=5;
			}else if($scope.adddata.rateType==6){
				$scope.adddata.priority=6;
			}else{
				$scope.adddata.priority=2;
			}
		}
		$scope.adddata.operatorId=$scope.operatorId;
		$scope.adddata.createTime=formatDate(new Date());
		if(checkDataSubmission($scope.adddata)==true){
		$scope.adddata.fee=$scope.adddata.fee*1000;
		$scope.adddata.otherFee=$scope.adddata.otherFee*1000;
		console.log($scope.adddata);
		$http({
			method : 'POST',
			url : '../rest/addRateRule',
			data : $scope.adddata,
			headers : {
				"Content-Type" : "application/json; charset=UTF-8"
			}
		}).then(function successCallback(response) {
			console.log(response.data.status);
			if(response.data.status=="success"){
				$scope.addmdata={};
				$('#addModal').modal('hide');
			}
			layer.alert(response.data.info, {shade: false}); 
			$scope.searchCostRule();
		}, function errorCallback(response) {
			layer.alert('添加失败', {shade: false}); 
			$scope.adddata={};
		});	
		}
	};
	
	//资费类型设置
	$scope.setRateType=function(cdrType,rateType){
		document.getElementById("addscope").disabled="";
		$scope.addscope=false;
		$scope.addmdata.fee=0;
		$scope.addmdata.otherFee=0;
		$scope.editmdata.fee=0;
		$scope.editmdata.otherFee=0;
		if(rateType==9||rateType==10){
			$scope.addscope=true;
			if(rateType==10){
				document.getElementById("addscope").disabled="disabled";
			}
		}
		if(rateType==10||rateType==9||rateType==8||rateType==3||rateType==4||rateType==5||rateType==6){
			document.getElementById("fee").disabled="disabled";
			document.getElementById("otherFee").disabled="";
		}else{
			document.getElementById("fee").disabled="";
			document.getElementById("otherFee").disabled="disabled";
		}
	};
	
	//设置资源类型
	$scope.setType = function(entryId){
		document.getElementById("addscope").disabled="";
		$scope.addscope=false;
		$scope.rateTypes=[];
		$scope.addmdata.rateType=null;
		$scope.otherFeeName="其他费用(元):";
		$scope.addmdata.fee=0;
		$scope.addmdata.otherFee=0;
		if(entryId==1){
			for(var i=0;i<$scope.rateTypesAll.length;i++){
				if($scope.rateTypesAll[i].entryId==1 || $scope.rateTypesAll[i].entryId==3 || 
						$scope.rateTypesAll[i].entryId==6|| $scope.rateTypesAll[i].entryId==8|| $scope.rateTypesAll[i].entryId==10){
					$scope.rateTypes.push($scope.rateTypesAll[i]);
				}
			}
		}else if(entryId==2){
			$scope.editmdata.otherFee=0;
			for(var i=0;i<$scope.rateTypesAll.length;i++){
				if($scope.rateTypesAll[i].entryId==1){
					$scope.rateTypes.push($scope.rateTypesAll[i]);
				}
			}
		}else if(entryId==3){
			$scope.otherFeeName="设备保障费(元):";
			for(var i=0;i<$scope.rateTypesAll.length;i++){
				if($scope.rateTypesAll[i].entryId==2||$scope.rateTypesAll[i].entryId==9){
					$scope.rateTypes.push($scope.rateTypesAll[i]);
				}
			}
		}else{
			$scope.otherFeeName="固定费用(元):";
			for(var i=0;i<$scope.rateTypesAll.length;i++){
				if($scope.rateTypesAll[i].entryId==3 || $scope.rateTypesAll[i].entryId==6){
					$scope.rateTypes.push($scope.rateTypesAll[i]);
				}
			}
		}
		$scope.addmdata.rateType=$scope.rateTypes[0].entryId;
		if($scope.addmdata.rateType==10||$scope.addmdata.rateType==9||$scope.addmdata.rateType==8||$scope.addmdata.rateType==3||
				$scope.addmdata.rateType==4||$scope.addmdata.rateType==5||$scope.addmdata.rateType==6){
			document.getElementById("fee").disabled="disabled";
			document.getElementById("otherFee").disabled="";
			if($scope.addmdata.rateType==10){
				document.getElementById("addscope").disabled="disabled";
			}
		}else{
			document.getElementById("fee").disabled="";
			document.getElementById("otherFee").disabled="disabled";
		}
	}
	
    //编辑资费规则模态框展开
	$scope.updateload= function(){
		$scope.scope=false;
		console.log($scope.rateRuleInfos);
		if($scope.rateRuleInfos.length>1){
			layer.alert('只能选择一条要修改的规则！', {shade: false}); 
			return;
		}
		if($scope.rateRuleInfos.length==0){
			layer.alert('请点击选择需要修改的规则', {shade: false}); 
			return;
		}
		angular.copy($scope.rateRuleInfos[0],$scope.editmdata);
		$scope.rateTypes=[];
		$scope.otherFeeName="其他费用(元):";
		//不同的资源类型对应不同的资费类型，不同的资费类型所展示的可编辑框项不同
		if($scope.editmdata.cdrType==1){
			if($scope.editmdata.rateType==8){
				for(var i=0;i<$scope.rateTypesAll.length;i++){
					if($scope.rateTypesAll[i].entryId==8){
						$scope.rateTypes.push($scope.rateTypesAll[i]);
					}
				}
			}else{
				for(var j=0;j<$scope.rateTypesAll.length;j++){
					if($scope.rateTypesAll[j].entryId==1 || $scope.rateTypesAll[j].entryId==3 ||
							$scope.rateTypesAll[j].entryId==6||$scope.rateTypesAll[j].entryId==10){
						$scope.rateTypes.push($scope.rateTypesAll[j]);
					}
				}
			}
		}else if($scope.editmdata.cdrType==2){
			if($scope.editmdata.rateType==8){
				for(var k=0;k<$scope.rateTypesAll.length;k++){
					if($scope.rateTypesAll[k].entryId==8){
						$scope.rateTypes.push($scope.rateTypesAll[k]);
					}
				}
			}else{
				for(var z=0;z<$scope.rateTypesAll.length;z++){
					if($scope.rateTypesAll[z].entryId==1){
						$scope.rateTypes.push($scope.rateTypesAll[z]);
					}
				}
			}
		}else if($scope.editmdata.cdrType==3){
			$scope.otherFeeName="设备保障费(元):";
			for(var o=0;o<$scope.rateTypesAll.length;o++){
				if($scope.rateTypesAll[o].entryId==2||$scope.rateTypesAll[o].entryId==9){
					$scope.rateTypes.push($scope.rateTypesAll[o]);
				}
			}
		}else{
			$scope.otherFeeName="固定费用(元):";
			for(var h=0;h<$scope.rateTypesAll.length;h++){
				if($scope.rateTypesAll[h].entryId==3 || $scope.rateTypesAll[h].entryId==6){
					$scope.rateTypes.push($scope.rateTypesAll[h]);
				}
			}
		}
		if($scope.editmdata.rateType==3||$scope.editmdata.rateType==4||$scope.editmdata.rateType==5||
				$scope.editmdata.rateType==6||$scope.editmdata.rateType==8||$scope.editmdata.rateType==9||$scope.editmdata.rateType==10){
			document.getElementById("mFee").disabled="disabled";
			document.getElementById("mOtherFee").disabled="";
		}else{
			document.getElementById("mFee").disabled="";
			document.getElementById("mOtherFee").disabled="disabled";
		}
		$scope.editmdata.inureDate=$filter('date')(new Date($scope.editmdata.inureDate), "yyyy-MM-dd");
		$scope.editmdata.expireDate=$filter('date')(new Date($scope.editmdata.expireDate), "yyyy-MM-dd");
		$scope.editmdata.fee=$scope.editmdata.fee/1000;
		$scope.editmdata.otherFee=$scope.editmdata.otherFee/1000;
		if($scope.editmdata.scope == 1) {
			$scope.scope=true;
		}
		$('#editModal').modal('show');
	}
	//编辑资费规则
	$scope.editRateRule = function() {
		$scope.editdata={};
		//非全局资费资费类型修改优先级也修改
		$scope.editmdata.inureDate = document.getElementById("editlogmin").value;
		$scope.editmdata.expireDate = document.getElementById("editlogmax").value;
		angular.copy($scope.editmdata,$scope.editdata);
		if(typeof($scope.editdata.inureDate) != "undefined" && $scope.editdata.inureDate.match(reg) == null){
			$scope.editdata.inureDate = undefined;
		}
		if(typeof($scope.editdata.expireDate) != "undefined" && $scope.editdata.expireDate.match(reg) == null){
			$scope.editdata.expireDate = undefined;
		}
		
		if(typeof($scope.editdata.otherFee) == "undefined" || $scope.editdata.otherFee == null || angular.equals($scope.editdata.otherFee,"")){
				$scope.editdata.otherFee=0;
		}
		if(checkDataSubmission($scope.editdata)==true){
			$scope.editdata.fee=$scope.editdata.fee*1000;
			$scope.editdata.otherFee=$scope.editdata.otherFee*1000;
			console.log($scope.editdata);
			$http({
				method : 'POST',
				url : '../rest/editRateRule',
				data : $scope.editdata,
				headers : {
					"Content-Type" : "application/json; charset=UTF-8"
				}
			}).then(function successCallback(response) {
				console.log(response.data);
				if(response.data.status=="success"){
					$('#editModal').modal('hide');
				}
				layer.alert(response.data.info, {shade: false}); 
				$scope.searchCostRule();
			}, function errorCallback(response) {
				layer.alert('修改失败', {shade: false}); 
				$scope.editdata={};
			});	
		}
	};
	//删除资费规则
	$scope.deleteRateRule = function() {
		var checkedRuleArray = new Array();
		checkedRuleArray = $scope.selected;
		console.log(checkedRuleArray);
		var operatorId=$scope.operatorId;
		if (checkedRuleArray.length != 0){
			var deletedata = {"rateItemIdArray":checkedRuleArray,"operatorId":operatorId};
			var deleteConfirm = confirm("确定是否删除选中内容?");
			//删除选中角色
			if(deleteConfirm == true){
				$http({
					method : 'POST',
					url : '../rest/deleteRateRule',
					data : deletedata,
					headers : {
						"Content-Type" : "application/json; charset=UTF-8"
					}
				}).then(function successCallback(response) {
					$scope.resetSelect(); 
					$scope.searchCostRule();
					layer.alert('删除成功', {shade: false}); 
				}, function errorCallback(response) {
					layer.alert('删除失败', {shade: false}); 
				});
			}
		}
		else
			layer.alert('请选择需要删除的规则', {shade: false}); 
	};
	//启用资费规则
	$scope.startUsing = function() {
		var checkedRuleArray = new Array();
		checkedRuleArray = $scope.selected;
		console.log(checkedRuleArray);
		var operatorId=$scope.operatorId;
		if (checkedRuleArray.length != 0){
			var startUsingdata = {"rateItemIdArray":checkedRuleArray,"operatorId":operatorId,"rateStatus":"1"};
				$http({
					method : 'POST',
					url : '../rest/updateRateRuleStatus',
					data : startUsingdata,
					headers : {
						"Content-Type" : "application/json; charset=UTF-8"
					}
				}).then(function successCallback(response) {
					$scope.searchCostRule();
					layer.alert('启用成功', {shade: false}); 
				}, function errorCallback(response) {
					layer.alert('启用失败', {shade: false}); 
				});
			}
		else
			layer.alert('请选择规则', {shade: false}); 
	};
	//禁用资费规则
	$scope.forbidden = function() {
		var checkedRuleArray = new Array();
		checkedRuleArray = $scope.selected;
		console.log(checkedRuleArray);
		var operatorId=$scope.operatorId;
		if (checkedRuleArray.length != 0){
			var forbiddendata = {"rateItemIdArray":checkedRuleArray,"operatorId":operatorId,"rateStatus":"2"};
				$http({
					method : 'POST',
					url : '../rest/updateRateRuleStatus',
					data : forbiddendata,
					headers : {
						"Content-Type" : "application/json; charset=UTF-8"
					}
				}).then(function successCallback(response) {
					$scope.searchCostRule();
					console.log(response.data);
					if(response.data.status=="success"){
						layer.alert('禁用成功', {shade: false});  
					}else{
						layer.alert(response.data.info, {shade: false}); 
					}			
				}, function errorCallback(response) {
					layer.alert('禁用失败', {shade: false}); 
				});
			}
		else
			layer.alert('请选择规则', {shade: false}); 
	};
	//导入框展开
	$scope.importExcel = function(){
		$('#importModal').modal('show');
	}
	//上传EXCEL文件
	$scope.uploadFile=function(){
		var fileName = $('#upfile').val();
		if(angular.equals(fileName,"") || !angular.equals(fileName.substring(fileName.indexOf(".")+1,fileName.length),"xls")){
			layer.alert('请选择需要上传的Excel文件', {shade: false}); 
			return;
		}
		
		var form = new FormData();
        var file = document.getElementById("fileUpload").files[0];
        if(file.size>10485760){
        	layer.alert('文件大小超出上传限制！'); 
        	return;
        }
        form.append('file', file);
        form.append('operatorId', $scope.operatorId);
        console.log(form);
    	$('#importModal').modal('hide');
     	$http({
     		method : 'POST',
     		url : '../rest/importRateRule',
     		 data: form,
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity
     	}).then(function successCallback(response) {
     		if(typeof response.data == 'string'){
     		if(response.data.indexOf("fileUpMaxError")!= -1){
     			layer.alert('文件大小超出上传限制！'); 
     			return;
     		}}
     		layer.alert(response.data.success, {shade: false}); 
     		$scope.searchCostRule();
     	}, function errorCallback(response) {
     		layer.alert('导入失败', {shade: false}); 
     	});
	};
	
	//导出资费规则
	$scope.ruleExport = function() {
		$scope.exportdata={};
		angular.copy($scope.selectdata,$scope.exportdata);
		$scope.exportdata.operatorId=$scope.operatorId;
		$http({
			method : 'POST',
			url : '../rest/exportRateRule',
			data : $scope.exportdata,
			headers : {
				"Content-Type" : "application/json; charset=UTF-8"
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
	//下载
	$scope.download = function(filename) {
		downloadTemplate('../rest/downloadExcel', 'fileName', filename);
	};
	//模板下载
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
		newElement.value = '/resources/rule/template/RateRuleTemplate.xls';
		form.appendChild(newElement);
		form.submit();
	}
	//添加规则格式验证
	function checkDataSubmission(formdata){
		console.log(formdata);
		if(angular.equals(formdata,{})){
			layer.alert("规则信息不能为空,请检查!");
			return false;
		} 
		if(typeof(formdata.rateRuleName) == "undefined" || angular.equals(formdata.rateRuleName,"") || getBLen(formdata.rateRuleName) > 32){
			layer.alert("资费规则名称为空或名称长度过长,请检查!");
			return false;
		}
		if(typeof(formdata.cdrType) == "undefined" || angular.equals(formdata.cdrType,"") ){
			layer.alert("资费类型不能为空,请检查!");
			return false;
		}
		if(typeof(formdata.rateType) == "undefined" || angular.equals(formdata.rateType,"") ){
			layer.alert("资源类型不能为空,请检查!");
			return false;
		}
		if(typeof(formdata.fee) == "undefined" || angular.equals(formdata.fee,"")){
			layer.alert("资费不能为空,请检查!");
			return false;
		}
		if(typeof(formdata.rateStatus) == "undefined" || angular.equals(formdata.rateStatus,"")){
			layer.alert("资费规则状态不能为空,请检查!");
			return false;
		}
//字段需求要求存在，但系统暂无使用处，预留备用	
//		if(typeof(formdata.startTime) == "undefined" || angular.equals(formdata.startTime,"")){
//			layer.alert("开始时间不能为空,请检查!");
//			return false;
//		}
//		if(typeof(formdata.endTime) == "undefined" || angular.equals(formdata.endTime,"")){
//			layer.alert("结束时间不能为空,请检查!");
//			return false;
//		}
		if(typeof(formdata.inureDate) == "undefined" || angular.equals(formdata.inureDate,"")){
			layer.alert("生效时间不能为空,请检查!");
			return false;
		}
		if(typeof(formdata.expireDate) == "undefined" || angular.equals(formdata.expireDate,"")){
			layer.alert("失效时间不能为空,请检查!");
			return false;
		}
		if(typeof(formdata.rateDesc) != "undefined" && getBLen(formdata.rateDesc) > 512){
			layer.alert("备注说明过长，请删减!");
			return false;
		}
		return true;
	}
	//长度计算
	var getBLen = function(str) {
		  if (str == null) return 0;
		  return str.replace(/[^\x00-\xff]/g,"**").length;
		}

}]);
//文件浏览框赋值
function doChange(file){
	 var fileUpload = $.trim($("#fileUpload").val());    //获取上传文件信息
	    var selectedFile = document.getElementById('fileUpload').files[0];
	    var name = selectedFile.name;
	    $('#upfile').val(name);     //赋值给自定义input框
}

//时间格式装换
var formatDate = function (date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? '0' + m : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    return y + '-' + m + '-' + d;
}
