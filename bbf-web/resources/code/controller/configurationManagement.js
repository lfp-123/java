var app = angular.module('configuration', ["ngTable", "commonServiceModule", "ui.bootstrap"]);
app.controller("appConfigController", ["$rootScope","$scope","$http","$modal","NgTableParams","commonService", function($rootScope,$scope,$http,$modal,NgTableParams,commonService){
    var self = this;
    $scope.roleInfos = []; //新增修改值
    $scope.roleList = []; // 查询值
    $scope.dictList=[];   //树菜单
    $scope.show=[];
    $scope.roleInfos_online=[]; // 查询匹配的主机
    $scope.select_SystemId=[]; //选择的系统
    $scope.keyList=[];  //key List
    $scope.selectAllCheckBoxs=[]; //全选 主机列表
    $scope.selectAllCacheConfig=[];
    $rootScope.index =true;
    $rootScope.flag =true;
    $rootScope.len =0;
    $rootScope.selectAllConfig=[];
   //展示下拉菜单
    $http({
        method : 'POST',
        url : '../rest/showDictTree',
        headers : {
            "Content-Type" : "application/json ; charset=UTF-8"
        }
    }).then(function successCallback(response) {
        console.log(response.data);
        $scope.dictList = response.data;


    }, function errorCallback(response) {
        console.log("can not receive response");
    })
    // //查询出所有的数据展示
    // $http({
    //     method : 'POST',
    //     url : '../rest/showAllApp',
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
    //     $scope.roleList = response.data;
    //     self.tableParams = new NgTableParams({count: 10}, {counts:[], dataset: $scope.roleList});
    // }, function errorCallback(response) {
    //     console.log("can not receive response");
    // });

    //从数据字典获取操作员状态
    var roleStatusNames = {};
    var successCallback = function(response) {
        // $scope.roleTypeDict = response.data;
        // $scope.roleStatusDict = response.data;
        if(response.data.length > 0) {
            for(var i=0; i<response.data.length; i++) {
                roleStatusNames[response.data[i].entryId] = response.data[i].entryName;
            }
        }

    }
    commonService.getDictDefByDictClass(1002, false, successCallback);

    $scope.getRoleStatusName = function(status) {
        return roleStatusNames[status];
    }
    $scope.parseRoleDesc = function(desc) {
        if(desc != null && desc != '' && desc.length > 20) {
            return desc.substring(0, 20) + "...";
        }
        return desc;
    }


    //搜索配置角色
    $rootScope.searchRole = function() {
        var count = 0;
        var systemId= $scope.dictId;
        var appName = $scope.appName;
        var appInstanceName = $scope.appInstanceName;
        if(!systemId){
            layer.alert('请选择系统类型', {shade: false});
            return;
        }
        if(!appName){
            layer.alert('请填写应用名', {shade: false});
            return;
        }
        var data = {"systemId":systemId, "appName":appName, "appInstanceName":appInstanceName};
        console.log(data)
        $scope.roleInfos = [];
        $http({
            method : 'POST',
            url : '../rest/searchConfig',
            data : data,
            headers : {
                "Content-Type" : "application/json"
            }
        }).then(function successCallback(response) {

            var len = response.data.length;
            $rootScope.len=len;
            $rootScope.selectAllConfig =[];
            for(var i=0;i<len;i++){
                $rootScope.selectAllConfig.push(response.data[i]);
            }
            if(len == 0) {
                layer.alert('没有数据', {shade: false});
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
    $scope.isSelected = function(info) {
        var pos = -1;
        for(var i=0; i<$scope.roleInfos.length; i++){
            if(info.configSeq == $scope.roleInfos[i].configSeq) {
                pos = i;
                break;
            }
        }
        return pos != -1;
    }
    $scope.selectRole = function(info) {
        console.log(info)
        if(info.isNull == 'null_tr') {
            return;
        }
        var pos = -1;
        for(var i=0; i<$scope.roleInfos.length; i++){
            if(info.configSeq == $scope.roleInfos[i].configSeq) {
                pos = i;
                break;
            }
        }
        if(pos == -1) {
            $scope.roleInfos.push(info);
        } else {
            $scope.roleInfos.splice(pos, 1);
        }


    }
    $scope.isAllSelect = function () {
      var len1 =  $scope.roleInfos.length;
      var len2 =  $rootScope.selectAllConfig.length;

        //相等属于全选后，勾选完毕
        if(len1==$rootScope.len && len2!=0){
            //下一次为取消勾选
            $rootScope.index = false;

            return true;
        }else {
            $rootScope.index = true;

            return false;
        }
    }
    $scope.selectAllcheckbox= function (){
        // 点击事件 初始状态为非全选 初始true
        if($rootScope.index){
            $scope.roleInfos = [].concat($rootScope.selectAllConfig);  // 11111 引用传递;
            //下一次要进入取消勾选状态 改为false;

            $rootScope.index = false;
        }else {
            $scope.roleInfos = [];

            //下一次进入全部勾选状态 改为true;
            $rootScope.index  =true;
        }
        console.log($scope.roleInfos)

    }

    //重置查询条件
    $scope.resetSearch = function(){
        $scope.dictId= null;
        $scope.appName = null;
        $scope.appInstanceName = null;
    };

    $scope.openShow= function (data) {

   var modalInstance =  $modal.open({
            size: 'sm',
            templateUrl : "show.html",//script标签中定义的id
            controller : "showController",
            backdrop : "static",
            resolve : {
                data : function() {//data作为modal的controller传入的参数
                    $scope.show = data
                    return $scope.show;//用于传递数据
                }, roleStatusDict: function() {
                    return $scope.roleStatusDict;
                }
            }
        });
        modalInstance.result.then(function (selectedItem) {
            if(selectedItem == "cancle") {
                reData();
            }
        }, function () {
            reData();
        });
    }

//sm md lg 三种可选大小,这里是模态框的大小
    $scope.openModel = function(type) {
        if(type == "add") {
            $modal.open({
                size: 'md',
                templateUrl : "add.html",//script标签中定义的id
                controller : "roleOperationCtrl",
                backdrop : "static",
                resolve : {
                    data : function() {//data作为modal的controller传入的参数
                        return [];//用于传递数据
                    },
                    roleStatusDict: function() {
                        return $scope.roleStatusDict;
                    }
                }
            });
        } else if (type == "modify") {
            if($scope.roleInfos.length > 1) {
                layer.alert('只能选择一个要修改的应用', {shade: false});
                return;
            }
            if($scope.roleInfos.length != 1) {
                layer.alert('请选择要修改的应用', {shade: false});
                return;
            }
            $scope.updateInitSystemName = $scope.roleInfos[0].systemId;
            console.log('biaoji');
            console.log($scope.roleInfos[0].systemId);
            var modalInstance = $modal.open({
                size: 'md',
                templateUrl : "modify.html",//script标签中定义的id
                controller : "roleOperationCtrl",
                backdrop : "static",
                resolve : {
                    data : function() {//data作为modal的controller传入的参数
                        return $scope.roleInfos;//用于传递数据
                    },
                    roleStatusDict: function() {
                        return $scope.roleStatusDict;
                    }
                }
            });
            modalInstance.result.then(function (selectedItem) {
                if(selectedItem == "cancle") {
                    reData();
                }
            }, function () {
                reData();
            });
        }  else if(type == "push") {
            if($scope.roleInfos.length ==0) {
                layer.alert('请选择要推送的应用', {shade: false});
                return;
            }
            $modal.open({
                size: 'lg',
                templateUrl : "push.html",//script标签中定义的id
                controller : "show_online",
                backdrop : "static",
                resolve : {
                    data : function() {//data作为modal的controller传入的参数
                        return $scope.roleInfos;//用于传递数据
                    },
                    roleStatusDict: function() {
                        return $scope.roleStatusDict;
                    }
                }
            });
        }
    }

    var reData = function() {
         var currPage = self.tableParams.page();
         self.tableParams = new NgTableParams({page: currPage, count: 10}, {counts: [], dataset: $scope.roleList});
        // $rootScope.searchRole();
    }

    //删除角色
    $scope.roleDelete = function() {
        if ($scope.roleInfos.length == 0){
            layer.alert('请选择要删除的配置', {shade: false});
            return;
        }
        var data = $scope.roleInfos;
        layer.confirm("确定是否删除",{btn:['确定','取消'],title:'提示'},function () {
            $http({
                method : 'POST',
                url : '../rest/deleteAppConfig',
                data : data,
                headers : {
                    "Content-Type" : "application/json"
                }
            }).then(function successCallback(response) {
                if(response.data.code != 0 && response.data.code != '0') {
                    layer.confirm('删除成功', {
                        btn: ['确定'] //按钮
                    }, function(){
                        layer.close(layer.index);
                        $scope.roleInfos = [];
                        $rootScope.searchRole();
                    });
                } else {
                    layer.confirm('删除成功', {
                        btn: ['确定'] //按钮
                    }, function(){
                        $scope.roleInfos = [];
                        $rootScope.searchRole();
                        layer.closeAll('dialog');
                    });
                }
            }, function errorCallback(response) {
                console.log(response);
                layer.alert('删除异常！', {shade: false});
            });
        });

    };
}]);
//模态框对应的Controller
app.controller('roleOperationCtrl', function($rootScope, $scope, $http, $modalInstance, commonService, data, roleStatusDict) {
    $scope.roleTypeDict=[];
    $scope.roleStatusDict = [];
    $scope.roleInfos = data;
    $scope.roleInfo = {};
    function initOrgNames() {
        $http({
            method : 'POST',
            url : '../rest/showDictTree',
            headers : {
                "Content-Type" : "application/json ; charset=UTF-8"
            }
        }).then(function successCallback(response) {
            console.log(response.data);
            $scope.roleStatusDict = response.data;
        }, function errorCallback(response) {
            console.log("can not receive response");
        });

        $http({
            method : 'POST',
            url : '../rest/showDictTypeTree',
            headers : {
                "Content-Type" : "application/json ; charset=UTF-8"
            }
        }).then(function successCallback(response) {
            console.log(response.data);
            $scope.roleTypeDict = response.data;
        }, function errorCallback(response) {
            console.log("can not receive response");
        });

    }
    initOrgNames();

    //新增配置管理
    $scope.addRole = function() {
        // $scope.roleInfo.modifyOperatorId = localStorage.getItem("modifyOpeId"); //操作日志管理
        var data = $scope.roleInfo;
        console.log(data);

        if(!data.appName) {
            layer.alert('请填写应用名', {shade: false});
            return;
        }
        if(!data.appInstanceName) {
            layer.alert('请填写应用实例名', {shade: false});
            return;
        }
        if(!data.configType) {
            layer.alert('请选择配置类型', {shade: false});
            return;
        }
        if(!data.systemId) {
            layer.alert('请填写系统名', {shade: false});
            return;
        }

        //添加配置
        $http({
            method : 'POST',
            url : '../rest/addConfig',
            data : data,
            headers : {
                "Content-Type" : "application/json"
            }
        }).then(function successCallback(response) {
             layer.alert('添加成功', {shade: false});
             $rootScope.searchRole();
             $modalInstance.close();
        }, function errorCallback(response) {
            console.log(response);
        });

    };
    $scope.selectConfigType = function (type) {
        console.log(type);
        if(type==9){
            document.getElementById('addFilePath').removeAttribute("disabled");

        }else {
            document.getElementById('addFilePath').setAttribute("disabled","disabled");

        }
    }

    //编辑角色
    $scope.roleUpdate = function() {
        // $scope.roleInfos[0].modifyOperatorId = localStorage.getItem("modifyOpeId");

        var data = $scope.roleInfos[0];
        console.log(data);
        if(!data.appName) {
            layer.alert('请填写应用名', {shade: false});
            return;
        }
        if(!data.appInstanceName) {
            layer.alert('请填写应用实例名', {shade: false});
            return;
        }
        if(!data.configType) {
            layer.alert('请选择配置类型', {shade: false});
            return;
        }
        if(!data.systemId) {
            layer.alert('请填写系统名', {shade: false});
            return;
        }
        $http({
            method : 'POST',
            url : '../rest/editConfig',
            data : data,
            headers : {
                "Content-Type" : "application/json"
            }
        }).then(function successCallback(response) {
            layer.confirm('修改成功', {
                btn: ['确定'] //按钮
            }, function(){
                $rootScope.searchRole();
                $modalInstance.close();
                layer.closeAll('dialog');
            });
            //layer.alert('修改成功', {shade: false});
        }, function errorCallback(response) {
            console.log(response);
        });
    };
    $scope.editConfigType = function (type) {
        console.log(type);
        if(type==9){

            document.getElementById('editPath').removeAttribute("disabled")
        }else {

            document.getElementById('editPath').setAttribute("disabled","disabled");
        }
    }
    $scope.cancel = function() {
        $modalInstance.close("cancle");
    }
});
app.controller("showController",function (data,$rootScope, $scope,$modalInstance) {
    console.log(data);
    $scope.show=data;
    $scope.cancel = function() {
        $modalInstance.close("cancle");
    }
})
app.controller("show_online",function (data,$rootScope,$scope,$modalInstance,$http,NgTableParams) {

    console.log(data);
    $scope.indexHost=true;
    $scope.select_SystemId=data; //存储选择选中key
    $scope.roleInfos_online=[]; //存储在线主机数据
    $scope.select_push_appConfig=[]; //选择推送的主机
    $scope.selectAllhost=[]; //全选主机列表
    //展示对应主机
    var systemId= data[0].systemId;
    var appName = data[0].appName;

    //遍历选中的系统信息
    var datas = {"systemId": systemId, "appName": appName, "appInstanceName": ""};
    $http({
        method: 'POST',
         url: 'http://10.1.8.69:9191/configcenter/getAppList',
        // url: 'http://127.0.0.1:9191/configcenter/getAppList',
        data: datas,
        dataType: 'jsonp',
        headers: {
            "Content-Type": "application/json ; charset=UTF-8"
        }
    }).then(function successCallback(response) {
        console.log(response.data);
        $scope.selectAllhost = response.data;
        $scope.roleInfos_online=response.data;
    }, function errorCallback(response){
        console.log("search fail");
    });

    self.tableParams = new NgTableParams({count: 10}, {counts:[], dataset: $scope.roleInfos_online});
    $scope.cancel = function() {
        $modalInstance.close("cancle");
    }

    $scope.isSelected = function(info) {
        var pos = -1;
        for(var i=0; i< $scope.select_push_appConfig.length; i++){
            if(info.host == $scope.select_push_appConfig[i].host) {
                pos = i;
                break;
            }
        }
        return pos!=-1;
    }

    $scope.selectRole = function(info) {
        var pos = -1;
        for (var i = 0; i < $scope.select_push_appConfig.length; i++) {
            if (info.host == $scope.select_push_appConfig[i].host) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            $scope.select_push_appConfig.push(info);
        } else {
            $scope.select_push_appConfig.splice(pos, 1);
        }
    }
    $scope.isOnlineSelect =function () {
        var len1 =$scope.select_push_appConfig.length;
        if(len1==$scope.selectAllhost.length){
            //下一次为取消勾选
            $scope.indexHost = false;

            return true;
        }else {
            $scope.indexHost = true;

            return false;
        }
    }
    $scope.selectAllCheckBoxss =function () {
        if($scope.indexHost){
            $scope.select_push_appConfig = [].concat( $scope.selectAllhost);  // 11111 引用传递;
            console.log($scope.select_push_appConfig)
            $scope.indexHost = false;
        }else {
            $scope.select_push_appConfig=[];

            $scope.indexHost  =true;
        }
        }

//推送主机信息
    $scope.pushConfig = function () {

        var configKeys=[]; //传输报文configKeys
        var hosts=[];
        console.log($scope.index);
        if(!$rootScope.index){
           configKeys=[];
        }else {
            for(var i=0;i<$scope.select_SystemId.length;i++){
                configKeys.push($scope.select_SystemId[i].configKey);
            }
        }
        if(!$scope.indexHost){
            for (var i=0;i<$scope.selectAllhost.length;i++){
                hosts.push($scope.selectAllhost[i].host);
            }
        }else {
            for (var i=0 ;i<$scope.select_push_appConfig.length;i++){
                hosts.push($scope.select_push_appConfig[i].host);
            }
        }

        console.log(hosts);
        var systemId = $scope.select_SystemId[0].systemId;
        var appName = $scope.select_SystemId[0].appName;

        var data = {  "systemId":systemId,
                "appName":appName,
                "hosts":hosts,
                "configKeys":configKeys
        };
        console.log(data);
        $http({
            method : 'POST',
            url : 'http://10.1.8.69:9191/configcenter/pushConfig',
           // url:'http://127.0.0.1:9191/configcenter/pushConfig',
            data:data,
            dataType:'jsonp',
            headers: {
                "Content-Type" : "application/json ; charset=UTF-8"
            }
        }).then(function successCallback(response) {

            layer.alert('推送成功', {shade: false});
            $rootScope.searchRole();
            $modalInstance.close();

        }, function errorCallback(response) {
            console.log("search fail");
        });
    }
})