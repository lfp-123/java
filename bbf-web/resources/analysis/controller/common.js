function getThisYearFirstMonth() {
	var nowDate = new Date();
	var year = nowDate.getFullYear();
	return year + "-01-01";
}

function getOrgs($scope, commonService) {
	var succFun = function(response) {
		var orgs = [{orgId: -1, value: '全部'}];
		//parseOrg(orgs, response.data);
		for(var i = 0; i<response.data.length; i++) {
			orgs.push({orgId: response.data[i].orgId, value: response.data[i].orgNameFull, orgName: response.data[i].orgName});
		}
		$scope.orgs = orgs;
		$scope.selOrg = $scope.orgs[0];
		$scope.selectedOrg.push($scope.orgs[0]);
	}
	commonService.httpPost('../rest/showOrgTree', null, succFun);
}

function parseOrg(orgs, data) {
	if(data.length > 0) {
		for(var i = 0; i<data.length; i ++) {
			if(data[i].childOrgList && data[i].childOrgList.length > 0) {
				parseOrg(orgs, data[i].childOrgList);
			} else {
				orgs.push({orgId: data[i].orgId, value: data[i].orgNameFull, orgName: data[i].orgName});
			}
		}
	} else {
		orgs.push(data);
	}
}
