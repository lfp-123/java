var app = angular.module('configManage', ["ngTable", "commonServiceModule", "ui.bootstrap"]);
app.controller("configController", ["$rootScope", "$scope", "$http", "$modal", "NgTableParams", "commonService", function ($rootScope, $scope, $http, $modal, NgTableParams, commonService) {
    var self = this;
    $scope.configInfos = [];
    $scope.configList = [];

    //查询出所有配置
    $rootScope.selectAllConfig = function () {
        $http({
            method: 'POST',
            url: '../rest/queryServerCfg',
            headers: {
                "Content-Type": "application/json ; charset=UTF-8"
            }
        }).then(function successCallback(response) {
            console.log(response.data);
            $scope.configList = response.data;
            self.tableParams = new NgTableParams({count: 10}, {counts: [], dataset: $scope.configList});
        }, function errorCallback(response) {
            console.log("can not receive response");
        });
    }

    $rootScope.selectAllConfig();

    //替换serverType  服务器类型
    var serverTypeNames = {};
    var serverTypeCallback = function (response) {
        $scope.serverTypeDict = response.data;
        if (response.data.length > 0) {
            for (var i = 0; i < response.data.length; i++) {
                serverTypeNames[response.data[i].entryId] = response.data[i].entryName;
            }
        }
    }
    commonService.getDictDefByDictClass(1015, false, serverTypeCallback);

    $scope.getServerTypeNames = function (status) {
        return serverTypeNames[status];
    }
    //替换status 状态
    var statusNames = {};
    var statusCallback = function (response) {
        $scope.statusDict = response.data;
        if (response.data.length > 0) {
            for (var i = 0; i < response.data.length; i++) {
                statusNames[response.data[i].entryId] = response.data[i].entryName;
            }
        }
    }
    commonService.getDictDefByDictClass(1002, false, statusCallback);
    $scope.getStatusNames = function (status) {
        return statusNames[status];
    }
    //替换 transferMode  传输模式
    var transferModeNames = {};
    var transferModeCallback = function (response) {
        $scope.transferModeDict = response.data;
        if (response.data.length > 0) {
            for (var i = 0; i < response.data.length; i++) {
                transferModeNames[response.data[i].entryId] = response.data[i].entryName;
            }
        }
    }
    commonService.getDictDefByDictClass(1016, false, transferModeCallback);
    $scope.getTransferModeNames = function (status) {
        return transferModeNames[status];
    }
    //替换 transProtocol  传输协议
    var transProtocolNames = {};
    var transProtocolCallback = function (response) {
        $scope.transProtocolDict = response.data;
        if (response.data.length > 0) {
            for (var i = 0; i < response.data.length; i++) {
                transProtocolNames[response.data[i].entryId] = response.data[i].entryName;
            }
        }
    }
    commonService.getDictDefByDictClass(1017, false, transProtocolCallback);
    $scope.getTransProtocolNames = function (status) {
        return transProtocolNames[status];
    }
    //替换 fileType 文件类型
    var fileTypeNames = {};
    var fileTypeCallback = function (response) {
        $scope.fileTypeDict = response.data;
        if (response.data.length > 0) {
            for (var i = 0; i < response.data.length; i++) {
                fileTypeNames[response.data[i].entryId] = response.data[i].entryName;
            }
        }
    }
    commonService.getDictDefByDictClass(1018, false, fileTypeCallback);
    $scope.getFileTypeNames = function (status) {
        return fileTypeNames[status];
    }


    //点击添加configInfo
    $scope.selectConfig = function (info) {
        var pos = -1;
        for (var i = 0; i < $scope.configInfos.length; i++) {
            if (info.serverId == $scope.configInfos[i].serverId) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            $scope.configInfos.push(info);
        } else {
            $scope.configInfos.splice(pos, 1);
        }
    }

    $scope.isSelected = function (info) {
        var pos = -1;
        for (var i = 0; i < $scope.configInfos.length; i++) {
            if (info.serverId == $scope.configInfos[i].serverId) {
                pos = i;
                break;
            }
        }
        return pos != -1;
    }


    $scope.openModel = function (type) {
        if (type == "add") {
            $modal.open({
                size: 'md',
                templateUrl: "add.html",//script标签中定义的id
                controller: "configModelCtrl",
                backdrop: "static",
                resolve: {
                    data: function () {//data作为modal的controller传入的参数
                        return [];//用于传递数据
                    },
                    serverTypeDict: function () {
                        return $scope.serverTypeDict;
                    },
                    statusDict: function () {
                        return $scope.statusDict;
                    },
                    transferModeDict: function () {
                        return $scope.transferModeDict;
                    },
                    transProtocolDict: function () {
                        return $scope.transProtocolDict;
                    },
                    fileTypeDict: function () {
                        return $scope.fileTypeDict;
                    }

                }
            });
        } else if (type == "modify") {
            if ($scope.configInfos.length > 1) {
                layer.alert('只能选择一个要修改的配置', {shade: false});
                return;
            }
            if ($scope.configInfos.length != 1) {
                layer.alert('请选择要修改的配置', {shade: false});
                return;
            }
            var modalInstance = $modal.open({
                size: 'md',
                templateUrl: "modify.html",//script标签中定义的id
                controller: "configModelCtrl",
                backdrop: "static",
                resolve: {
                    data: function () {//data作为modal的controller传入的参数
                        console.log(JSON.stringify($scope.configInfos));
                        return $scope.configInfos[0];//用于传递数据
                    },
                    serverTypeDict: function () {
                        return $scope.serverTypeDict;
                    },
                    statusDict: function () {
                        return $scope.statusDict;
                    },
                    transferModeDict: function () {
                        return $scope.transferModeDict;
                    },
                    transProtocolDict: function () {
                        return $scope.transProtocolDict;
                    },
                    fileTypeDict: function () {
                        return $scope.fileTypeDict;
                    }
                }
            });
            modalInstance.result.then(function (selectedItem) {
                if (selectedItem == "cancle") {
                    reData();
                }
            }, function () {
                reData();
            });
        }
    }

    var reData = function () {
        for (var i = 0; i < $scope.configList.length; i++) {
            if ($scope.configInfos[0].schedulerCfgId == $scope.configList[i].schedulerCfgId) {
                $scope.configList[i] = JSON.parse(localStorage.getItem("configSrcData"));
                break;
            }
        }
        $scope.configInfos[0] = JSON.parse(localStorage.getItem("configSrcData"));
        localStorage.setItem("configSrcData", JSON.stringify({}));
        var currPage = self.tableParams.page();
        self.tableParams = new NgTableParams({page: currPage, count: 10}, {counts: [], dataset: $scope.configList});
    }


    self.changePage = changePage;

    function changePage(curPage) {
        if (!/^\-?\d*$/.test(curPage)) {
            return;
        }
        if (curPage <= 0) {
            curPage = 1;
        } else {
            var maxPage = Math.floor((self.tableParams.total() - 1) / self.tableParams.count() + 1);
            if (curPage > maxPage) {
                curPage = maxPage;
            }
        }
        self.tableParams.page(curPage);
    }

    //删除
    $scope.configDelete = function () {
        if ($scope.configInfos.length == 0) {
            layer.alert('请选择要删除的配置', {shade: false});
            return;
        }
        var deleteConfirm = confirm("确定是否删除选中内容?");
        var data = $scope.configInfos;
        //删除选中角色
        if (deleteConfirm == true) {
            $http({
                method: 'POST',
                url: '../rest/deleteServerCfg',
                data: data,
                headers: {
                    "Content-Type": "application/json ; charset=UTF-8"
                }
            }).then(function successCallback(response) {
                layer.alert(response.data.msg, {shade: false});
                $scope.configInfos = [];
                $rootScope.selectAllConfig();
            }, function errorCallback(response) {
                console.log(response.data);
            });
        }
    };

    $scope.parseRoleDesc = function(desc) {
        if(desc != null && desc != '' && desc.length > 20) {
            return desc.substring(0, 20) + "...";
        }
        return desc;
    }

}]);

