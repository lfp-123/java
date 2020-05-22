(function() {
	/** 首页相关 */
	var homeModules = angular.module('homeModule', ['homeServiceModule', 'commonServiceModule']);
	// 首页
	homeModules.controller('homeController', ['$scope','homeService','$log', function($scope,homeService,log){
		homeService.checkUserRole(parseInt(localStorage.getItem("loginType")), function(response) {
			if(response.data.code != 0) {
				layer.alert(response.data.msg, {shade: false});
			}
		});
		
		//私有变量
		$scope.$$vm            = {};
		$scope.$$vm.navUrl     = 'home/nav.html';
		$scope.$$vm.contentUrl = 'home/content.html';
		$scope.$$vm.footerUrl  = 'home/footer.html';
		
		var tabCounter = 0;
		var $tab = $('#content-tab');
		var $nav = $tab.find('.am-tabs-nav');
		var $bd = $tab.find('.am-tabs-bd');
		
		//切换点击样式
		$scope.changeCls = function(resourceName) {
			if ($scope.currentResourceName != resourceName) {
				$(".admin-sidebar-list li ul li a:contains(" + $scope.currentResourceName + ")").removeClass("active");
				$(".admin-sidebar-list li ul li a:contains(" + resourceName + ")").addClass("active");
				$scope.currentResourceName = resourceName;
			}
		}
		
		//添加页面签
		$scope.$on('addTab', function(event, title, pageUrl){
			//判断页面是否已打开
			for( var i = 0; i < window.frames.length; i++ ) {
				if( window.frames[i].frameElement.id  == pageUrl ) {
					//页面已打开
					$tab.tabs('open', i+1 );
					$tab.tabs('refresh');
					return;
				}
			}
			var nav = '<li id="navli'+tabCounter+'" class="crmp-active"><span class="am-icon-close"></span>' +'<a href="javascript: void(0)"> ' + title + '</a></li>';
            var content = '<div class="am-tab-panel" style="padding-right:0px;padding-bottom:0px"><iframe frameborder="0" marginwidth="0" marginheight="0" valign="middle" width="100%" height="550" scrolling="no" resize="no" id="'+pageUrl+'" src="'+pageUrl+'">' + tabCounter + '</iframe></div>';
			$nav.append(nav);
			$bd.append(content);
			$nav.on("click", "#navli" + tabCounter + ">a", function() {
				$scope.changeCls($(this).text().trim());
			});
			tabCounter++;
			$tab.tabs('refresh');
			//切换到刚添加的标签页
			var $currNavLi = $('navli'+tabCounter);
			var index = $nav.children('li').index($currNavLi);
			$tab.tabs('open', index );
			$tab.tabs('refresh');
			event.stopPropagation();
		});
		
		$nav.on("click", "li a:contains('首页')", function() {
			$scope.changeCls('首页');
		});
		
		// 移除标签页
		$nav.on('click', '.am-icon-close', function() {
		  var $item = $(this).closest('li');
		  var index = $nav.children('li').index($item);
		  $item.remove();
		  $bd.find('.am-tab-panel').eq(index).remove();
		  
		  $tab.tabs('open', index > 0 ? index - 1 : index + 1);
		  $tab.tabs('refresh');
		  $scope.changeCls($nav.children('li.am-active').find('a').text().trim());
		  event.stopPropagation();
		});
		//切换至首页($scope.$on 接收事件)
		$scope.$on('toFirstPage', function(event){
		  $tab.tabs('open', 0);
		  $tab.tabs('refresh');
		});
		//切换页面,只是更换admin-content页面内容($scope.$on 接收事件)
		$scope.$on('gotoPage', function(event){
			$scope.$$vm.contentUrl = pageUrl;
			event.stopPropagation();
		});
	}])
	// sidebar 导航栏
	.controller('indexController', ['$rootScope', '$scope', '$http', '$interval', 'commonService','$timeout', function($rootScope, $scope, $http, $interval, commonService, $timeout) {
		$scope.operatorLevel = localStorage.getItem("operatorLevel");
		
		$scope.pieFlag = false;
		$scope.lineFlag = false;
		$scope.totalBarFlag = false;
		$scope.barFlag = false;
		
		$scope.legend = [];
		$scope.pieSdata = [ {
			value : 0,
			name : ''
		}];
		
		var successCallback = function(response) {
			$scope.legend = [];
			if(response.data && response.data.length > 0) {
				for(var i = 0; i<response.data.length; i ++) {
					$scope.legend.push({name : response.data[i].entryName, cdrType : response.data[i].entryId});
				}
			}
		};
		var data = {
			dictClass: 1001
		}
		commonService.httpPost('./rest/getDictDefByDictClass', JSON.stringify(data), successCallback);
		
		var search = function() {
			var succ = function(response) {
				console.log(response);
				if(response.data.length == 0) {
					layer.alert('没有数据', {shade: false}); 
				}
				var lineData = response.data.line;
				var pieData = response.data.pie;
				if(lineData.length > 0) {
					$scope.lineFlag = true;
					var line_xdata = response.data.dates;
					var line_sdata = [];
					var totalInfo = {
						name : '合计',
						type : 'line',
						stack : '合计',
						smooth: true,
						data : []
					};
					for(var j = 0; j < lineData.length; j++) {
						for(var i = 0; i < $scope.legend.length; i++) {
							if($scope.legend[i].cdrType == lineData[j].CDR_TYPE) {
								var tmp = {
									name : $scope.legend[i].name,
									type : 'line',
									smooth: true,
									stack : $scope.legend[i].name + '总量',
									data : []
								};
								for(var k = 0; k < line_xdata.length; k++) {
									var t = isNaN(totalInfo.data[k]) ? 0 : totalInfo.data[k];
									if(lineData[j][line_xdata[k]]) {
										totalInfo.data[k] = t + lineData[j][line_xdata[k]];
										tmp.data.push(lineData[j][line_xdata[k]]);
									} else {
										totalInfo.data[k] = t + 0;
										tmp.data.push(0);
									}
								}
								line_sdata.push(tmp);
								break;
							}
						}
					}
					if(totalInfo.data.length > 0) {
						line_sdata.push(totalInfo);
					}
					if(line_sdata.length == 0) {
						line_sdata =  [ {name : '', type : 'line', stack : '总量', data : [ 0 ] } ];
						line_xdata = [''];
					}
					$scope.lineSdata = line_sdata;
					$scope.lineXdata = line_xdata;
				}
				
				if(pieData.length > 0) {
					$scope.pieFlag = true;
					var pie_sdata = [];
					for(var j = 0; j<pieData.length; j ++) {
						for(var i = 0; i<$scope.legend.length; i++) {
							if($scope.legend[i].cdrType == pieData[j].cdrType) {
								pie_sdata.push({value : pieData[j].totalFee, name : $scope.legend[i].name});
								break;
							}
						}
					}
					if(pie_sdata.length == 0) {
						pie_sdata =  [ {value : 0, name : ''} ];
					}
					$scope.pieSdata = pie_sdata;
				}
				
				/*if($scope.operatorLevel == 2) {
					var totalBarData = response.data.totalBar;
					var barData = response.data.bar;
					if(totalBarData.length > 0) {
						$scope.totalBarFlag = true;
						var tbarXdata = [];
						for(var k = 0; k<response.data.orgs.length; k ++) {
							tbarXdata.push({orgId : response.data.orgs[k].orgId, value : response.data.orgs[k].orgName});
						}
						var tbarSdata = [ {
							name : '全部',
							type : 'bar',
							data : []
						} ]
						for(var i = 0; i<tbarXdata.length; i ++) {
							var had = false;
							for(var j = 0; j<totalBarData.length; j ++) {
								if(tbarXdata[i].orgId == totalBarData[j].orgId) {
									tbarSdata[0].data.push(totalBarData[j].totalFee);
									had = true;
									break;
								}
							}
							if(!had) {
								tbarSdata[0].data.push(0);
							}
						}
						$scope.tbarXdata = tbarXdata;
						$scope.tbarSdata = tbarSdata;
					}
					
					if(barData) {
						for(var k = 0; k<$scope.legend.length; k++) {
							if(barData[$scope.legend[k].cdrType] && barData[$scope.legend[k].cdrType].length > 0) {
								$scope.barFlag = true;
								break;
							}
						}
						if(!$scope.barFlag) {
							return;
						}
						var barXdata = [];
						for(var k = 0; k<response.data.orgs.length; k ++) {
							barXdata.push({orgId : response.data.orgs[k].orgId, value : response.data.orgs[k].orgName});
						}
						var barSdata = [];
						for(var k = 0; k<$scope.legend.length; k++) {
							var tmp = {
					            name: $scope.legend[k].name,
					            type: 'bar',
					            data: []
					        };
							var tmpData = barData[$scope.legend[k].cdrType];
							for(var i = 0; i<barXdata.length; i ++) {
								var had = false;
								for(var j = 0; j<tmpData.length; j ++) {
									if(barXdata[i].orgId == tmpData[j].orgId) {
										tmp.data.push(tmpData[j].totalFee);
										had = true;
										break;
									}
								}
								if(!had) {
									tmp.data.push(0);
								}
							}
							barSdata.push(tmp);
						}
						$scope.barXdata = barXdata;
						$scope.barSdata = barSdata;
					}
				}*/
				
				if($scope.operatorLevel == 3) {
					var totalBarData = response.data.totalBar;
					if(totalBarData.length > 0) {
						$scope.totalBarFlag = true;
						var top = sortBarResultByFee(totalBarData);
						var top10asc = top.slice(0, 10);
						var top10desc = top.slice(top.length - 10).reverse();
						console.log(top10desc);
						var orgMap = {};
						for(var k = 0; k<response.data.orgs.length; k ++) {
							orgMap[response.data.orgs[k].orgId] = response.data.orgs[k].orgName;
						}
						
						var tbarSdata = [{
							name : '全部',
							type : 'bar',
							data : []
						}]
						var tbarXdata = [];
						for(var i = 0; i<top10asc.length; i ++) {
							tbarXdata.push({orgId : top10asc[i].orgId, value : orgMap[top10asc[i].orgId]});
							tbarSdata[0].data.push(top10asc[i].totalFee);
						}
						
						var _tbarSdata = [ {
							name : '全部',
							type : 'bar',
							data : []
						} ]
						var _tbarXdata = [];
						for(var i = 0; i<top10desc.length; i ++) {
							_tbarXdata.push({orgId : top10desc[i].orgId, value : orgMap[top10desc[i].orgId]});
							_tbarSdata[0].data.push(top10desc[i].totalFee);
						}
						
						$scope.tbarXdata = tbarXdata;
						$scope.tbarSdata = tbarSdata;
						$scope._tbarXdata = _tbarXdata;
						$scope._tbarSdata = _tbarSdata;
					}
				}
			};
			if($scope.legend.length == 0) {
				var fun = function(response) {
					successCallback(response);
					commonService.httpPost('./rest/getTotalAnalysisResult', null, succ);
				}
				commonService.httpPost('./rest/getDictDefByDictClass', JSON.stringify(data), fun);
			} else {
				commonService.httpPost('./rest/getTotalAnalysisResult', null, succ);
			}
		}
		
		function sortBarResultByFee(barData) {
			var top = [];
			for(var i = 0; i<barData.length; i++) {
				if(barData[i].orgId != 0) {
					top.push(barData[i]);
				}
			}
			for(var i = 0; i<top.length - 1; i++) {
				var index = i;
				for(var j = (i+1); j<top.length; j++) {
					if(top[index].totalFee < top[j].totalFee) {
						 index = j;
					}
				}
				if(index != i) {
					var tmp = top[i];
					top[i] = top[index];
					top[index] = tmp;
				}
			}
			return top;
		}
		
		$timeout(function () {
			$(".myTest").animate({left: "0px", opacity:'1.0'}, 3000);
			search();
		}, 500);
		
		$interval(search, 2 * 60 * 60 * 1000, 100);
	}]).directive('feeline', function(commonService) {
		return {
			restrict : 'EA',
			replace : true,
			scope : {
				id : "@",
				legend : "=",
				lineSdata : "=",
				lineXdata : "=",
				height: '@'
			},
			link : function($scope, element, attrs, controller) {
				$(element).css({
					display:'block',
					height: $scope.height
				});
				var lineChart = echarts.init(element[0]);
				// 初始化echarts实例
				$scope.$watchGroup(['lineSdata', 'lineXdata', 'legend'], function(newValue, oldValue) {
					var leg = $scope.legend;
					leg.push({name : '合计', cdrType : -1});
					
					// 指定图标的配置和数据
					var lineOption = {
						title : {
							text : '资源类型费用分析',
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
							data : leg,
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
							data : $scope.lineXdata
						},
						yAxis : {
							type : 'value',
							axisLabel:{
			                    formatter: function (value) {
			                    	return commonService.formatYAxis(value / 1000.0);
			                    }
				            }
						},
						series : $scope.lineSdata,
						animationDelay: 1000,
						animationDuration: 3000
					};
					// 使用制定的配置项和数据显示图表
					lineChart.setOption(lineOption, true);
				}, true);
			}
		}
	}).directive('feepie', function() {
		return {
			scope : {
				id : "@",
				legend : "=",
				pieSdata : "=",
				height: '@'
			},
			restrict : 'E',
			replace : true,
			link : function($scope, element, attrs, controller) {
				$(element).css({
					display:'block',
					height: $scope.height
				});
				var pieChart = echarts.init(element[0]);
				$scope.$watchGroup(['pieSdata', 'legend'], function(newValue, oldValue) {
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
							orient: 'vertical',
							x: 'right',
					        y: 'center',
							data : $scope.legend
						},
						color : ['#D14342', '#3FB0D0', '#97BC4F', '#EE8436'],
						animationDelay: 2500,
						animationDuration: 2000,
						// 数据内容数组
						series : [ {
							name : '资源',
							type : 'pie',
							radius : "55%",
							center : [ '50%', '60%' ],
							data : $scope.pieSdata,
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
	                                	   if(a) {
	                                		   return a['name'] + ' : ' + a['value']/1000.0 + '(' + a['percent'] + '%)';
	                                	   }
	                                	   return '';
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
	}).directive('totalfeebar', function(commonService) {
		return {
			scope : {
				id : "@",
				tbarSdata : "=",
				tbarXdata : "=",
				tbarSdataa : "=",
				tbarXdataa : "=",
				height: '@',
				level: "="
			},
			restrict : 'E',
			replace : true,
			link : function($scope, element, attrs, controller) {
				$(element).css({
					display:'block',
					height: $scope.height
				});
				var tbarChart = echarts.init(element[0]);
				$scope.$watchGroup(['tbarSdata', 'tbarXdata', 'tbarSdataa', 'tbarXdataa'], function(newValue, oldValue) {
					var titleValue = '部门资源总费用';
					var flag = true;
					if($scope.level == 3) {
						flag = $scope.id == 'totalBarMain';
						titleValue = '资源费用部门TOP10(' + (flag ? '降序':'升序') + ')'; 
					}
					var tbarOption = {
						title : {
							text : titleValue,
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
							splitLine:{  
	                            show:false  
	                        },  
	                        axisLabel : {
	                        	//坐标轴刻度标签的相关设置。
	                        	interval: 0,
	                            formatter : function(params, index) {
	                            	var text = params;
	                            	if(params.length > 5) {
	                            		var tmp =  params.substring(0, 5);
	                            		text = tmp + "...";
	                            	} 
	                            	return text;
	                            }
	                        },
							data : flag ? $scope.tbarXdata : $scope.tbarXdataa
						} ],
						yAxis : [ {
							type : 'value',
							axisLabel:{
			                    formatter: function (value) {
			                    	return commonService.formatYAxis(value / 1000.0);
			                    }
				            }
						} ],
						dataZoom: [{
							type: 'slider',
							show: true,
							xAxisIndex: [0],
							left: flag ? '9.6%' : '6.2%',
							bottom: -5,
							start: 0,
							end: 80 //初始化滚动条
						 }],
						series : flag ? $scope.tbarSdata : $scope.tbarSdataa,
						animationDelay: 2000,
						animationDuration: 5000
					};
					if(flag) {
						if(($scope.tbarXdata) && $scope.tbarXdata.length < 15) {
							tbarOption.series[0].barWidth = '60';
						}
					} else {
						if(($scope.tbarXdataa) && $scope.tbarXdataa.length < 15) {
							tbarOption.series[0].barWidth = '60';
						}
					}
					tbarChart.setOption(tbarOption, true);
				}, true);
			}
		};
	}).directive('feebar', function(commonService) {
		return {
			scope : {
				id : "@",
				barSdata : "=",
				barXdata : "=",
				height: '@'
			},
			restrict : 'E',
			replace : true,
			link : function($scope, element, attrs, controller) {
				$(element).css({
					display:'block',
					height: $scope.height
				});
				var barChart = echarts.init(element[0]);
				$scope.$watchGroup(['barSdata', 'barXdata'], function(newValue, oldValue) {
					var barOption = {
						title : {
							text : '部门各资源费用',
							x : 'center'
						},
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
						color : ['#D14342', '#3FB0D0', '#97BC4F', '#EE8436'],
						xAxis : [ {
							type : 'category',
							splitLine:{  
	                            show:false  
	                        },  
							data : $scope.barXdata,
							axisLabel: {
	                            interval: 0,
	                            formatter: function(value) {
	                        		if($scope.barXdata.length >= 15) {
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
						dataZoom: [{
							type: 'slider',
							show: true,
							xAxisIndex: [0],
							left: '9%',
							bottom: -5,
							start: 10,
							end: 90 //初始化滚动条
						 }],
						series : $scope.barSdata,
						animationDelay: 2000,
						animationDuration: 5000
					};
					barChart.setOption(barOption, true);
				}, true);
			}
		};
	}); 
})();
