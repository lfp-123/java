	var app = angular.module("specialInsFeeApp", [ "ngTable","commonServiceModule"]);
	app.controller("specialInsFeeController", ["$scope", "$timeout", "$http", "$filter", "NgTableParams", "commonService", function($scope, $timeout,$http,$filter,NgTableParams,commonService) {
		
		var self = this;
		$scope.insFeeInfos = [];
		$scope.insFeeList = [];
		
		//操作员ID
	 	$scope.operatorId = localStorage.getItem("modifyOpeId");
		
	 	/**
	 	 * 找出所有工单记录
	 	 */
	 	$scope.findAllSpecialRateOperate = function() {
	 		$http({
		 		method : 'POST',
		 		url : '../rest/findAllSpecialRateOperate',
		 		headers : {
		 			"Content-Type" : "application/json ; charset=UTF-8"
		 		}
		 	}).then(function successCallback(response) {
		 		if (angular.equals(response.data,[])) {
		 			layer.alert('暂无数据！');
		 		} else {
		 			
		 			for(var i =0 ;i < response.data.length;i++){
		 				response.data[i].createTime = $filter('date')(new Date(response.data[i].createTime), "yyyy-MM-dd HH:mm:ss");
		 			}
		 			
		 			$scope.specialRateOperateList = response.data;
		 			// 表格不够10行时以空行填充
		 			if (response.data.length % 10 != 0 ){
		 				for( i = response.data.length % 10 ; i<10 ; i++){
		 					$scope.specialRateOperateList.push({});
		 				}
		 			}
		 			console.log($scope.specialRateOperateList.length);
		 			self.tableParams = new NgTableParams({count: 10}, {counts: [], dataset: $scope.specialRateOperateList});
		 			
		 		}
		 		
		 	}, function errorCallback(response) {
		 		layer.alert('数据初始化失败', {shade: false}); 
		 	});
	 	};
	 	
	 	$scope.findAllSpecialRateOperate();
	 	
		// 模板下载	
		$scope.downloadTemplate = function() {
			
			var form = document.createElement('form');
			document.body.appendChild(form);
			form.style.display = "none";
			form.action = '../rest/downloadTemplate';
			form.id = 'excel';
			form.method = 'post';

			var newElement = document.createElement("input");
			newElement.setAttribute("type", "hidden");
			newElement.name = 'filePath';
			newElement.value = '/resources/rule/template/insFeeImportTemplate.xls';
			form.appendChild(newElement);
			form.submit();
		}
		
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
	        form.append('file', file);
	        form.append('operatorId', $scope.operatorId);
	    	$('#importModal').modal('hide');
	     	$http({
	     		method : 'POST',
	     		url : '../rest/importInsFee',
	     		 data: form,
	            headers: {'Content-Type': undefined},
	            transformRequest: angular.identity
	     	}).then(function successCallback(response) {
	     		layer.alert(response.data.errorMess, {shade: false}); 
	     		$scope.findAllSpecialRateOperate();
	     	}, function errorCallback(response) {
	     		layer.alert('导入失败', {shade: false}); 
	     	});
		};
		
		/**
		 * 特殊号码保障费导入详情模态框信息
		 */
		$scope.loadDetail = function(specialRate){
			var data = {"operateId":specialRate.operateId};
			$http({
		 		method : 'POST',
		 		url : '../rest/findSpecialInsFeeByOperId',
		 		data : data,
		 		headers : {
		 			"Content-Type" : "application/json ; charset=UTF-8"
		 		}
		 	}).then(function successCallback(response) {
		 		if (angular.equals(response.data, [])) {
		 			layer.alert('暂无数据！');
		 		} else {
		 			
		 			for(var i =0 ;i < response.data.length;i++){
		 				response.data[i].startTime = $filter('date')(new Date(response.data[i].startTime), "yyyy-MM-dd HH:mm");
		 				response.data[i].endTime = $filter('date')(new Date(response.data[i].endTime), "yyyy-MM-dd HH:mm");
		 			}
		 			
		 			$scope.insFeeList = response.data;
		 			// 表格不够10行时以空行填充
		 			if (response.data.length % 10 != 0 ){
		 				for( i = response.data.length % 10 ; i<10 ; i++){
		 					$scope.insFeeList.push({});
		 				}
		 			}
		 			
		 			self.insFeeTableParams = new NgTableParams({count: 10}, {counts: [], dataset: $scope.insFeeList});
		 			$('#detailModal').modal('show');
		 		}
		 		
		 	}, function errorCallback(response) {
		 		$scope.flag = false;
		 		layer.alert('数据初始化失败', {shade: false}); 
		 	});
			
		}
		
	
	}]);
	
	//文件浏览框赋值
	function doChange(file){
		 var fileUpload = $.trim($("#fileUpload").val());    //获取上传文件信息
		    var selectedFile = document.getElementById('fileUpload').files[0];
		    var name = selectedFile.name;
		    $('#upfile').val(name);     //赋值给自定义input框
	}