app.controller('configModelCtrl', function ($rootScope, $scope, $http, $modalInstance, commonService, data, serverTypeDict, statusDict, transferModeDict, transProtocolDict, fileTypeDict) {

    $scope.serverTypeDict = serverTypeDict;
    $scope.statusDict = statusDict;
    $scope.transferModeDict = transferModeDict;
    $scope.transProtocolDict = transProtocolDict;
    $scope.fileTypeDict = fileTypeDict;

    $scope.configInfoInsert = {};
    $scope.configInfo = data;

    function init() {
        if ($scope.configInfo) {
            localStorage.setItem("configSrcData", JSON.stringify($scope.configInfo));
        }
    }

    init();

    //添加配置
    $scope.add = function () {
        var data = $scope.configInfoInsert;
        console.log(data);
        $http({
            method: 'POST',
            url: '../rest/addServerCfg',
            data: data,
            headers: {
                "Content-Type": "application/json"
            }
        }).then(function successCallback(response) {
            layer.alert(response.data.msg, {shade: false});
            $rootScope.selectAllConfig();
            $modalInstance.close();
        }, function errorCallback(response) {
            console.log(response.data);
        });
    };


    //修改配置
    $scope.update = function () {
        var data = $scope.configInfo;
        $http({
            method: 'POST',
            url: '../rest/updateServerCfg',
            data: data,
            headers: {
                "Content-Type": "application/json ; charset=UTF-8"
            }
        }).then(function successCallback(response) {
            layer.alert(response.data.msg, {shade: false});
            console.log(response);
            $rootScope.selectAllConfig();
            $modalInstance.close();
        }, function errorCallback(response) {
            console.log(response);
        });
    }

    $scope.setServerType = function (serverType) {
        console.log(serverType);
        if (serverType == 13) {
            document.getElementById('srcPath').removeAttribute("placeholder");
            document.getElementById('destPath').removeAttribute("placeholder");
            document.getElementById('transProtocol').removeAttribute("placeholder");
        } else {
            document.getElementById('srcPath').setAttribute("placeholder", "必填");
            document.getElementById('destPath').setAttribute("placeholder", "必填");
            document.getElementById('transProtocol').setAttribute("placeholder", "必填");

        }
    }

    //关闭窗口
    $scope.cancel = function () {
        console.log("关闭窗口");
        $modalInstance.close("cancle");
    }
});
