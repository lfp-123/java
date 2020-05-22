(function() {
	/** 首页相关 */
	var homeServiceModule = angular.module('homeServiceModule', []);
	homeServiceModule.factory('homeService', ['$http', function($http) {
		return {
			checkUserRole : function(params, callBack) {
				var httpCfg = {
					method : "post",
					url : "./rest/checkUserRole",
					data: params,
					headers : {
						"Content-Type" : "application/json ; charset=UTF-8"
					}
				};
				$http(httpCfg).then(function successCallback(response) {
					callBack(response);
				}, function errorCallback(response) {
					console.log(response);
					console.log("can not receive response");
				});
			}
		};	
	}]);
})();