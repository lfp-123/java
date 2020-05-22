var app = angular.module('configManage', ["ngTable", "commonServiceModule", "ui.bootstrap"]);
app.controller("configController", ["$rootScope", "$scope", "$http", "$modal", "NgTableParams", "commonService", function ($rootScope, $scope, $http, $modal, NgTableParams, commonService) {
    var self = this;
    $scope.configInfos = [];
    $scope.configList = [];

    //查询出所有的角色
    $rootScope.selectAllConfig = function () {
        $http({
            method: 'POST',
            url: '../rest/queryTimingSchedulerCfg',
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

    //替换cdrType  话单类型
    var cdrTypeNames = {};
    var cdrTypeCallback = function (response) {
        $scope.cdrTypeDict = response.data;
        if (response.data.length > 0) {
            for (var i = 0; i < response.data.length; i++) {
                cdrTypeNames[response.data[i].entryId] = response.data[i].entryName;
            }
        }
    }
    commonService.getDictDefByDictClass(1001, false, cdrTypeCallback);

    $scope.getCdrTypeNames = function (status) {
        return cdrTypeNames[status];
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

    //替换 schedulerType 调度器用途
    var schedulerTypeNames = {};
    var schedulerTypeCallback = function (response) {
        $scope.schedulerTypeDict = response.data;
        if (response.data.length > 0) {
            for (var i = 0; i < response.data.length; i++) {
                schedulerTypeNames[response.data[i].entryId] = response.data[i].entryName;
            }
        }
    }

    commonService.getDictDefByDictClass(1013, false, schedulerTypeCallback);
    $scope.getSchedulerTypeNames = function (status) {
        return schedulerTypeNames[status];
    }

    //替换 syncCycle 周期类型
    var syncCycleNames = {};
    var syncCycleCallback = function (response) {
        $scope.syncCycleDict = response.data;
        if (response.data.length > 0) {
            for (var i = 0; i < response.data.length; i++) {
                syncCycleNames[response.data[i].entryId] = response.data[i].entryName;
            }
        }
    }
    commonService.getDictDefByDictClass(1014, false, syncCycleCallback);
    $scope.getSyncCycleNames = function (status) {
        return syncCycleNames[status];
    }

    //点击添加configInfo
    $scope.selectConfig = function (info) {
        var pos = -1;
        for (var i = 0; i < $scope.configInfos.length; i++) {
            if (info.schedulerCfgId == $scope.configInfos[i].schedulerCfgId) {
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
            if (info.schedulerCfgId == $scope.configInfos[i].schedulerCfgId) {
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
                    cdrTypeDict: function () {
                        return $scope.cdrTypeDict;
                    },
                    statusDict: function () {
                        return $scope.statusDict;
                    },
                    schedulerTypeDict: function () {
                        return $scope.schedulerTypeDict;
                    },
                    syncCycleDict: function () {
                        return $scope.syncCycleDict;
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
                    cdrTypeDict: function () {
                        return $scope.cdrTypeDict;
                    },
                    statusDict: function () {
                        return $scope.statusDict;
                    },
                    schedulerTypeDict: function () {
                        return $scope.schedulerTypeDict;
                    },
                    syncCycleDict: function () {
                        return $scope.syncCycleDict;
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
                url: '../rest/deleteTimingSchedulerCfg',
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

    $scope.formatDateTime = function (inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
    }


}]);

app.controller('configModelCtrl', function ($compile, $rootScope, $scope, $http, $modalInstance, commonService, data, cdrTypeDict, schedulerTypeDict, statusDict, syncCycleDict) {
    $scope.cdrTypeDict = cdrTypeDict;
    $scope.statusDict = statusDict;
    $scope.schedulerTypeDict = schedulerTypeDict;
    $scope.syncCycleDict = syncCycleDict;
    $scope.configInfoInsert = {};
    $scope.configInfo = data;

    function init() {
        if ($scope.configInfo) {
            localStorage.setItem("configSrcData", JSON.stringify($scope.configInfo));
            // $scope.setCdrType($scope.configInfo[0].schedulerType);
        }
        if ($scope.configInfo.schedulerType == 13) {
            $("#cdrType").val("0");
            $scope.cdrTypeDict = [cdrTypeDict[0], cdrTypeDict[3]];
        } else {
            $scope.cdrTypeDict = cdrTypeDict;
        }
    }

    init();

    //添加配置
    $scope.add = function () {
        var data = $scope.configInfoInsert;
        console.log(data);
        $http({
            method: 'POST',
            url: '../rest/addTimingSchedulerCfg',
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
            url: '../rest/updateTimingSchedulerCfg',
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

    $scope.setSycleUpdate = function (syncCycleDict) {
        console.log(syncCycleDict);
        $("#cycleValue").remove();
        var $cycleValue = $("<input type=\"text\" id=\"cycleValue\" class=\"form-control\">");
        if (syncCycleDict == 1) {
            $("#cycleValueDiv").append($cycleValue);
            angular.element(document.getElementById("cycleValue")).attr("ng-model", "configInfo.cycleValue");
            $compile(angular.element(document.getElementById("cycleValue")))($scope);
            document.getElementById('cycleValue').setAttribute("placeholder", "30-每30分执行一次");
        } else if (syncCycleDict == 2) {
            $("#cycleValueDiv").append($cycleValue);
            angular.element(document.getElementById("cycleValue")).attr("ng-model", "configInfo.cycleValue");
            $compile(angular.element(document.getElementById("cycleValue")))($scope);
            document.getElementById('cycleValue').setAttribute("placeholder", "2-每2小时执行一次");
        } else if (syncCycleDict == 3) {
            $("#cycleValueDiv").append($cycleValue);
            angular.element(document.getElementById("cycleValue")).attr("ng-model", "configInfo.cycleValue");
            $compile(angular.element(document.getElementById("cycleValue")))($scope);
            document.getElementById('cycleValue').setAttribute("placeholder", "0130-1点30分");
        } else if (syncCycleDict == 4) {
            $("#cycleValueDiv").append($cycleValue);
            angular.element(document.getElementById("cycleValue")).attr("ng-model", "configInfo.cycleValue");
            $compile(angular.element(document.getElementById("cycleValue")))($scope);
            document.getElementById('cycleValue').setAttribute("placeholder", "1,0130-每周一1点30分");
        } else if (syncCycleDict == 5) {
            // $cycleValue = $("<select ng-model=\"configInfo.cycleValue\" id=\"cycleValue\" class=\"form-control\">\n" +
            //    "<option value=\"51\">每月第一天</option>\n" +
            //    "<option value=\"52\">每月最后一天</option>\n" +
            //    " </select>");
            $("#cycleValueDiv").append($cycleValue);
            angular.element(document.getElementById("cycleValue")).attr("ng-model", "configInfo.cycleValue");
            $compile(angular.element(document.getElementById("cycleValue")))($scope);
            document.getElementById('cycleValue').setAttribute("placeholder", "1,0130-每月1号1点30分");
        } else if (syncCycleDict == 6) {
            $cycleValue = $("<select ng-model=\"configInfo.cycleValue\" id=\"cycleValue\" class=\"form-control\">\n" +
                "<option selected='selected' value=\"0101000000\">每年第一天</option>\n" +
                "<option value=\"1231000000\">每年最后一天</option>\n" +
                " </select>");
            $("#cycleValueDiv").append($cycleValue);
            angular.element(document.getElementById("cycleValue")).attr("ng-model", "configInfo.cycleValue");
            $compile(angular.element(document.getElementById("cycleValue")))($scope);
        }
    }

    $scope.setSycleInsert = function (syncCycleDict) {
        console.log(syncCycleDict);
        $("#cycleValue").remove();
        var $cycleValue = $("<input type=\"text\" id=\"cycleValue\" class=\"form-control\">");
        if (syncCycleDict == 1) {
            $("#cycleValueDiv").append($cycleValue);
            angular.element(document.getElementById("cycleValue")).attr("ng-model", "configInfoInsert.cycleValue");
            $compile(angular.element(document.getElementById("cycleValue")))($scope);
            document.getElementById('cycleValue').setAttribute("placeholder", "30-每30分执行一次");
        } else if (syncCycleDict == 2) {
            $("#cycleValueDiv").append($cycleValue);
            angular.element(document.getElementById("cycleValue")).attr("ng-model", "configInfoInsert.cycleValue");
            $compile(angular.element(document.getElementById("cycleValue")))($scope);
            document.getElementById('cycleValue').setAttribute("placeholder", "2-每2小时执行一次");
        } else if (syncCycleDict == 3) {
            $("#cycleValueDiv").append($cycleValue);
            angular.element(document.getElementById("cycleValue")).attr("ng-model", "configInfoInsert.cycleValue");
            $compile(angular.element(document.getElementById("cycleValue")))($scope);
            document.getElementById('cycleValue').setAttribute("placeholder", "0130-1点30分");
        } else if (syncCycleDict == 4) {
            $("#cycleValueDiv").append($cycleValue);
            angular.element(document.getElementById("cycleValue")).attr("ng-model", "configInfoInsert.cycleValue");
            $compile(angular.element(document.getElementById("cycleValue")))($scope);
            document.getElementById('cycleValue').setAttribute("placeholder", "1,0130-每周一1点30分");
        } else if (syncCycleDict == 5) {
            // $cycleValue = $("<select ng-model=\"configInfo.cycleValue\" id=\"cycleValue\" class=\"form-control\">\n" +
            //    "<option value=\"51\">每月第一天</option>\n" +
            //    "<option value=\"52\">每月最后一天</option>\n" +
            //    " </select>");
            $("#cycleValueDiv").append($cycleValue);
            angular.element(document.getElementById("cycleValue")).attr("ng-model", "configInfoInsert.cycleValue");
            $compile(angular.element(document.getElementById("cycleValue")))($scope);
            document.getElementById('cycleValue').setAttribute("placeholder", "1,0130-每月1号1点30分");
        } else if (syncCycleDict == 6) {
            $cycleValue = $("<select  id=\"cycleValue\" class=\"form-control\">\n" +
                "<option value=\"0101000000\">每年第一天</option>\n" +
                "<option value=\"1231000000\">每年最后一天</option>\n" +
                " </select>");
            $("#cycleValueDiv").append($cycleValue);
            angular.element(document.getElementById("cycleValue")).attr("ng-model", "configInfoInsert.cycleValue");
            $compile(angular.element(document.getElementById("cycleValue")))($scope);
        }
    }


    $scope.setCdrType = function (schedulerType) {
        if (schedulerType == 10) {
            $("#cdrType").val("0");
            document.getElementById('cdrType').setAttribute("disabled", "disabled");
            $scope.syncCycleDict = syncCycleDict;
        } else {
            $scope.configInfoInsert.cdrType = cdrTypeDict[0].entryId;
            document.getElementById('cdrType').removeAttribute("disabled");
            if (schedulerType == 13) {
                $("#cdrType").val("0");
                $("#syncCycle").val("4");
                $scope.cdrTypeDict = [cdrTypeDict[0], cdrTypeDict[3]];
                $scope.syncCycleDict = [syncCycleDict[4]];
            } else if (schedulerType == 11) {
                $("#syncCycle").val("4");
                $scope.syncCycleDict = [syncCycleDict[4]];
                $scope.cdrTypeDict = cdrTypeDict;
            } else {
                $scope.cdrTypeDict = cdrTypeDict;
                $scope.syncCycleDict = syncCycleDict;
            }
        }

    }

    $scope.updateCdrType = function (schedulerType) {
        console.log(schedulerType);
        if (schedulerType == 10) {
            $("#cdrType").val("0");
            document.getElementById('cdrType').setAttribute("disabled", "disabled");
            $scope.syncCycleDict = syncCycleDict;
        } else {
            $scope.configInfoInsert.cdrType = cdrTypeDict[0].entryId;

            document.getElementById('cdrType').removeAttribute("disabled");
            if (schedulerType == 13) {
                $("#cdrType").val("0");
                $("#syncCycle").val("4");
                $scope.cdrTypeDict = [cdrTypeDict[0], cdrTypeDict[3]];
                $scope.syncCycleDict = [syncCycleDict[4]];
            } else if (schedulerType == 11) {
                $("#syncCycle").val("4");
                $scope.syncCycleDict = [syncCycleDict[4]];
                $scope.cdrTypeDict = cdrTypeDict;
            } else {
                $scope.cdrTypeDict = cdrTypeDict;
                $scope.syncCycleDict = syncCycleDict;
            }
        }
    }

    //关闭窗口
    $scope.cancel = function () {
        console.log("关闭窗口");
        $modalInstance.close("cancle");
    }

});


