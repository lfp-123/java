var app = angular.module('showAppConfig', ["ngTable", "commonServiceModule", "ui.bootstrap"]);
app.controller("showAppController", ["$rootScope","$scope","$http","$modal","NgTableParams","commonService", function($rootScope,$scope,$http,$modal,NgTableParams,commonService){
    var self = this;
    $scope.roleInfos = [];
    $scope.roleList = [];
    $scope.dictList=[];
    $scope.systemName=[];

    // //查询出所有的数据展示
    // $http({
    //     method : 'POST',
    //     url : 'http://10.1.8.69:9191/configcenter/getAppList',
    //     headers : {
    //         "Content-Type" : "application/json ; charset=UTF-8"
    //     }
    // }).then(function successCallback(response) {
    //     var len = response.data.length;
    //     if(len < 10) {
    //         for(var i=10; i>len; i--) {
    //             response.data.push({isNull: 'null_tr'});
    //         }
    //     }
    //     console.log(response.data);
    //
    //     $scope.roleList = response.data;
    //     self.tableParams = new NgTableParams({count: 10}, {counts:[], dataset: $scope.roleList});
    // }, function errorCallback(response) {
    //     console.log("can not receive response");
    // });

    $http({
        method : 'POST',
        url : '../rest/showDictTree',
        headers : {
            "Content-Type" : "application/json ; charset=UTF-8"
        }
    }).then(function successCallback(response) {
        console.log(response.data);
        $scope.systemName =response.data;

        $scope.dictList = response.data;
    }, function errorCallback(response) {
        console.log("can not receive response");
    })

    //查询在线的服务
    $rootScope.searchRole = function() {
        var systemId= $scope.systemId;
        var appName = $scope.appName;
        var appInstanceName = $scope.appInstanceName;

        var data = {"systemId":systemId, "appName":appName, "appInstanceName":appInstanceName};
        console.log(data)
        $scope.roleInfos = [];
        $http({
            method : 'POST',
             url : 'http://10.1.8.69:9191/configcenter/getAppList',
           // url : 'http://127.0.0.1:9191/configcenter/getAppList',
            data: data,
            dataType:'jsonp',
            headers: {
                "Content-Type" : "application/json ; charset=UTF-8"
            }

        }).then(function successCallback(response) {
            var len = response.data.length;
            for(var i=0;i<$scope.systemName.length;i++){
                for(var j=0;j<len;j++){
                    if($scope.systemName[i].DictId == response.data[j].systemId){
                        response.data[j].systemName = ($scope.systemName[i].DictName);
                   }
                }
            }
            if(len == 0) {
                layer.alert('没有在线的主机', {shade: false});
            }
            if(len < 10) {
                for(var i=10; i>len; i= i-1) {
                    response.data.push({isNull: 'null_tr'});
                }
            }

            $scope.roleList = response.data;
            self.tableParams = new NgTableParams({count: 10}, {counts:[], dataset: $scope.roleList});
        }, function errorCallback(response) {
            console.log("search fail");
        });
    };



    //重置查询条件
    $scope.resetSearch = function(){
        $scope.systemId=null ;
        $scope.appName = null;
        $scope.appInstanceName = null;
    };
}]);


