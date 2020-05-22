/**
 * 指令式文件上传, 使用简单. 依赖服务: ['angularFileUpload', 'ui.bootstrap']
 */
(function () {
    angular.module('ui.fileupload.modal', ['angularFileUpload'])
        .controller('FileUploadModalCtrl', ['$scope', '$modalInstance', 'FileUploader', '$log', function ($scope, $modalInstance, FileUploader, $log) {
            $scope.uploadSuccessedFiles = []; //$scope.$parent.getNgModelCtrlViewValue() || [];
            $scope.onFileUploadSuccess = function (fileItem) {
                $scope.uploadSuccessedFiles.push(fileItem);
                console.log('$scope.uploadSuccessedFiles.push(fileItem); this length: ' + $scope.uploadSuccessedFiles.length);
            };
            $scope.remove = function (fileItem) {
                var idx = $scope.uploadSuccessedFiles.indexOf(fileItem);
                if (idx != -1) $scope.uploadSuccessedFiles.splice(idx, 1);
            };
            $scope.ok = function () {
                $scope.$parent.responseResult($scope.uploadSuccessedFiles);
                $log.info('Modal dismissed at: ' + new Date());
                $modalInstance.close();
            };
            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };

            //-------------------------------- core begin
            var uploader = $scope.uploader = new FileUploader({
                url: 'Uploadify'
            });

            // FILTERS
           uploader.filters.push({
                name: 'imageFilter',
                fn: function(item /*{File|FileLikeObject}*/, options) {
                    var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
                    return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
                }
            });
            uploader.filters.push({
                name: 'sizeFilter',
                fn: function(item /*{File|FileLikeObject}*/, options) {
                    return item.size/ 1024 <= 1024;
                }
            });

            $scope.removeAll = function(){
                uploader.clearQueue();
                $scope.uploadSuccessedFiles = [];
            }

            // CALLBACKS
            uploader.onWhenAddingFileFailed = function (item /*{File|FileLikeObject}*/, filter, options) {
                //console.info('onWhenAddingFileFailed', item, filter, options);
            };
            uploader.onAfterAddingFile = function (fileItem) {
                //console.info('onAfterAddingFile', fileItem);
            };
            uploader.onAfterAddingAll = function (addedFileItems) {
                //console.info('onAfterAddingAll', addedFileItems);
            };
            uploader.onBeforeUploadItem = function (item) {
                //console.info('onBeforeUploadItem', item);
            };
            uploader.onProgressItem = function (fileItem, progress) {
                //console.info('onProgressItem', fileItem, progress);
            };
            uploader.onProgressAll = function (progress) {
                //console.info('onProgressAll', progress);
            };
            uploader.onSuccessItem = function (fileItem, response, status, headers) {
                //console.info('onSuccessItem', fileItem, response, status, headers);
            };
            uploader.onErrorItem = function (fileItem, response, status, headers) {
                //console.info('onErrorItem', fileItem, response, status, headers);
            };
            uploader.onCancelItem = function (fileItem, response, status, headers) {
                // console.info('onCancelItem', fileItem, response, status, headers);
            };
            uploader.onCompleteItem = function (fileItem, response, status, headers) {
                //console.info('onCompleteItem', fileItem, response, status, headers);
                if(status == 200) $scope.onFileUploadSuccess(response);
            };
            //-------------------------------- core end
        }])

        //指令部分.....................
        .controller('fileUpload1_d', ['$scope', 'FileUploader', '$modal', function ($scope, FileUploader, $modal) {
            var ngModelCtrl = {$setViewValue: angular.noop}; // nullModelCtrl
            this.init = function (ngModelCtrl_) {
                ngModelCtrl = ngModelCtrl_;
            };
            $scope.responseResult = function (uploadedFiles) {
                var fileNames = '';
                var filePaths = '';
                angular.forEach(uploadedFiles, function (file) {
//                    if ($(ngModelCtrl.$viewValue).filter(function () {
//                            return this['filePath'] == file['filePath']
//                        }).length == 0) alert(ngModelCtrl.$viewValue);
                    fileNames += file.fileName;
                    filePaths += file.filePath;
                });
                ngModelCtrl.$setViewValue(fileNames);
                ngModelCtrl.$render();
                $scope.$parent.$$vm.imageSrc = filePaths;
            };
            $scope.getNgModelCtrlViewValue = function () {
                return angular.copy(ngModelCtrl.$viewValue);
            };
            $scope.openFileUploadModal = function (size) {
                $modal.open({
                    templateUrl: 'template/common/file.upload.modal.tpl.html',
                    size: size,
                    scope: $scope,
                    windowClass: 'modal-file-upload',
                    backdrop: 'static',
                    controller: 'FileUploadModalCtrl'
                });
            };
        }])
        .directive('fileUpload1', function () {
            return {
                restrict: 'E',
                scope: {},
                require: ['fileUpload1', '?ngModel'], // get a hold of NgModelController,
                replace: true,
                controller: 'fileUpload1_d',
                template: '<span class="input-group-btn"><button type="button" class="btn btn-default" ng-click="openFileUploadModal()"><i class="glyphicon glyphicon-upload" style="font-size:20px;"></i></button></span>',
                link: function (scope, element, attrs, ctrls) {
                    var FileUpload1Ctrl = ctrls[0], ngModelCtrl = ctrls[1];
                    if (!ngModelCtrl) {
                        return; // do nothing if no ng-model
                    }
                    FileUpload1Ctrl.init(ngModelCtrl);
                }
            };
        });
})();