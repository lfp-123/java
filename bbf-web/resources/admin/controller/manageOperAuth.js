	var app = angular.module('mangeOperAuth', ["integralui"]);
	app.controller("operAuthController", ["$scope", "$timeout", "IntegralUITreeViewService","IntegralUIGridService","$http", function($scope, $timeout,$treeService,$gridService,$http){
		
		/**
		 * Grid表格
		 */
		
		
		$scope.gridName = "gridSample";
		$scope.gridLines = 'horizontal';
		// Initilaize the Theme Selector for this sample
        initTheme($scope, $gridService, $scope.gridName);

		$scope.columns = [
			{ id: 1, headerText: "操作员工号", headerAlignment: "left", contentAlignment: "left", width: 140 },
			{ id: 2, headerText: "操作员姓名", headerAlignment: "left", contentAlignment: "left", width: 220 },
			{ id: 3, headerText: "操作员状态", headerAlignment: "left", contentAlignment: "left", width: 220 },
			{ id: 4, headerText: "操作员等级", headerAlignment: "left", contentAlignment: "left", width: 200 },					
		];
		
		//查找grid表格， 所有的操作员
		$http({
			method : 'POST',
			url : '../rest/showAllOperGrid',
			headers : {
				"Content-Type" : "application/json ; charset=UTF-8"
			}
		}).then(function successCallback(response) {
			localStorage.setItem("operGrid",JSON.stringify(response.data));
		}, function errorCallback(response) {
			console.log("can not receive response");
		});
		
		$scope.rows = JSON.parse(localStorage.getItem("operGrid"));
		
		$scope.sorting = 'none';
		var sortColumn = null;
		var prevColumn = null;
		
		//排序
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
		};
		
		//搜索操作员
		$scope.searchOperator = function() {
			
			if (angular.isUndefined($scope.searchId)){
				$scope.searchId = null;
			}
			
			if (angular.isUndefined($scope.searchName)){
				$scope.searchName = null;
			}
			var data = {"operatorId":$scope.searchId
						,"operatorName":$scope.searchName};
			
			//搜索操作员，以gridJSON格式返回
			$http({
				method : 'POST',
				url : '../rest/searchOperatorGrid',
				data : data,
				headers : {
					"Content-Type" : "application/json"
				}
			}).then(function successCallback(response) {
				
				localStorage.setItem("operGrid",JSON.stringify(response.data));
				$scope.rows =  JSON.parse(localStorage.getItem("operGrid"));
				$gridService.clearRows($scope.gridName);
				for(var i=0;i<response.data.length;i++){
					$gridService.addRow($scope.gridName,response.data[i]);
				}
				
			}, function errorCallback(response) {
				console.log("search fail");
			});	
			
		};
		
		//选中操作员
		$scope.onAfterSelect = function(e){
			sessionStorage.setItem("selectedOperId",e.object.cells[0].text);
			var data = {"operatorId":sessionStorage.getItem("selectedOperId")};
			
			$http({
				method : 'POST',
				url : '../rest/showOperAuthByOperId',
				data : data,
				headers : {
					"Content-Type" : "application/json"
				}
			}).then(function successCallback(response) {
				sessionStorage.setItem("selectedAuthIdArray",response.data);
				clearTreeBoxChecked($scope.data);
				clearEarray();
				setTreeBoxChecked($scope.data);
			}, function errorCallback(response) {
				
			});	
		};
		
		/**
		 * 菜单树
		 */
		
		var eArray = new Array();
		
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
		
		$scope.data = JSON.parse(localStorage.getItem("resourceTree"));
		
		//菜单树的操作
		$scope.treeName = "treeSample";
		initTheme($scope, $treeService, $scope.treeName);
		
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
		
		//清空数组
		function clearEarray(){
			eArray.splice(0, eArray.length);
		}
		
		//清除所有选中的菜单
		function clearTreeBoxChecked(items){
			if( null == items ) return;
			for(var i = 0;i<items.length;i++){
				items[i].checked = false;
				items[i].style = 'initial';
				if(angular.isUndefined(items[i].items)){
					
				}else{
					clearTreeBoxChecked(items[i].items);
				}
			}
		}
		
		
		//当资源属于选中的操作员时，菜单中的资源打勾并高亮
		function setTreeBoxChecked(items) {
			if( null == items ) return;
			for(var i = 0; i < items.length; i++ ) {
				if(sessionStorage.getItem("selectedAuthIdArray").indexOf(items[i].resourceId) != -1) {
					items[i].checked = true;
					items[i].style = {
						general: 'item-highlight',
						content: 'item-highlight'
					}
					eArray.push(items[i].resourceId);
				}
				if(angular.isUndefined(items[i].items)){
					
				}else{
					setTreeBoxChecked(items[i].items);
				}	
			}
			
		}
				
		//点击取消时返回之前选择的
		$scope.cancelSelected = function(){
			clearTreeBoxChecked($scope.data);
			setTreeBoxChecked($scope.data);
		};
		
		
		//编辑操作员资源关系
		$scope.updateOperAuth = function(){
			
			var authArray = new Array();
            for(var i = 0;i < eArray.length;i++){
                var flag = true;
                for(var j = i;j < eArray.length-1;j++){
					if(eArray[i] == eArray[j+1]){
						flag = false;
                        break;
                    }
                }
                if(flag){
                	authArray.push(eArray[i]);
                }
            }
 			
			var data = {"operatorId":sessionStorage.getItem("selectedOperId")
    				,"selectedResource":authArray
    				,"modifyOperatorId":localStorage.getItem("modifyOpeId")};
			
			$http({
				method : 'POST',
				url : '../rest/editOperatorAuth',
				data : data,
				headers : {
					"Content-Type" : "application/json ; charset=UTF-8"
				}
			}).then(function successCallback(response) {
				
			}, function errorCallback(response) {
				
			});
						
		};
				
		// Highlights the item when item is checked
		$scope.onItemCheckedChanged = function(e){
			
			if (e.value){
				e.item.style = {
					general: 'item-highlight',
					content: 'item-highlight'
				}
				eArray.push(e.item.resourceId);
			}else{
				e.item.style = 'initial';
				for (var i = eArray.length - 1; i > -1; i--) {
					if (eArray[i] == e.item.resourceId) {
						eArray.splice(i, 1);
					}
				}
			}

			var refreshTimer = $timeout(function(){
				$treeService.refresh($scope.treeName, e.item);

				$timeout.cancel(refreshTimer);
			}, 1);
			
		}
				
		var expandTimer = $timeout(function(){
			$treeService.expand($scope.treeName);
		}, 1);
		
		
		
	}]);