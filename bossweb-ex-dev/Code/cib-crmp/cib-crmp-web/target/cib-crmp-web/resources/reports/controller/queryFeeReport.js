
var app = angular.module('queryFeeReportApp', ['commonServiceModule','angularjs-dropdown-multiselect',"ngTable"]);
app.controller("queryFeeReportCtrl", function($scope, $http, commonService, NgTableParams) {

var self = this;
	
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
	
	// 搜索资费规则
	var data = {"rateStatus":1};
	$http({
		method : 'POST',
		url : '../rest/selectRateRule',
		data : data,
		headers : {
			"Content-Type" : "application/json ; charset=UTF-8"
	}
	}).then(function successCallback(response) {

		var rateArr = new Array();
		for (var i=0; i < response.data.length;i++){
			if (response.data[i].scope == 1) {
				rateArr.push(response.data[i]);
			}
		}
		$scope.rateRule = rateArr;	
		
	}, function errorCallback(response) {
		layer.alert('数据初始化失败', {shade: false}); 
	});
	
	// 重置
	$scope.resetBtn = function() {
		$scope.data.unitName = null;
		$scope.data.userName = null;
		$scope.data.feeType = null;
		$scope.beginDate = (year + "" + month) - 0;
		$scope.endDate = (year + "" + month) - 0;
		$scope.selectedOrg = [];
		$scope.data.notesId = null;
	};
	
	// 导出
	$scope.exportBtn = function() {
		
		// 开始转圈
//		var index = parent.layer.load(1, {
//			  shade: [0.5,'#000'],time: 300000
//			});
		
		var myForm = document.createElement("form");
		myForm.action = "../rest/exportExcel";
		myForm.method = "POST";
		myForm.style.display = "none";
		
//		myForm.target = "../page/tempPage.html";
		$scope.data.businessType = "1";
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
		document.body.removeChild(myForm);
		
//		// 结束转圈
//		layer.close(index);
		
		
//		$scope.data.businessType = "1";
//		$http({
//			method : 'POST',
//			url : '../rest/exportExcel',
//			data : $scope.data,
//			headers : {
//				"Content-Type" : "application/json ; charset=UTF-8"
//			}
//		}).then(function successCallback(response) {
////			if ("success" == response.data.status) {
////				var fileName = response.data.fileName;
////				downloadTemplate('../rest/downloadExcel', 'fileName', fileName);
////			} else {
////				alert("Excel生成失败");
////			}
//			
////			var myForm = document.createElement("form");
//////			myForm.action = "../rest/exportExcel";
//////			myForm.method = "POST";
////			myForm.style.display = "none";
////			$scope.data.businessType = "1";
////			for (var item in $scope.data) {
////				var opt = document.createElement("input");
////				opt.type = "text";
////				opt.id = item;
////				opt.name = item;
////				opt.value = $scope.data[item];
////				myForm.appendChild(opt);
////			}
////			document.body.appendChild(myForm);
////			myForm.submit();
////			document.body.removeChild(myForm);
//			
//			
//			// 这里会弹出一个下载框，增强用户体验
//		    var blob = new Blob([response.data], {type: "multipart/form-data"});  
////		    var fileName = $scope.reqNo + "kkk.xls";
//		    var fileName = "kkk.xls";
//		    
//		    if (!!window.ActiveXObject || "ActiveXObject" in window){
//		    	//IE
//		    	window.navigator.msSaveBlob(blob, fileName);
//		    } else {
//		    	//Not IE
//		    	var objectUrl = URL.createObjectURL(blob);  
//	            //  创建a标签模拟下载  
//	            var aForTxt = $("<a><span class='forTxt'>下载</span></a>").attr("href",objectUrl);  
//	            aForTxt.attr("download",fileName);
//	            $("body").append(aForTxt);  
//	            $(".forTxt").click();  
//	            aForTxt.remove(); 
//		    }
//			
//			
//		}, function errorCallback(response) {
//			alert("请求失败");
//			console.log(response);
//		});
		
	};
	
	// 预览
	$scope.previewBtn = function() {
		$http({
	        method: 'POST',
	        url: "../rest/previewReport?businessType=1",
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
		
	// 批量查询
	importBtn = function(obj) {
		var form = new FormData();
        var file = document.getElementById("uploadFile").files[0];
        
        var fileName = $('#uploadFile').val();
		if(angular.equals(fileName,"") || !angular.equals(fileName.substring(fileName.indexOf(".")+1,fileName.length),"xls")){
			layer.alert('请选择需要上传的Excel文件', {shade: false}); 
			return;
		}
        
        if(file.size > 10485760){
        	layer.alert('上传的文件大小不能超过10M'); 
        	return;
        }

        form.append('file', file);
        var index = layer.load(1,{shade: [0.5, '#393D49']}, {shadeClose: true});
      	$http({
      		method : 'POST',
      		url : '../rest/batchQuery',
      		data: form,
            headers: {'Content-Type': undefined}
      	}).then(function successCallback(response) {
      		layer.close(index);
      		if (response.data.errCode == "0000") {
      			downloadBtn(response.data.fileDir, "../rest/downloadBatchResult");
      		} else {
      			layer.alert(response.data.errMsg);
      		}
      	}, function errorCallback(response) {
      		layer.close(index);
      		console.log("error");
      	});
      	document.getElementById("uploadFile").value = "";
	}
	
	downloadBtn = function(fileDir, downloadUrl) {
		var myForm = document.createElement("form");
		myForm.action = downloadUrl;
		myForm.method = "POST";
		myForm.style.display = "none";
		//下载路径
		var opt = document.createElement("input");
		opt.type = "text";
		opt.id = "fileDir";
		opt.name = "fileDir";
		opt.value = fileDir;
		
		myForm.appendChild(opt);
		document.body.appendChild(myForm);
		myForm.submit();
		document.body.removeChild(myForm);
	}
		
	// 模板下载	
	$scope.downloadTemplate = function(){
		var form = document.createElement('form');
		document.body.appendChild(form);
		form.style.display = "none";
		form.action = '../rest/downloadTemplate';
		form.id = 'excel';
		form.method = 'post';

		var newElement = document.createElement("input");
		newElement.setAttribute("type", "hidden");
		newElement.name = 'filePath';
		newElement.value = '/resources/rule/template/queryFeeTemplate.xls';
		form.appendChild(newElement);
		form.submit();
	}
	
	commonService.getDictDefByDictClass(1001, false, function(response) {
		$scope.feeTypes = response.data;
	});
	
	//显示资费规则详情
	$scope.loadDetail = function(list, index){
		
		if(list[index].cdrType == 3){
			$scope.hourFlag = true;
			$scope.minFlag = false;
			$scope.meetingFlag = true;
			$scope.numberFlag = false;
			$scope.calledFlag = false;
			$scope.otherFee = true;
			
			if (list[index].feeType == 9) {
				$scope.otherFee = true;
				$scope.fee = false;
			} else {
				$scope.otherFee = false;
				$scope.fee = true;
			}
			
		} else {
			$scope.minFlag = true;
			$scope.hourFlag = false;
			$scope.numberFlag = true;
			$scope.meetingFlag = false;
			$scope.calledFlag = true;
			$scope.otherFee = false;
			$scope.fee = true;
		}
		
		self.detailTableParams = new NgTableParams({count: 10}, 
				{
					counts: [], 
					getData: function(params) {
						var data = {
							"notesId":list[index].notesId, 
							"beginDate":list[index].month, 
							"cdrType":list[index].cdrType,
							"feeType":list[index].feeType,
							"unitName":list[index].orgNameFull,
							"operatorId":list[index].operatorId,
							pageSize:params.count(),
							pageNo:params.page() 
						}
						
						console.log(data);

						 
			     return $http({
							method : 'POST',
							url : '../rest/showDetailUsrList',
							data : data,
							headers : {
								"Content-Type" : "application/json ; charset=UTF-8"
							}
						}).then(function successCallback(response) {
							
							$scope.detailList = [];
							
							if (angular.equals(response.data.list, [])) {
					 			layer.alert('暂无数据！');
					 		} else {
					 			
					 			
					 			// 表格不够10行时以空行填充
					 			if (response.data.list.length % 10 != 0 ){
					 				for( i = response.data.list.length % 10 ; i<10 ; i++){
					 					response.data.list.push({});
					 				}
					 			}
					 			
					 			$scope.detailList = response.data.list;
					 			params.total(response.data.total);
					 			$('#detailModal').modal('show');
					 			return response.data.list;
					 		}
						}, function errorCallback(response) {
							layer.alert('数据加载失败！');
							return [];
						});
						
					}
				}
		);
				
	};
	
	
});
