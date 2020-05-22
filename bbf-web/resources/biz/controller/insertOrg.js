	var app = angular.module('insertOrg', []);
	app.controller('OrgController', function($scope, $http) {
		$scope.insertOrg = function() {
			var data = {"org_name":$scope.org_name
					   		,"org_desc":$scope.org_desc};
			$http({
				method : 'POST',
				url : 'rest/insertOrganization',
				data : data,
				headers : {
					"Content-Type" : "application/json"
				}
			}).then(function successCallback(response) {
				console.log('can receive json');
			}, function errorCallback(response) {
				console.log('can not receive response');
			});
		};
    });