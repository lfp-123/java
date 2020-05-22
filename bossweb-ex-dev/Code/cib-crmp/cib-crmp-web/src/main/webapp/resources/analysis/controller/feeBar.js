var app = angular.module('barApp', ['commonServiceModule', 'angularjs-dropdown-multiselect']);

app.controller('barCtrl', function($scope, commonService) {
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
	
	var getCdrTypes = function(response) {
		$scope.cdrTypes = [];
		if(response.data && response.data.length > 0) {
			$scope.cdrTypes = response.data;
			$scope.selCdrType = response.data[0];
		}
	};
	commonService.getDictDefByDictClass(1001, true, getCdrTypes);
	
	var parseLeafOrgs = function (xdata, data) {
		if(data.length > 0) {
			for(var i = 0; i<data.length; i ++) {
				xdata.push({orgId : data[i].orgId, value : data[i].orgName});
			}
		} else {
			xdata.push({orgId : '', value : ''});
		}
	}
	
	var successCallback = function(response) {
		var xdata = [];
		parseLeafOrgs(xdata, response.data);
		$scope.xdata = xdata;
	};
	commonService.httpPost('../rest/showOrgTree', null, successCallback);
	
	$scope.startDate = getThisYearFirstMonth();
	$scope.endDate = new moment(new Date()).format('YYYY-MM-DD');
	
	$scope.analysis = function() {
		var analysisSucc = function(response) {
			if(response.data.length == 0) {
				layer.alert('没有分析数据', {shade: false}); 
			}
			var xdata = [];
			if($scope.selectedOrg.length > 0) {
				for(var k = 0; k<$scope.selectedOrg.length; k ++) {
					xdata.push({orgId : $scope.selectedOrg[k].orgId, value : $scope.selectedOrg[k].value});
				}
			} else {
				xdata = $scope.orgs;
			}
			var sdata = [];
			for(var i = 0; i<xdata.length; i ++) {
				var had = false;
				for(var j = 0; j<response.data.length; j ++) {
					if(xdata[i].orgId == response.data[j].orgId) {
						sdata.push(response.data[j].totalFee);
						had = true;
						break;
					}
				}
				if(!had) {
					sdata.push(0);
				}
			}
			$scope.xdata = xdata;
			$scope.sdata = sdata;
		};
		var cdrType = -1;
		if($scope.selCdrType) {
			cdrType = $scope.selCdrType.entryId;
		}
		var data = {
			startDate : moment($scope.startDate).format("YYYYMMDD"), 
			endDate : moment($scope.endDate).format("YYYYMMDD"),
			cdrType : cdrType,
			orgs : $scope.selectedOrg
		};
		commonService.httpPost('../rest/getFeeAnalysisBar', data, analysisSucc);
	}
	
	var init = function() {
		var xdata = [];
		var succFun1 = function(response) {
			parseOrgs(xdata, response.data);
			var analysisSucc1 = function(response) {
				if(response.data.length == 0) {
					layer.alert('没有分析数据', {shade: false}); 
				}
				var sdata = [];
				for(var i = 0; i<xdata.length; i ++) {
					var had = false;
					for(var j = 0; j<response.data.length; j ++) {
						if(xdata[i].orgId == response.data[j].orgId) {
							sdata.push(response.data[j].totalFee);
							had = true;
							break;
						}
					}
					if(!had) {
						sdata.push(0);
					}
				}
				$scope.xdata = xdata;
				$scope.sdata = sdata;
			};
			var data = {
				startDate : moment($scope.startDate).format("YYYYMMDD"), 
				endDate : moment($scope.endDate).format("YYYYMMDD"),
				cdrType : -1,
				orgs : []
			};
			commonService.httpPost('../rest/getFeeAnalysisBar', data, analysisSucc1);
		}
		commonService.httpPost('../rest/showOrgTree', null, succFun1);
	}
	init();
	
	$scope.reset = function() {
		$scope.startDate = getThisYearFirstMonth();
		$scope.endDate = new moment(new Date()).format('YYYY-MM-DD');
		$scope.selectedOrg = [];
		$scope.selCdrType = $scope.cdrTypes[0];
	}
});

app.directive('feebar', function(commonService) {
	return {
		scope : {
			id : "@",
			sname : "=",
			sdata : "=",
			xdata : "="
		},
		restrict : 'E',
		template : '<div style="min-height:450px;"></div>',
		replace : true,
		link : function($scope, element, attrs, controller) {
			var barChart = echarts.init(document.getElementById($scope.id), 'macarons');
			$scope.$watchGroup(['sdata', 'xdata'], function(newValue, oldValue, scope) {
				var barOption = {
					title : {
						text : '资源使用情况',
						x : 'center'
					},
					color : [ '#3398DB' ],
					tooltip : {
						trigger : 'axis',
						axisPointer : { // 坐标轴指示器，坐标轴触发有效
							type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
						},
						formatter : function(a) {
							var text = '';
							if(a.length > 0) {
								text = a[0].name + '<br/>' + a[0].marker + ' ' + a[0].seriesName + ' : ' + a[0].value/1000.0;
							}
							return text;
						}
					},
					grid : {
						left : '3%',
						right : '4%',
						bottom : '3%',
						y2 : '40%',
						containLabel : true
					},
					xAxis : [ {
						type : 'category',
						data : $scope.xdata,
						axisLabel: {
                            interval: 0,
                            formatter: function(value) {
                        		if($scope.xdata.length >= 15) {
                        			return value.split("").join("\n");
                        		}
                        		return value;
                        	}
						}
					} ],
					yAxis : [ {
						type : 'value',
						axisLabel:{
							formatter: function (value) {
		                    	return commonService.formatYAxis(value / 1000.0);
		                    }
			            }
					} ],
					series : [ {
						name : $scope.sname,
						type : 'bar',
						data : $scope.sdata
					} ],
					animationDuration: 5000
				};
				if($scope.xdata.length > 40) {
					barOption.dataZoom = [{
						type: 'slider',
						show: true,
						xAxisIndex: [0],
						left: '6.5%',
						bottom: -5,
						start: 0,
						end: 30 //初始化滚动条
					 }];
				}
				if($scope.xdata.length < 15) {
					barOption.series[0].barWidth = '60';
				}
				barChart.setOption(barOption, true);
			}, true);
		}
	};
});