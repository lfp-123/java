	var app = angular.module('mangeRoleAuth', ["integralui"]);
	app.controller("appCtrl", ["$scope", "$timeout", "IntegralUITreeViewService","IntegralUIGridService","$http", function($scope, $timeout,$treeService,$gridService,$http){
		
		//控制器
		
		$scope.gridName = "gridSample";
		$scope.gridLines = 'horizontal';
		
		// Initilaize the Theme Selector for this sample
        initTheme($scope, $gridService, $scope.gridName);

		$scope.columns = [
			{ id: 1, headerText: "角色工号", headerAlignment: "left", contentAlignment: "left", width: 140 },
			{ id: 2, headerText: "角色姓名", headerAlignment: "left", contentAlignment: "left", width: 220 },
			{ id: 3, headerText: "角色别名", headerAlignment: "left", contentAlignment: "left", width: 220 },
			{ id: 4, headerText: "角色状态", headerAlignment: "left", contentAlignment: "left", width: 200 },					
		];
		
		$scope.rows = [{"cells":[{"text":"1004"},{"text":"角色1"},{"text":"ROLE_USER"},{"text":"0"}]}
				,{"cells":[{"text":"1006"},{"text":"角色3"},{"text":"ROLE_ADMIN3"},{"text":"0"}]}
				,{"cells":[{"text":"1007"},{"text":"三号角色"},{"text":"ROLE_ADMIN1"},{"text":"0"}]}
				,{"cells":[{"text":"1008"},{"text":"三号角色"},{"text":"ROLE_ADMIN"},{"text":"1"}]}
				,{"cells":[{"text":"1009"},{"text":"张三"},{"text":"ROLE_ADMIN4"},{"text":"0"}]}
				,{"cells":[{"text":"1014"},{"text":"张三23"},{"text":"ROLE_ADMIN2"},{"text":"1"}]},{"cells":[{"text":"1111"}
				,{"text":"菜单树"},{"text":"ROLE_TREE_TEST"},{"text":"0"}]},{"cells":[{"text":"9998"},{"text":"匿名角色"},{"text":"ROLE_ANONYMOUS"}
				,{"text":"1"}]},{"cells":[{"text":"9999"},{"text":"通用角色"},{"text":"ROLE_ALL_USER"},{"text":"1"}]},
				{"cells":[{"text":"9999"},{"text":"通用角色"},{"text":"ROLE_ALL_USER"},{"text":"1"}]},
				{"cells":[{"text":"9999"},{"text":"通用角色"},{"text":"ROLE_ALL_USER"},{"text":"1"}]},
				{"cells":[{"text":"9999"},{"text":"通用角色"},{"text":"ROLE_ALL_USER"},{"text":"1"}]},
				{"cells":[{"text":"9999"},{"text":"通用角色"},{"text":"ROLE_ALL_USER"},{"text":"1"}]},
				{"cells":[{"text":"9999"},{"text":"通用角色"},{"text":"ROLE_ALL_USER"},{"text":"1"}]}];
				
		$scope.sorting = 'none';
		var sortColumn = null;
		var prevColumn = null;

		$scope.onColumnClick = function(e){
			if (e.column){
				if (e.column != prevColumn){
					if ($scope.sorting == 'none')
						$scope.sorting = 'ascending';
				}
				else {
					if ($scope.sorting == 'ascending')
						$scope.sorting = 'descending';
					else
						$scope.sorting = 'ascending';
				}

				sortColumn = e.column;
				prevColumn = e.column;
				$gridService.sort($scope.gridName, e.column, $scope.sorting);
			}
		}
		
		/*
		//选中角色
		$scope.onAfterSelect = function(e){
			sessionStorage.setItem("selectedRoleId",e.object.cells[0].text);
			var data = {"roleId":sessionStorage.getItem("selectedRoleId")};
			console.log(data);
			$http({
				method : 'POST',
				url : '../rest/showAuthRoleByRoleId',
				data : data,
				headers : {
					"Content-Type" : "application/json"
				}
			}).then(function successCallback(response) {
				sessionStorage.setItem("authArrByRoleId",response.data);
				console.log(sessionStorage.getItem("authArrByRoleId"));
				setTreeBoxChecked($scope.data);
			}, function errorCallback(response) {
				
			});	
		};
		
		$scope.onColumnCheckedChanged = function(e){
			console.log(e);
		}
		
		
		
		//显示所有的角色
		$http({
			method : 'POST',
			url : '../rest/showAllRole',
			headers : {
				"Content-Type" : "application/json ; charset=UTF-8"
			}
		}).then(function successCallback(response) {
			$scope.rows = response.data;
		}, function errorCallback(response) {
			console.log("can not receive response");
		});
				
				
		//搜索角色
		$scope.searchRole = function() {
						
			var data = {"roleId":$scope.searchId
						,"roleName":$scope.searchName};
			
			//搜索
			$http({
				method : 'POST',
				url : '../rest/searchRole',
				data : data,
				headers : {
					"Content-Type" : "application/json"
				}
			}).then(function successCallback(response) {
				for(var i = 0;i < response.data.length;i++){
					if(response.data[i].roleStatus == 0){
						response.data[i].roleStatus = "正常";
					}else{
						response.data[i].roleStatus = "不可用";
					}
				}
				$scope.role = response.data;
			}, function errorCallback(response) {
				console.log(response.data);
			});	
		};
		
		
		//输入框清空时返回所有
		$scope.inputChange = function() {
			
			var idLength = $scope.searchId.length;	
			var nameLength = $scope.searchName.length;
			
			if (idLength == 0 && nameLength == 0){
				$http({
			        method: 'POST',
			        url: '../rest/showAllRole'
			    }).then(function successCallback(response) {
			    	for(var i = 0;i < response.data.length;i++){
			    		if(response.data[i].roleStatus == 0){
			    			response.data[i].roleStatus = "正常";
			    		}else{
			    			response.data[i].roleStatus = "不可用";
			    		}
			    	}
			    	$scope.role = response.data;
			    }, function errorCallback(response) {
			    	console.log("can not receive json")
			    });
			}
		};
		
		//查找菜单树
		$http({
			method : 'POST',
			url : '../rest/showAuthTree',
			headers : {
				"Content-Type" : "application/json ; charset=UTF-8"
			}
		}).then(function successCallback(response) {
			localStorage.setItem("resourceTree",JSON.stringify(response.data));
		}, function errorCallback(response) {
			console.log("can not receive response");
		});
		
		//编辑角色资源关系
		$scope.updateRoleAuth = function(){
			
			makeArr($scope.data);
			
			for(var i=0; i<eArray.length-1; i++){
				for(var j=i+1; j<eArray.length; j++){
					if(eArray[i] == eArray[j]){
						eArray.splice(j,1);
					}
				}
			}
			
			console.log(eArray);
						
		};
		
		
		//菜单树的操作
		
		$scope.treeName = "treeSample";
		
		initTheme($scope, $treeService, $scope.treeName);
		$scope.data = JSON.parse(localStorage.getItem("resourceTree"));
		*/
		$scope.data = [{"resourceId":11000006,"text":"首页"},{"resourceId":11000007,"text":"系统管理","items":[{"resourceId":10000002,"text":"机构管理","resourceUrl":"/admin/orgManagement.html"},{"resourceId":10000003,"text":"资源管理","resourceUrl":"/admin/resourceManagement.html"},{"resourceId":10000004,"text":"操作员管理","resourceUrl":"/admin/showOperator.html"},{"resourceId":10000006,"text":"角色管理","resourceUrl":"/admin/roleManagement.html"},{"resourceId":11000011,"text":"角色资源管理","resourceUrl":"/admin/roleAuthManagement.html","items":[{"resourceId":10000001,"text":"资源111","resourceUrl":"/admin/operator_info.html"}]}]},{"resourceId":11000008,"text":"绩效管理","resourceUrl":"/pages/admin-table.html"}
		,{"resourceId":11000009,"text":"统计报表","resourceUrl":"/pages/admin-form.html"},{"resourceId":11000010,"text":"退出"}];
		
		$scope.checkBoxSettings = {
			style: {
				box: {
					checked: 'checkbox-checked',
					unchecked: 'checkbox-unchecked'
				}
			}
		}
	
		$scope.ctrlStyle = {
			item: {
				general: {
					normal: 'item-normal',
					hovered: 'item-hover'
				},
				content: {
					normal: 'item-normal',
					hovered: 'item-hover',
					selected: 'item-selected'
				}
			}
		}
		
		// Highlights the item when item is checked
		//var eArray = new Array();
		$scope.onItemCheckedChanged = function(e){
			
			if (e.value){
				e.item.style = {
					general: 'item-highlight',
					content: 'item-highlight'
				}
				//eArray.push(e.item.resourceId);
			}else{
				e.item.style = 'initial';
				eArray.pop();
			}

			var refreshTimer = $timeout(function(){
				$treeService.refresh($scope.treeName, e.item);

				$timeout.cancel(refreshTimer);
			}, 1);
			
		}
		
				
		var resourceIdArray = [11000006,10000002,11000010,10000001];
		
		function setTreeBoxChecked(items) {
			if( null == items ) return;
			for(var i = 0; i < items.length; i++ ) {
				if(sessionStorage.getItem("authArrByRoleId").indexOf(items[i].resourceId) != -1) {
					items[i].checked = true;
					items[i].style = {
						general: 'item-highlight',
						content: 'item-highlight'
					}
				}else{
					items[i].checked = false;
					items[i].style = 'initial';
				}
				if(angular.isUndefined(items[i].items)){
					
				}else{
					setTreeBoxChecked(items[i].items);
				}
				
			}
		}
		
		//setTreeBoxChecked($scope.data);
		
//		if(resourceIdArray != null){
//			setTreeBoxChecked($scope.data);
//		}
					
		$scope.$watch('authArray',function() {
			console.log("kkkkkk");
		});
		
//		localStorage.rmStorage("authArrByRoleId");
		
		var expandTimer = $timeout(function(){
			$treeService.expand($scope.treeName);
		}, 1);
		
		/*
		function makeArr(items2){
			if(null == items2) return;
			for(var i=0;i <items2.length; i++){
				if((items2[i].value == true) || (items2[i].checked == true)){
					eArray.push(items2[i].resourceId);
				}
				if(angular.isUndefined(items2[i].items)){
					
				}else{
					makeArr(items2[i].items);
				}
				
			}

		}
		*/
	}]);
	
	
	