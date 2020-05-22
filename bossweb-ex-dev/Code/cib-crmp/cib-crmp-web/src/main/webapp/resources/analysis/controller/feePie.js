var app = angular.module('pieApp', ['commonServiceModule', 'angularjs-dropdown-multiselect']);

app.controller('pieCtrl', function($scope, commonService) {
	$scope.operatorLevel = localStorage.getItem("operatorLevel");
	$scope.orgs = [];
	$scope.legend = [ '' ];
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
	commonService.getDictDefByDictClass(1001, false, successCallback);
	
	$scope.startDate = getThisYearFirstMonth();
	$scope.endDate = new moment(new Date()).format('YYYY-MM-DD');
	
	$scope.analysis = function() {
		var analysisSucc = function(response) {
			var sdata = [];
			for(var j = 0; j<response.data.length; j ++) {
				for(var i = 0; i<$scope.legend.length; i ++) {
					if($scope.legend[i].cdrType == response.data[j].cdrType) {
						sdata.push({value : response.data[j].totalFee, name : $scope.legend[i].name});
						break;
					}
				}
			}
			if(sdata.length == 0) {
				layer.alert('没有分析数据', {shade: false}); 
				sdata =  [];
			}
			$scope.sdata = sdata;
		};
		var data = {
			startDate : moment($scope.startDate).format("YYYYMMDD"), 
			endDate : moment($scope.endDate).format("YYYYMMDD")
		};
		if($scope.selectedOrg[0]) {
			data['orgId'] = $scope.selectedOrg[0].orgId;
		}
		commonService.httpPost('../rest/getFeeAnalysisPie', data, analysisSucc);
	}
	$scope.analysis();
	
	$scope.reset = function() {
		$scope.startDate = getThisYearFirstMonth();
		$scope.endDate = new moment(new Date()).format('YYYY-MM-DD');
		$scope.selectedOrg[0] = $scope.orgs[0];
	}
});

app.directive('feepie', function() {
	return {
		scope : {
			id : "@",
			legend : "=",
			// item: "=",
			sdata : "="
		},
		restrict : 'E',
		template : '<div style="height:400px;"></div>',
		replace : true,
		link : function($scope, element, attrs, controller) {
			var pieChart = echarts.init(document.getElementById($scope.id), 'macarons');
			$scope.$watchGroup(['sdata', 'legend'], function(newValue, oldValue, scope) {
				var pieOption = {
					title : {
						text : '资源类型费用占比分析',// 标题说明
						x : 'center'// 居中
					},
					// 提示框，鼠标悬浮交互时的信息提示
					tooltip : {
						show : true,
						trigger : 'item',
						formatter : function(a) {
							if(a) {
								return a['name'] + ' : ' + a['value']/1000.0 + '(' + a['percent'] + '%)';
							}
							return '';
						}
					},
					// 图例
					legend : {
						x : 'center',
						y : 'bottom',
						data : $scope.legend
					},
					color : ['#D14342', '#3FB0D0', '#97BC4F', '#EE8436', '#6B4E90'],
					animationDelay: 1000,
					animationDuration: 2000,
					// 数据内容数组
					series : [ {
						name : '资源',
						type : 'pie',
						radius : "55%",
						center : [ '50%', '60%' ],
						data : $scope.sdata,
						itemStyle : {
							emphasis : {
								shadowBlur : 10,
								shadowOffsetX : 0,
								shadowColor : 'rgba(0, 0, 0, 0.5)'
							},
							normal: { 
                                label: { 
                                   show: true, 
                                   formatter : function(a) {
                                	   return a['name'] + ' : ' + a['value']/1000.0 + '(' + a['percent'] + '%)';
           						   }
                                }, 
                                labelLine :{show:true}
                            }
						}
					} ]

				};
				pieChart.setOption(pieOption, true);
			}, true);
		}
	};
});