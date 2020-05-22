	
	var app = angular.module('opeLogin', []);
	app.controller('loginController', function($scope, $http) {
		
		/*用户名非空校验*/
		$scope.id_validate = function() {
			var ope_id = $scope.ope_id;
			if (ope_id.length == 0){
				document.getElementById("id_span").innerHTML = "●用户名不能为空";
				document.getElementById("ope_id").focus();
			}else{
				document.getElementById("id_span").innerHTML = "";
			}
		};
		
		/*密码非空校验*/
		$scope.password_validate = function() {
			var ope_password = $scope.ope_password;
			console.log(ope_password.length);
			if (ope_password.length == 0){
				document.getElementById("password_span").innerHTML = "●密码不能为空";
				document.getElementById("ope_password").focus();
			}else{
				document.getElementById("password_span").innerHTML = "";
			}
		};
		
		
		
		$scope.opeLogin = function() {
			var data = {"operatorId":$scope.ope_id
					   		,"password":$scope.ope_password};
			$http({
				method : 'POST',
				url : '../rest/operatorLogin',
				data : data,
				headers : {
					"Content-Type" : "application/json ; charset=UTF-8"
				}
			}).then(function successCallback(response) {
				console.log(response.data.operatorId);
				localStorage.setItem("modifyOpeId",response.data.operatorId);
				window.location.href = "../index.html";
			}, function errorCallback(response) {
				console.log("can not receive response");
			});
		};
	
	});

