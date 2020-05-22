(function() {
	var headerModules = angular.module('headerModule', ['headerServiceModule', 'ui.bootstrap']);
	
	headerModules.controller('headerController', ['$rootScope', '$scope', '$http', 'homeService', '$modal', function($rootScope, $scope, $http, homeService, $modal){
		var htmlDecode = function(str) {  
		    return str.replace(/&#(x)?([^&]{1,5});?/g,function($,$1,$2) {  
		        return String.fromCharCode(parseInt($2 , $1 ? 16:10));  
		    });  
		};
		$scope.loginType = parseInt(localStorage.getItem("loginType"));
		$http({
			method : 'GET',
			url: 'home/csrf.jsp',
			headers : {
				"Content-Type" : "application/json"
			}
		}).then(function successCallback(response) {
			$scope.username = htmlDecode(response.data.username);
			$scope.csrf_parameterNam=response.data.csrf_parameterNam;
			$scope.csrf_token=response.data.csrf_token;
		}, function errorCallback(response) {
			console.log(response);
		});
		
		$scope.showModifyPwd = function() {
			$modal.open({
	        	size: 'md',
	            templateUrl : "modify.html",//script标签中定义的id
	            controller : "headerCtrl",
	            backdrop : "static",
	            resolve : {
	                data : function() {//data作为modal的controller传入的参数
	                    
	                }
	            }
	        });
		}
		
		$scope.changeCls = function(resourceId) {
			if ($scope.resourceId != resourceId) {
				$("#" + $scope.resourceId).removeClass("active");
				$("#" + resourceId).addClass("active");
				$scope.resourceId = resourceId;
			}
		}
		
		//私有变量
		$scope.$$vm = {};
		//点击打开菜单
		$scope.gotoPage = function(item){
			$scope.$emit('gotoPage', item);
		};
		//点击添加页面
		$scope.addTab = function(title, pageUrl){
			$scope.$emit('addTab', title, pageUrl);
		};
		//切换至首页($scope.$emit子向父发送事件)
		$scope.toFirstPage = function(){
			//子 Controller 往父 Controller 上发送事件
			$scope.$emit('toFirstPage');
		};
		
		//根据用户名查找具备的菜单
		$http({
			method : 'POST',
			url: 'rest/findResourceByOperatorId',
			headers : {
				"Content-Type" : "application/json"
			}
		}).then(function successCallback(response) {
			$scope.resourceList = response.data;
		}, function errorCallback(response) {
			console.log("can no receive message");
		});
		
		//退出
		$rootScope.logout = function() {
			layer.confirm('你确定要退出系统吗？', {
	            btn : [ '确定', '取消' ]
	        }, function(index) {
	        	$http({
	    			method : 'POST',
	    			url: 'logout',
	    			data: "_csrf=" + $scope.csrf_token
	    		}).then(function successCallback(response) {
	    			console.log(response);
	    			if(response.status == 200 && response.data.indexOf("账户登录") != -1) {
	    				window.parent.location.href = "login.html";
	    			}
	    		}, function errorCallback(response) {
	    			console.log("logout err");
	    		});
	        	//$("#hideDiv").html("<form action='logout' method='post' name='logoutForm'><input type='hidden' name='_csrf' value='" +  + "' /></form>");
	        	//document.forms['logoutForm'].submit();
	        });
		}
	}]);
	
	headerModules.controller('headerCtrl', function($scope, $http, $modalInstance, data) {
		$scope.modifyPwdInfo = {};
		//更新操作员
	    $scope.modiftyPwd = function() {
	    	if($scope.modifyPwdInfo.oldPassword == null || $scope.modifyPwdInfo.oldPassword == "") {
	    		layer.alert('请输入旧密码');
	    		return;
	    	}
	    	if($scope.modifyPwdInfo.password == null || $scope.modifyPwdInfo.password == "") {
	    		layer.alert('请输入新密码');
	    		return;
	    	}
	    	if($scope.modifyPwdInfo.apassword == null || $scope.modifyPwdInfo.apassword == "") {
	    		layer.alert('请输入确认密码');
	    		return;
	    	}
	    	if(("" + $scope.modifyPwdInfo.password).length < 6) {
	    		layer.alert('密码至少6位', {shade: false});
	    		return;
	    	}
	    	if($scope.modifyPwdInfo.password != $scope.modifyPwdInfo.apassword) {
	    		layer.alert('两次输入的密码不一致', {shade: false});
	    		return;
	    	}
	    	
			//编辑操作员
			$http({
				method : 'POST',
				url : './rest/changeOperPwd',
				data : JSON.stringify($scope.modifyPwdInfo),
				headers : {
					"Content-Type" : "application/json"
				}
			}).then(function successCallback(response) {
				console.log(response);
				if(response.data.code != "0") {
					layer.alert(response.data.msg, {shade: false});
					$modalInstance.close();
					return;
				}
				layer.alert('修改成功', {shade: false}); 
				$modalInstance.close();
				return;
			}, function errorCallback(response) {
				console.log(response.data);
				$modalInstance.close();
			});	
	    }
	    
	    $scope.cancel = function() {
	    	$modalInstance.close();
	    }
	});
})();
