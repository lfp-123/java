var app = angular.module('lineApp', ['commonServiceModule', 'angularjs-dropdown-multiselect']);

app.controller('lineCtrl', function($scope, commonService) {
	$scope.operatorLevel = localStorage.getItem("operatorLevel");
	$scope.orgs = [];
	$scope.legend = [];
	$scope.xdata = [];
	$scope.sdata = [];
	
	$scope.selectedOrg = [];
	getOrgs($scope, commonService);
	
	$scope.orgTexts = {
		buttonDefaultText: "全部"
	}
	
	$scope.orgsSettings = {
		selectionLimit: 1,
		showUncheckAll: false,
		enableSearch: true,
		smartButtonMaxItems: 1,
		clearSearchOnClose: true,
		displayProp: "value",
		idProperty: "orgId",
		scrollableHeight: '300px', 
		scrollable: true,
		smartButtonTextConverter: function(itemText, originalItem) {
			return originalItem.orgName;
		}
	};
	
	var successCallback = function(response) {
		$scope.legend = [];
		if(response.data && response.data.length > 0) {
			for(var i = 0; i<response.data.length; i ++) {
				$scope.legend.push({name : response.data[i].entryName, cdrType : response.data[i].entryId});
			}
		}
	};
	commonService.getDictDefByDictClass(1001, true, successCallback);

	$scope.startDate = getThisYearFirstMonth();
	$scope.endDate = new moment(new Date()).format('YYYY-MM-DD');

	$scope.analysis = function() {
		var data = {
			startDate : moment($scope.startDate).format("YYYYMMDD"), 
			endDate : moment($scope.endDate).format("YYYYMMDD"),
			dates : []
		};
		if($scope.selectedOrg[0]) {
			data['orgId'] = $scope.selectedOrg[0].orgId;
		}
		var xdata = [];
		var totalInfo = {
			name : '合计',
			type : 'line',
			stack : '合计',
			data : []
		};
		for(var i = moment($scope.startDate).format("YYYYMM"); i <= moment($scope.endDate).format("YYYYMM"); ) {
			xdata.push(i);
			totalInfo.data.push(0);
			i = moment(i + '01').add(1, 'months').format('YYYYMM');
		}
		data.dates = xdata;
		var analysisSucc = function(response) {
			var sdata = [];
			console.log(response);
			for(var j = 0; j < response.data.length; j++) {
				for(var i = 0; i < $scope.legend.length; i++) {
					if($scope.legend[i].cdrType == response.data[j].CDR_TYPE) {
						var tmp = {
							name : $scope.legend[i].name,
							type : 'line',
							stack : $scope.legend[i].name + '总量',
							data : []
						};
						for(var k = 0; k < xdata.length; k++) {
							if(response.data[j][xdata[k]]) {
								totalInfo.data[k] = totalInfo.data[k] + response.data[j][xdata[k]];
								tmp.data.push(response.data[j][xdata[k]]);
							} else {
								totalInfo.data[k] = totalInfo.data[k] + 0;
								tmp.data.push(0);
							}
						}
						sdata.push(tmp);
						break;
					}
				}
			}
			if(sdata.length == 0) {
				layer.alert('没有分析数据', {shade: false}); 
				sdata =  [];
				xdata = [];
			} else {
				sdata.push(totalInfo);
			}
			console.log(sdata);
			$scope.sdata = sdata;
			$scope.xdata = xdata;
		};
		
		commonService.httpPost('../rest/getFeeAnalysisLine', data, analysisSucc);
	}
	$scope.analysis();
	
	$scope.reset = function() {
		$scope.startDate = getThisYearFirstMonth();
		$scope.endDate = new moment(new Date()).format('YYYY-MM-DD');
		$scope.selectedOrg[0] = $scope.orgs[0];
	}
});

app.directive('feeline', function(commonService) {
	return {
		scope : {
			id : "@",
			legend : "=",
			sdata : "=",
			xdata : "="
		},
		restrict : 'E',
		template : '<div style="height:400px;"></div>',
		replace : true,
		link : function($scope, element, attrs, controller) {
			// 初始化echarts实例
			var lineChart = echarts.init(document.getElementById($scope.id), 'macarons');
			$scope.$watchGroup(['sdata', 'xdata', 'legend'], function(newValue, oldValue) {
				if($scope.legend) {
					$scope.legend.push({name : '合计', cdrType : -1});
				}
				// 指定图标的配置和数据
				var lineOption = {
					title : {
						text : '资源使用情况',
						x : 'center',
						y : 'top'
					},
					tooltip : {
						trigger : 'axis',
						formatter : function(a) {
							var text = '';
							if(a.length > 0) {
								text = a[0]['name'] + ' : ' + '<br/>';
								for(var i=0; i<a.length; i++){ 
									text += a[i].marker + ' ' + a[i].seriesName + ' : ' + a[i].value/1000.0 + '<br/>';
								}
							}
							return text;
						}
					},
					legend : {
						data : $scope.legend,
						x : 'center',
						y : 'bottom'
					},
					color : ['#D14342', '#3FB0D0', '#97BC4F', '#EE8436', '#6B4E90'],
					grid : {
						left : '3%',
						right : '4%',
						bottom : '9%',
						containLabel : true
					},
					xAxis : {
						type : 'category',
						boundaryGap : false,
						data : $scope.xdata
					},
					yAxis : {
						type : 'value',
						axisLabel:{
		                    formatter: function (value) {
		                    	return commonService.formatYAxis(value / 1000.0);
		                    }
			            }
					},
					series : $scope.sdata,
					animationDuration: 3000
				};
				// 使用制定的配置项和数据显示图表
				lineChart.setOption(lineOption, true);
			}, true);
		}
	};
});
