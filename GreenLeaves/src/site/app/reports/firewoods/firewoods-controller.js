(function () {
//    angular.module("firewoodsModule", ['chart.js', 'ngAnimate', 'ngSanitize', 'ui.bootstrap']);
    angular.module("firewoodsModule", ['ui.bootstrap', 'chart.js']);

    angular.module("firewoodsModule")
            .factory("firewoodsFactory", function ($http, systemConfig) {
                var factory = {};

                //load all branch
                factory.findAllBranch = function (callback) {
                    var url = systemConfig.apiUrl + "/api/green-leaves/firewood/all-branch";
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {

                            });
                };
                //load all unload point
                factory.findAllUnloadPoint = function (callback) {
                    var url = systemConfig.apiUrl + "/api/green-leaves/firewood/all-unloadpoint";
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {

                            });
                };
                //load all firewood type
                factory.findAllFirewoodType = function (callback) {
                    var url = systemConfig.apiUrl + "/api/green-leaves/firewood/all-firewood-type";
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {

                            });
                };

                //findReportByBranch
                factory.findReportByBranch = function (fromDate, toDate, branch, supno, rno, callback) {
                    var url = systemConfig.apiUrl + "/api/green-leaves/firewood/find-report/" + fromDate + "/" + toDate + "/" + branch + "/" + supno + "/" + rno;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {

                            });
                };

                //find fromDate and toDate
                factory.findbyFromDateToDate = function (fromDate, toDate, callback) {
                    var url = systemConfig.apiUrl + "/api/green-leaves/firewood/from-to-date/" + fromDate + "/" + toDate;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {

                            });
                };
                //find fromDate and toDate and branch
                factory.findbyFromDateToDateAndBranch = function (fromDate, toDate, branch, callback) {
                    var url = systemConfig.apiUrl + "/api/green-leaves/firewood/find-by-date-branch/" + fromDate + "/" + toDate + "/" + branch;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {

                            });
                };
                //find by  unloadpoint wise
                factory.findbyUnloadPointWise = function (fromDate, toDate, branch, callback) {
                    var url = systemConfig.apiUrl + "/api/green-leaves/firewood/find-unloadpoint-wise/" + fromDate + "/" + toDate + "/" + branch;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {

                            });
                };
                //find by  unloadpoint branch wise
                factory.findbyUnloadPointBranchWise = function (fromDate, toDate, branch, unloadpoint, callback) {
                    var url = systemConfig.apiUrl + "/api/green-leaves/firewood/find-unloadpoint-branchwise/" + fromDate + "/" + toDate + "/" + branch + "/" + unloadpoint;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {

                            });
                };
                //find by fiewood type
                factory.findbyFirewoodType = function (fromDate, toDate, branch, firewoodtype, callback) {
                    var url = systemConfig.apiUrl + "/api/green-leaves/firewood/find-firewood-type/" + fromDate + "/" + toDate + "/" + branch + "/" + firewoodtype;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {

                            });
                };


                return factory;
            });

    angular.module("firewoodsModule")
            .controller("firewoodsController", function ($scope, firewoodsFactory, optionPane, $filter) {
                // models
                $scope.ui = {};
                $scope.http = {};
                $scope.model = {};
                $scope.chart = {};
                $scope.ui.theader = {};

                $scope.model.branchList = [];
                $scope.model.firewoodDetails = [];
                $scope.model.unloadPointList = [];
                $scope.model.firewoodTypeList = [];

                //chart
                $scope.chart.labels = [];
                $scope.chart.data = [];
                $scope.chart.colors = ['#FF5733'];
                $scope.chart.options = {
                    responsive: true,
                    maintainAspectRatio: false
                };
                //$scope.chart.series = ['Series A'];


                $scope.http.search = function () {
                    $scope.chart.labels = [];
                    $scope.chart.data = [];

                    if (!$scope.model.supno) {
                        $scope.model.supno = null;
                    }
                    if (!$scope.model.rno) {
                        $scope.model.rno = null;
                    }
                    if (!$scope.model.branch) {
                        $scope.model.branch = null;
                    }
                    $scope.model.unloadpoint = null;
                    $scope.model.firewoodtype = null;

                    var fromdate = $filter('date')($scope.model.fromDate, 'yyyy-MM-dd');
                    var todate = $filter('date')($scope.model.toDate, 'yyyy-MM-dd');

                    if ($scope.model.unloadpoint === null && $scope.model.firewoodtype === null && $scope.model.branch === null) {
                        $scope.ui.theader = 'default';
                        firewoodsFactory.findbyFromDateToDate(fromdate, todate,
                                function (data) {
                                    $scope.model.firewoodDetails = data;
                                    $scope.ui.totalQty = null;
                                    $scope.ui.totalAmount = null;
                                    $scope.totalQty();
                                    angular.forEach(data, function (val) {
                                        $scope.chart.labels.push(val[1]);
                                        $scope.chart.data.push(val[2]);
                                    });
                                    $scope.chart.data.push(0);

                                });

                    }

                    //chart funtions
                    if ($scope.model.branch !== null) {
                        $scope.ui.theader = 'default-branch';

                        firewoodsFactory.findbyFromDateToDateAndBranch(fromdate, todate, $scope.model.branch,
                                function (data) {
                                    $scope.model.firewoodDetails = data;
                                    $scope.ui.totalQty = null;
                                    $scope.ui.totalAmount = null;
                                    $scope.totalQty();
                                    angular.forEach(data, function (val) {
//                                        var todate = $filter('date')($scope.model.toDate, 'yyyy-MM-dd');
                                        $scope.chart.labels.push($filter('date')(val[1], 'yyyy-MM-dd'));
                                        $scope.chart.data.push(val[2]);
                                        console.log($scope.chart.data)
                                    });
                                    $scope.chart.data.push(0);

                                });
                    }


                };

                $scope.http.searchUnloadPointwise = function () {
                    $scope.chart.labels = [];
                    $scope.chart.data = [];

                    if (!$scope.model.supno) {
                        $scope.model.supno = null;
                    }
                    if (!$scope.model.rno) {
                        $scope.model.rno = null;
                    }
                    if (!$scope.model.branch) {
                        $scope.model.branch = null;
                    }
                    if (!$scope.model.unloadpoint) {
                        $scope.model.unloadpoint = null;
                    }
                    if (!$scope.model.firewoodtype) {
                        $scope.model.firewoodtype = null;
                    }

                    var fromdate = $filter('date')($scope.model.fromDate, 'yyyy-MM-dd');
                    var todate = $filter('date')($scope.model.toDate, 'yyyy-MM-dd');
                    if ($scope.model.unloadpoint === null && $scope.model.firewoodtype === null) {
                        $scope.ui.theader = 'unloadPoint';
                        firewoodsFactory.findbyUnloadPointWise(fromdate, todate, $scope.model.branch,
                                function (data) {
                                    $scope.model.firewoodDetails = data;
                                    $scope.ui.totalQty = null;
                                    $scope.ui.totalAmount = null;
                                    $scope.totalQty();
                                    // chart funtions
                                    angular.forEach($scope.model.firewoodDetails, function (val) {
                                        $scope.chart.labels.push(val[0]);
                                        $scope.chart.data.push(val[2]);
                                    });
                                    $scope.chart.data.push(0);
                                }, function (error) {
                        });
                    }


                    if ($scope.model.unloadpoint !== null) {
                        $scope.ui.theader = 'unloadbranchwise';
                        firewoodsFactory.findbyUnloadPointBranchWise(fromdate, todate,
                                $scope.model.branch, $scope.model.unloadpoint,
                                function (data) {
                                    $scope.model.firewoodDetails = data;
                                    $scope.ui.totalQty = null;
                                    $scope.totalQty();
                                    angular.forEach(data, function (val) {
                                        $scope.chart.labels.push(val[1]);
                                        $scope.chart.data.push(val[2]);
//                                        console.log($scope.chart.data)
                                    });
                                    $scope.chart.data.push(0);

                                });
                    }
                    if ($scope.model.firewoodtype !== null) {
                        console.log($scope.model.firewoodtype)
                        $scope.ui.theader = 'firewoodtype';
                        firewoodsFactory.findbyFirewoodType(fromdate, todate,
                                $scope.model.branch, $scope.model.firewoodtype,
                                function (data) {
                                    $scope.model.firewoodDetails = data;
                                    $scope.ui.totalQty = null;
                                    $scope.totalQty();
                                    angular.forEach(data, function (val) {
                                        $scope.chart.labels.push($filter('date')(val[1], 'yyyy-MM-dd'));
                                        $scope.chart.data.push(val[2]);
//                                        console.log($scope.chart.data)
                                    });
                                    $scope.chart.data.push(0);

                                });
                    }
                };


                $scope.ui.onSelectBranch = function ($item, $model, $label) {
                    $scope.model.unloadPointList = [];
                    $scope.model.firewoodTypeList = [];
                    angular.forEach($scope.model.unloadPointTempList, function (data) {
                        if ($model === data.branch) {
                            $scope.model.unloadPointList.push(data);
                        }
                    });
                    angular.forEach($scope.model.firewoodTypeTempList, function (data) {
                        if ($model === data.branch) {
                            $scope.model.firewoodTypeList.push(data);
                        }
                    });
                };

                $scope.totalQty = function () {
                    angular.forEach($scope.model.firewoodDetails, function (val) {
                        if (!$scope.ui.totalQty || $scope.ui.totalQty === null) {
                            $scope.ui.totalQty = val[2];
                            $scope.ui.totalAmount = val[3];
                        } else {
                            $scope.ui.totalQty += val[2];
                            $scope.ui.totalAmount += val[3];
                        }
                    });
                };

                $scope.ui.changeUnloadPoint = function () {
                    $scope.model.firewoodtype = null;
                };
                $scope.ui.changeFirewoodType = function () {
                    $scope.model.unloadpoint = null;
                };

                $scope.ui.unloadpointLabel = function ($model) {
                    var displayText;
                    angular.forEach($scope.model.unloadPointList, function (data) {
                        if (data.indexNo === $model) {
                            displayText = data.name;
                            return;
                        }
                    });
                    return displayText;
                };
                $scope.ui.firewoodtypeLabel = function ($model) {
                    var displayText;
                    angular.forEach($scope.model.firewoodTypeList, function (data) {
                        if (data.indexNo === $model) {
                            displayText = data.name;
                            return;
                        }
                    });
                    return displayText;
                };
                $scope.ui.branchLabel = function ($model) {
                    var displayText;
                    angular.forEach($scope.model.branchList, function (data) {
                        if (data.indexNo === $model) {
                            displayText = data.name;
                            return;
                        }
                    });
                    return displayText;
                };



                $scope.ui.init = function () {


                    firewoodsFactory.findAllBranch(
                            function (data) {
                                $scope.model.branchList = data;
                            });
                    firewoodsFactory.findAllUnloadPoint(
                            function (data) {
//                                $scope.model.unloadPointList = data;
                                $scope.model.unloadPointTempList = data;
                                console.log(data)
                            });
                    firewoodsFactory.findAllFirewoodType(
                            function (data) {
//                                $scope.model.firewoodTypeList = data;
                                $scope.model.firewoodTypeTempList = data;
                            });
                };

                $scope.ui.init();


            });
}());


