//扩展JS类型原型对象
(function () {
    Date.prototype.format = function (format) {
        var o = {
            "M+": this.getMonth() + 1, //month 
            "d+": this.getDate(), //day 
            "h+": this.getHours(), //hour 
            "m+": this.getMinutes(), //minute 
            "s+": this.getSeconds(), //second 
            "q+": Math.floor((this.getMonth() + 3) / 3), //quarter 
            "S": this.getMilliseconds() //millisecond 
        }

        if (/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }

        for (var k in o) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
            }
        }
        return format;
    };
})();

var commonApp = angular.module('upApp.common', ['ui.bootstrap', 'ngDialog']);
//项目的公共服务,提供了一些常用的工具方法
commonApp.factory("upserve", ["$location", "$http", "$modal", "ngDialog", function ($location, $http, $modal, ngDialog) {
    var commonLogic = {
        getUrlParam: function (key) { //通过key获取url中的参数值
            var strHref = window.location.href.toString();
            var intPos = strHref.indexOf("?");
            var strRight = strHref.substr(intPos + 1); //==========获取到右边的参数部分
            var arrTmp = strRight.split("&"); //=============以&分割成数组

            for (var i = 0; i < arrTmp.length; i++) //===========循环数组
            {
                var dIntPos = arrTmp[i].indexOf("=");
                var paraName = arrTmp[i].substr(0, dIntPos);
                var paraData = arrTmp[i].substr(dIntPos + 1);
                if (paraName.toUpperCase() == key.toUpperCase()) {
                    return paraData;
                }
            }
            return "";
        },
        indexOf: function (array, pre, val) { //根据属性键值对获取在数组中的索引
            var result = -1;
            if (this.isNoValue(array)) {
                return result;
            }
            for (var index = 0; index < array.length; index++) {
                value = array[index];
                if (!angular.isObject(value) && value == val) {
                    result = index;
                    break;
                }
                else if (value[pre] == val) {
                    result = index;
                    break;
                }
            }
            return result;
        },
        loading: function () {
            var upoverflow = document.createElement("div");
            upoverflow.className = "upoverflow";
            upoverflow.id = "upoverflow";
            upoverflow.style.zIndex = 9999999999999;
            document.body.appendChild(upoverflow);
            return {
                close: function () {
                    upoverflow = document.getElementById("upoverflow");
                    upoverflow.parentNode.removeChild(upoverflow);
                }
            };
        },
        isNoValue: function (val, isNotDebarSpace) {
            if (!isNotDebarSpace && angular.isString(val)) {
                val = val.replace(/(^\s*)|(\s*$)/g, "");
            }
            return angular.isUndefined(val) || val == null || val === '';
        },
        remove: function (array, pre, val) {//从数组中删除某个值
            var index = commonLogic.indexOf(array, pre, val);
            if (index > -1) {
                array.splice(index, 1);
            }
            else {
                console.error("upserve.remove()方法出错,在给定的数组中未找到需要删除的元素!");
            }
        },
        find: function (array, pre, val) { //在数组中比对查找第一个
            var result = null;
            if (this.isNoValue(array)) {
                return result;
            }

            for (var i = 0; i < array.length; i++) {
                var value = array[i];
                if (value[pre] == val) {
                    result = value;
                    break;
                }
            }
            return result;
        },
        numMulti: function (num1, num2) {
            var baseNum = 0;
            try {
                baseNum += num1.toString().split(".")[1].length;
            } catch (e) {
            }
            try {
                baseNum += num2.toString().split(".")[1].length;
            } catch (e) {
            }
            return Number(num1.toString().replace(".", "")) * Number(num2.toString().replace(".", "")) / Math.pow(10, baseNum);
        },
        numAdd: function (arg1, arg2) {
            var r1, r2, m;
            try { r1 = arg1.toString().split(".")[1].length } catch (e) { r1 = 0 }
            try { r2 = arg2.toString().split(".")[1].length } catch (e) { r2 = 0 }
            m = Math.pow(10, Math.max(r1, r2))
            return (arg1 * m + arg2 * m) / m
        },
        numSub: function (arg1, arg2) {
            return commonLogic.numAdd(arg1, -arg2);
        },
        findAll: function (array, pre, val) { //在数组中查找出所有匹配元素
            var result = [];
            angular.forEach(array, function (value) {
                if (value[pre] == val) {
                    result.push(value);
                }
            });
            return result;
        },
        count: function (array, pre, val) {
            var result = 0;
            angular.forEach(array, function (value) {
                if (value[pre] == val) {
                    result++;
                }
            });
            return result;
        },
        post: function (url, postData, successCallBack, errorCallBack) {
            var authenticityEle = angular.element("input[name=authenticityToken]"); //自动进行安全性验证
            if (angular.isDefined(authenticityEle)) {
                postData.authenticityToken = authenticityEle.val();
            }
            var loadEntity = null;
            if (postData.loading) {
                loadEntity = commonLogic.loading();
            }
            $http.post(url, postData, postCfg).success(function (data) {
                if (loadEntity) {
                    loadEntity.close();
                }
                ajaxSuccessResultFun(data, successCallBack, errorCallBack, url);
            }).error(function (e) {
                commonLogic.alert(url + "发生网络错误!");
            });
        },
        get: function (option) {
            $http.get(option.url).success(function (data) {
                ajaxSuccessResultFun(data, option.success, option.error, option.url);
            }).error(function (e) {
                commonLogic.alert(option.url + "发生网络错误!");
            });
        },
        alert: function (msg, callBackFun) {//消息,回调
            if (this.isNoValue(msg)) {
                msg = "操作成功!";
            }

            var modalInstance = $modal.open({
                size: "sm",
                template: '<div class="modal-header"><h5 class="modal-title">系统提示:<button type="button" ng-click="cancel();" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></h5></div>'
                    + '<div class="modal-body"><div class="row"><div class="col-sm-12"><span class="h5">' + msg + '</span></div></div></div>'
                    + '<div class="modal-footer"><button class="btn btn-primary" ng-click="ok()">确 定</button></div>',
                controller: ["$scope", "$modalInstance", function ($scope, $modalInstance) {
                    $scope.ok = function () {
                        $modalInstance.close();
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    }
                }]
            });
            modalInstance.result.then(function () {
                (callBackFun || angular.noop)();
            }, function () {
                (callBackFun || angular.noop)();
            });
        },
        confirm: function (msg, callBackFun, size) {//消息,回调(回调时,会返回bool值)
            if (!size) {
                size = 350;
            }

            var modalInstance = $modal.open({
                size: size,
                template: '<div class="modal-header"><h5 class="modal-title">系统提示:<button type="button" ng-click="cancel();" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></h5></div>'
                    + '<div class="modal-body"><div class="row"><div class="col-sm-12"><span class="text-warning h4">' + msg + '</span></div></div></div>'
                    + '<div class="modal-footer"><button class="btn btn-primary btn-save" ng-click="ok()">确 定</button><button class="btn btn-default btn-save" ng-click="cancel()">取 消</button></div>',
                controller: ["$scope", "$modalInstance", function ($scope, $modalInstance) {
                    $scope.ok = function () {
                        $modalInstance.close();
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    }
                }]
            });
            modalInstance.result.then(function () {
                (callBackFun || angular.noop)(true);
            }, function () {
                (callBackFun || angular.noop)(false);
            });
        },
        tip: function (msg, time, callBackFun, size) {//消息,时间,回调
            if (commonLogic.isNoValue(size)) {
                size = 300;
            }
            if (commonLogic.isNoValue(time)) {
                time = 1;
            }
            var modalInstance = $modal.open({
                size: size,
                template: '<div class="modal-body"><div class="row"><div class="col-sm-12 text-center"><span class="text-warning h4">' + msg + '</span></div></div></div>',
            });
            setTimeout(function () {
                modalInstance.close();
                (callBackFun || angular.noop)();
            }, time * 1000);
        },
        convertToDate: function (dateStr, format) { //将字符串转化为时间类型 //必须是合法的字符类型// 目的是为了解决某些时间格式字符串,在IE中无法正常转换的问题
            if (commonLogic.isNoValue(dateStr)) {
                return;
            }
            if (commonLogic.isNoValue(format)) {
                format = "yyyy-MM-dd";
            }
            try {

                if (angular.isDate(dateStr)) {
                    return dateStr.format(format);
                }
                return new Date(Date.parse(dateStr.replace(/-/g, "/"))).format(format);
            } catch (e) {
                commonLogic.alert("字符串转时间类型失败! 字符串:" + dateStr);
            }
        },
        uuid: function () {
            var s = [];
            var hexDigits = "0123456789abcdef";
            for (var i = 0; i < 36; i++) {
                s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
            }
            s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
            s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
            s[8] = s[13] = s[18] = s[23] = "-";

            var uuid = s.join("");
            return uuid;
        }
    };
    var ajaxSuccessResultFun = function (data, successCallBack, errorCallBack, url) {
        switch (data.code) {
            case 200:
                (successCallBack || angular.noop)(data.data);
                break;
            case 900:
                window.location.href = "/login";
                break;
            case 901:
                commonLogic.alert("无操作权限,请联系管理员");
                break;
            case 998:
                var error = "无效的参数请求！";
                if (errorCallBack) {
                    errorCallBack(error);
                } else {
                    commonLogic.alert(error);
                }
                break;
            case 999:
                if (errorCallBack) {
                    errorCallBack(data.message);
                } else {
                    if (commonLogic.isNoValue(data.message)) {
                        data.message = "发生错误,错误原因未知! URL:" + url;
                    }
                    commonLogic.alert(data.message);
                }
                break;
            default:
                window.location.href = "/";  //服务端登录状态已失效, 无法返回操作码
                break;
        }
    };
    return commonLogic;
}])
.filter('upDate', ["$filter", "upserve", function ($filter, upserve) {
        return function (input, format) {
            if (!upserve.isNoValue(input)) {
                if (angular.isString(input)) {
                    var pointIndex = input.indexOf('.');
                    if (pointIndex > -1) {//类似： 1990-09-14 22：30：40.000
                        input = input.substr(0, pointIndex);
                    }
                    input = new Date(Date.parse(input.replace(/-/g, "/")));;
                }
                if (upserve.isNoValue(format)) {
                    format = "yyyy-MM-dd";
                }
                input = $filter('date')(input, format);
            }
            return input;
        };
    }])
    .filter('upSubstr', ["upserve", function (upserve) {
        return function (input, length) {
            if (!upserve.isNoValue(input)) {
                if (!angular.isString(input)) {
                    input = input.toString();
                }
                input = input.length > length ? (input.substr(0, length - 3) + '...') : input;
            }
            return input;
        };
    }])
    .filter('upToDate', ["upserve", function (upserve) {
        return function (input) {
            if (!upserve.isNoValue(input)) {
                if (angular.isString(input)) {
                    input = new Date(Date.parse(input.replace(/-/g, "/")));;
                }
            }
            return input;
        };
    }]).directive('onFinishRenderFilters', ["$timeout", function ($timeout) {  //监视ng-repeat 渲染完成
        return {
            restrict: 'A',
            link: function (scope, element, attr) {
                if (scope.$last === true) {
                    $timeout(function () {
                        scope.$emit('ngRepeatFinished');
                    });
                }
            }
        };
    }]).directive('upnumber', ["upserve", function (upserve) {
        return {
            restrict: 'A',
            scope: {
                upmin: '=',
                upmax: '=',
                ngModel: '=',
                exact: '=',
                defaultval: "=",
                changemodel: "=",
                numberchange: "&"
            },
            link: function (scope, element, attr) {
                scope.$watch("ngModel", function (val, oldVal) {
                    if (!upserve.isNoValue(val)) {
                        if (scope.exact && scope.exact > 0) { //可以输入小数
                            var pointIndex = val.toString().indexOf('.');
                            if (pointIndex != -1 && pointIndex < val.toString().length - 2) {
                                val = parseFloat(val).toFixed(scope.exact);
                            }
                        }

                        if (isNaN(val)) {
                            val = oldVal;
                        }
                    }
                    scope.ngModel = val;
                    (scope.numberchange || angular.noop)({ entity: scope.changemodel, val: val });
                });
                element.bind("blur", function () {
                    var val = parseFloat(scope.ngModel);
                    if ((upserve.isNoValue(val) || isNaN(val)) && !upserve.isNoValue(scope.defaultval)) {
                        val = scope.defaultval;
                    }
                    if (!upserve.isNoValue(val)) {
                        if (!upserve.isNoValue(scope.upmin) && val < scope.upmin) {
                            val = scope.upmin;
                        }
                        else if (!upserve.isNoValue(scope.upmax) && val > scope.upmax) {
                            val = scope.upmax;
                        }

                        if (!upserve.isNoValue(scope.exact) && scope.exact > 0) { //可以输入小数
                            val = parseFloat(val).toFixed(scope.exact);
                        }

                        scope.$apply(function () {
                            scope.ngModel = val;
                        });
                    }
                    (scope.numberchange || angular.noop)({ entity: scope.changemodel, val: val });
                });
            }
        };
    }]).directive('upbankcard', ["upserve", function (upserve) {
        return {
            restrict: 'A',
            scope: {
                ngModel: '=',
                verify: '=',
                tooltip: '='
            },
            link: function (scope, element, attr) {
                scope.$watch("ngModel", function (val, oldVal) {
                    if (!upserve.isNoValue(val)) {
                        if (isNaN(val)) {
                            val = oldVal;
                        }
                    }
                    scope.ngModel = val;
                });
                element.bind("blur", function () {
                    var CheckBankNo = function (bankno) {
                        if (upserve.isNoValue(bankno)) {
                            return false;
                        }
                        if (bankno.length < 16 || bankno.length > 19) {
                            return false;
                        }
                        var num = /^\d*$/;  //全数字
                        if (!num.exec(bankno)) {
                            return false;
                        }
                        return true;
                    }

                    var verify = CheckBankNo(scope.ngModel);
                    scope.$apply(function () {
                        scope.verify = verify;
                    });
                });
            }
        };
    }]).directive("upmark", function () {
        return {
            restrict: "E",
            template: "",
            transclude: false,
            replace: true
        };
    });

var transFn = function (data) {
    return $.param(data);
},
postCfg = {
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    transformRequest: transFn
};

window.onload = function () {
    angular.element("a[href=#]").attr("href", "javascript:void(0);return false;");
};

var _hmt = _hmt || [];
