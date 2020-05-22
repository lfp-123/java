var httpServiceModule = angular.module('httpServiceModule',[]);
httpServiceModule.factory('httpService', function($http) {
	return {
		doHttp : function (url, method, json, successFun) {
			var httpCfg = {
				method : method,
				url : url,
				headers : {
					"Content-Type" : "application/json ; charset=UTF-8"
				}
			}
			if(json != null) {
				httpCfg['data'] = json;
			}
			$http(httpCfg).then(function successCallback(response) {
				if(successFun != null) {
					successFun(response);
				}
			}, function errorCallback(response) {
				console.log(response);
				console.log("can not receive response");
			});
		}
	};
});


var commonServiceModule = angular.module('commonServiceModule',['httpServiceModule']);
commonServiceModule.factory('commonService', function(httpService) {
	return {
		getDictDefByDictClass : function(dictClass, defalut, successFun) {
			var fun = function(response) {
				if(defalut == true) {
					response.data.unshift({dictClass : dictClass, dictName : "添加默认", entryId : -1, entryName : "全部"});
				}
				if(successFun != null) {
					successFun(response);
				}
			}
			httpService.doHttp('../rest/getDictDefByDictClass', 'POST', {"dictClass" : dictClass}, fun);
		},
		httpPost : function(url, json, successFun) {
			httpService.doHttp(url, 'POST', json, successFun);
		},
		formatYAxis : function(num) {
			num = num.toString().replace(/\$|\,/g,''); 
			if(isNaN(num)) {
				num = "0"; 
			}
			var sign = (num == (num = Math.abs(num))); 
			num = Math.floor(num*10+0.50000000001); 
			num = Math.floor(num/10).toString(); 
			for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++) {
				num = num.substring(0,num.length-(4*i+3))+','+ 
				num.substring(num.length-(4*i+3)); 
			}
			return (((sign)?'':'-') + num ); 
		},
		reFreshInitData : function(callBack) {
			var url = "../rest/reFreshInitData";
			httpService.doHttp(url, 'POST', null, callBack);
		}
	};
});