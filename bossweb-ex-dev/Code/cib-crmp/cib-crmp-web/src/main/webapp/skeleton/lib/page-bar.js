﻿app.directive("pageDirective", function() {
	return {
		restrict : "EA",
		templateUrl : "../page-bar.html",
		replace : true,
		controller : function($scope, $http) {
			// 配置
			$scope.count = 0;
			//默认每次请求10行，可通过在ng-init里进行设置其它值
			if ($scope.p_pernum == undefined) {
				$scope.p_pernum = 10;
			}
			// 变量
			$scope.p_current = 1;
			$scope.p_all_page = 0; //总共有多少页
			$scope.pages = [];
			$scope.data = {};

			// 获取数据
			var _get = function(page, size, callback) {
				$scope.data.unitName = [];
				if ( typeof($scope.selectedOrg) != "undefined" && $scope.selectedOrg.length > 0){
					for (var i = 0;i < $scope.selectedOrg.length;i++){
						$scope.data.unitName.push($scope.selectedOrg[i].orgId);
					}
				}
				
				$scope.data.callType = "";
				$scope.data.realCallTypes = "";
				if ( typeof($scope.selCallType) != "undefined" && typeof($scope.selCallType.entryId) != "undefined"){
					$scope.data.callType = $scope.selCallType.entryId;
				}
				if (  typeof($scope.selCallType) != "undefined" && typeof($scope.selCallType.entryDesc) != "undefined"){
					$scope.data.callType = $scope.selCallType.entryId;
					$scope.data.realCallTypes = $scope.selCallType.entryDesc;
				}
				
				$scope.data.beginDate = "";
				if ( typeof($scope.beginDate) != "undefined" ){
					$scope.data.beginDate = $scope.beginDate;
				}
				$scope.data.endDate = "";
				if ( typeof($scope.endDate) != "undefined" ){
					$scope.data.endDate = $scope.endDate;
				}
				$scope.data.partCount = "";
				if ( typeof($scope.selPartCount) != "undefined" ){
					$scope.data.partCount = $scope.selPartCount.entryId;
				}

				$http(
						{
							method : "POST",
							url : $scope.url + "?page=" + page
									+ "&size=" + size,
							data : $scope.data
						}).then(
						function successCallback(res) {
//							layer.close(index);
							if (res && res.status == 200) {
								$scope.count = res.data.cnt;
								$scope.list = res.data.list;
								if ($scope.count == 0) {
									layer.alert('没有符合条件的记录！', {shade: false});
								}
								if (res.data.list.length < $scope.p_pernum){
									for(i = res.data.list.length % $scope.p_pernum; i < $scope.p_pernum; i++) {
										$scope.list.push({});
									}
								}
								$scope.p_current = page;
								$scope.p_all_page = Math.ceil($scope.count
										/ $scope.p_pernum);
								reloadPno();
								callback();
							} else {
								layer.alert("加载数据失败");
							}
						}, function errorCallback(res) {
//							layer.close(index);
							layer.alert("数据加载失败");
						});
			};
			// 初始化第一页
			_get($scope.p_current, $scope.p_pernum, function() {
				
			});

			// 上一页
			$scope.pre_index = function() {
				if ($scope.p_current > 1) {
					$scope.load_page(--$scope.p_current);
				}
			}

			// 下一页
			$scope.next_index = function() {
				if ($scope.p_current < $scope.p_all_page) {
					$scope.load_page(++$scope.p_current);
				}
			}

			// 跳转
			$scope.go_index = function() {
				if ($scope.p_go_page == undefined || $scope.p_go_page == "") {
					layer.alert("请输入跳转页码");
					return;
				}
				if ($scope.p_go_page.indexOf(".") != -1) {
					layer.alert("页码只能输入整数");
					return;
				}
				var inx = parseInt($scope.p_go_page);
				if (!isNaN(inx)) {
					if (inx > $scope.p_all_page) {
						inx = $scope.p_all_page;
					} else if (inx < 1) {
						inx = 1;
					}
					$scope.load_page(inx);
				} else {
					layer.alert("页码" + $scope.p_go_page + "不合法");
				}
				$scope.p_go_page = "";
			}

			// 首页
			$scope.p_index = function() {
				if ($scope.p_current != 1) {
					$scope.load_page(1);
				}
			}
			// 尾页
			$scope.p_last = function() {
				if ($scope.p_all_page > 0 && $scope.p_current != $scope.p_all_page) {
					$scope.load_page($scope.p_all_page);
				}
			}
			// 加载某一页
			$scope.load_page = function(page) {
				_get(page, $scope.p_pernum, function() {
					
				});
			};
			// 初始化页码
			var reloadPno = function() {
				$scope.pages = calculateIndexes($scope.p_current,
						$scope.p_all_page, 5);
			};
			// 分页算法
			var calculateIndexes = function(current, length, displayLength) {
				var indexes = [];
				var start = Math.floor(current - displayLength / 2);
				var end = Math.floor(current + displayLength / 2);
				if (start <= 1) {
					start = 1;
					end = start + (displayLength - 1);
					if (end >= length - 1) {
						end = length - 1;
					}
				}
				if (end >= length - 1) {
					end = length;
					start = end - (displayLength - 1);
					if (start <= 1) {
						start = 1;
					}
				}
				for (var i = start; i <= end; i++) {
					indexes.push(i);
				}
				return indexes;
			};
		}
	};
});