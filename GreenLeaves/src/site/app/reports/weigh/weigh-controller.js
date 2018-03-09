(function () {
//    angular.module("greenLeavesWeighModule", ['chart.js', 'ngAnimate', 'ngSanitize', 'ui.bootstrap']);
    angular.module("greenLeavesWeighModule", ['ui.bootstrap', 'chart.js']);

    angular.module("greenLeavesWeighModule")
            .factory("weighFactory", function ($http, systemConfig) {
                var factory = {};

                //load all dates
                factory.findAllDates = function (fromdate, todate, callback) {
                    var url = systemConfig.apiUrl + "/api/green-leaves/weigh/all-dates/" + fromdate + "/" + todate;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {

                            });
                };
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
                //load all Suppliers
                factory.findAllSuppliers = function (callback) {
                    var url = systemConfig.apiUrl + "/api/green-leaves/weigh/all-suppliers";
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {

                            });
                };
                //load all Suppliers
                factory.findAllRoutes = function (callback) {
                    var url = systemConfig.apiUrl + "/api/green-leaves/weigh/all-routes";
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {

                            });
                };
                //load all Collectors
                factory.findAllCollectors = function (callback) {
                    var url = systemConfig.apiUrl + "/api/green-leaves/weigh/all-collectors";
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {

                            });
                };
                //find by date  
                factory.findbyFromDateToDate = function (fromDate, toDate, callback) {
                    var url = systemConfig.apiUrl + "/api/green-leaves/weigh/findby-fromdate-todate/" + fromDate + "/" + toDate;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {

                            });
                };
                //find by branch
                factory.findbyFromDateToDateAndBranch = function (fromDate, toDate, branch, callback) {
                    var url = systemConfig.apiUrl + "/api/green-leaves/weigh/findby-branch/" + fromDate + "/" + toDate + "/" + branch;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {

                            });
                };
                //find by route
                factory.findbyFromDateToDateAndBranchAndRoute = function (fromDate, toDate, branch, route, callback) {
                    var url = systemConfig.apiUrl + "/api/green-leaves/weigh/findby-route/" + fromDate + "/" + toDate + "/" + branch + "/" + route;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {

                            });
                };
                //find by supplier
                factory.findbyFromDateToDateAndBranchAndSupplier = function (fromDate, toDate, branch, supplier, callback) {
                    var url = systemConfig.apiUrl + "/api/green-leaves/weigh/findby-supplier/" + fromDate + "/" + toDate + "/" + branch + "/" + supplier;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {

                            });
                };
                //find by collector
                factory.findbyFromDateToDateAndBranchAndCollector = function (fromDate, toDate, branch, collector, callback) {
                    var url = systemConfig.apiUrl + "/api/green-leaves/weigh/findby-collector/" + fromDate + "/" + toDate + "/" + branch + "/" + collector;
                    $http.get(url)
                            .success(function (data, status, headers) {
                                callback(data);
                            })
                            .error(function (data, status, headers) {

                            });
                };


                return factory;
            });

    angular.module("greenLeavesWeighModule")
            .controller("greenLeavesWeighController", function ($scope, weighFactory, optionPane, $filter) {
                //models
                $scope.ui = {};
                $scope.http = {};
                $scope.model = {};
                $scope.chart = {};

                //chart
                $scope.chart.labels = [];
                $scope.chart.data = [];
                $scope.chart.options = {
                    responsive: true,
                    maintainAspectRatio: false
                };
                $scope.chart.series = ['Net Qty', 'Gross Qty'];



                $scope.ui.searchByBranchWise = function () {
                    $scope.chart.labels = [];
                    $scope.chart.data = [];
                    $scope.ui.chartmode = 'bar';
                    $scope.ui.totalGross = null;
                    $scope.ui.totalNet = null;
                    $scope.ui.totalDeduction = null;

                    if (!$scope.model.branch) {
                        $scope.model.branch = null;
                    }
                    if (!$scope.model.route) {
                        $scope.model.route = null;
                    }
                    if (!$scope.model.supplier) {
                        $scope.model.supplier = null;
                    }
                    if (!$scope.model.collector) {
                        $scope.model.collector = null;
                    }

                    var fromdate = $filter('date')($scope.model.fromDate, 'yyyy-MM-dd');
                    var todate = $filter('date')($scope.model.toDate, 'yyyy-MM-dd');

                    //date
                    if ($scope.model.fromDate !== null && $scope.model.toDate !== null && $scope.model.branch === null) {
                        $scope.ui.mode = 'branchwise';
                        console.log("1")
                        weighFactory.findbyFromDateToDate(fromdate, todate,
                                function (data) {
                                    $scope.model.weighDetailsList = data;
                                    $scope.totalQty();
                                    var tempData = [];
                                    angular.forEach(data, function (val) {
                                        $scope.chart.labels.push(val[0]);
                                        tempData.push(val[3]);
                                    });
                                    tempData.push(0);
                                    $scope.chart.data.push(tempData);
                                    var tempData2 = [];
                                    angular.forEach(data, function (val) {
                                        tempData2.push(val[1]);
                                    });
                                    tempData2.push(0);
                                    $scope.chart.data.push(tempData2);
                                });
                    }
                    //branch
                    if ($scope.model.fromDate !== null && $scope.model.toDate !== null && $scope.model.branch !== null && $scope.model.route === null && $scope.model.collector === null && $scope.model.supplier === null) {
                        $scope.ui.mode = 'routewise';
                        console.log("2")
                        weighFactory.findbyFromDateToDateAndBranch(fromdate, todate, $scope.model.branch,
                                function (data) {
                                    $scope.model.weighDetailsList = data;
                                    $scope.totalQty();
                                    var tempData = [];
                                    angular.forEach(data, function (val) {
                                        $scope.chart.labels.push(val[0]);
                                        tempData.push(val[3]);
                                    });
                                    tempData.push(0);
                                    $scope.chart.data.push(tempData);
                                    var tempData2 = [];
                                    angular.forEach(data, function (val) {
                                        tempData2.push(val[1]);
                                    });
                                    tempData2.push(0);
                                    $scope.chart.data.push(tempData2);
                                });
                    }
                    //route
                    if ($scope.model.fromDate !== null && $scope.model.toDate !== null && $scope.model.branch !== null && $scope.model.route !== null && $scope.model.collector === null && $scope.model.supplier === null) {
                        console.log("3")
                        $scope.ui.mode = 'datewise';
                        weighFactory.findbyFromDateToDateAndBranchAndRoute(fromdate, todate, $scope.model.branch, $scope.model.route,
                                function (data) {
                                    $scope.model.weighDetailsList = data;
                                    $scope.totalQty();
                                    var tempData = [];
                                    var tempData2 = [];
                                    weighFactory.findAllDates(fromdate, todate, function (dates) {
                                        angular.forEach(dates, function (val) {
                                            $scope.chart.labels.push($filter('date')(val, 'yyyy-MM-dd'));
                                        });
                                        angular.forEach(data, function (value) {
                                            angular.forEach($scope.chart.labels, function (val, index) {
                                                if (!tempData[index]) {
                                                    tempData[index] = 0;
                                                }
                                                if (val === $filter('date')(value[0], 'yyyy-MM-dd')) {
                                                    tempData[index] = value[3];
                                                }
                                            });

                                            angular.forEach($scope.chart.labels, function (val, index) {
                                                if (!tempData2[index]) {
                                                    tempData2[index] = 0;
                                                }
                                                if (val === $filter('date')(value[0], 'yyyy-MM-dd')) {
                                                    tempData2[index] = value[1];
                                                }
                                            });
                                        });
                                        $scope.chart.data.push(tempData);
                                        $scope.chart.data.push(tempData2);
                                    });


//                                  
                                });
                    }
                    //supplier
                    if ($scope.model.fromDate !== null && $scope.model.toDate !== null && $scope.model.branch !== null && $scope.model.supplier !== null && $scope.model.collector === null) {
                        $scope.ui.mode = 'datewise';
                        console.log("4")
                        weighFactory.findbyFromDateToDateAndBranchAndSupplier(fromdate, todate, $scope.model.branch, $scope.model.supplier,
                                function (data) {
                                    $scope.model.weighDetailsList = data;
                                    $scope.totalQty();
                                    var tempData = [];
                                    var tempData2 = [];
                                    weighFactory.findAllDates(fromdate, todate, function (dates) {
                                        angular.forEach(dates, function (val) {
                                            $scope.chart.labels.push($filter('date')(val, 'yyyy-MM-dd'));
                                        });
                                        angular.forEach(data, function (value) {
                                            angular.forEach($scope.chart.labels, function (val, index) {
                                                if (!tempData[index]) {
                                                    tempData[index] = 0;
                                                }
                                                if (val === $filter('date')(value[0], 'yyyy-MM-dd')) {
                                                    tempData[index] = value[3];
                                                }
                                            });

                                            angular.forEach($scope.chart.labels, function (val, index) {
                                                if (!tempData2[index]) {
                                                    tempData2[index] = 0;
                                                }
                                                if (val === $filter('date')(value[0], 'yyyy-MM-dd')) {
                                                    tempData2[index] = value[1];
                                                }
                                            });
                                        });
                                        $scope.chart.data.push(tempData);
                                        $scope.chart.data.push(tempData2);
                                    });
                                });
                    }
                    //collector
                    if ($scope.model.fromDate !== null && $scope.model.toDate !== null && $scope.model.branch !== null && $scope.model.collector !== null) {
                        $scope.ui.mode = 'datewise';
                        weighFactory.findbyFromDateToDateAndBranchAndCollector(fromdate, todate, $scope.model.branch, $scope.model.collector,
                                function (data) {
                                    $scope.model.weighDetailsList = data;
                                    $scope.totalQty();
                                    var tempData = [];
                                    var tempData2 = [];
                                    weighFactory.findAllDates(fromdate, todate, function (dates) {
                                        angular.forEach(dates, function (val) {
                                            $scope.chart.labels.push($filter('date')(val, 'yyyy-MM-dd'));
                                        });
                                        angular.forEach(data, function (value) {
                                            angular.forEach($scope.chart.labels, function (val, index) {
                                                if (!tempData[index]) {
                                                    tempData[index] = 0;
                                                }
                                                if (val === $filter('date')(value[0], 'yyyy-MM-dd')) {
                                                    tempData[index] = value[3];
                                                }
                                            });

                                            angular.forEach($scope.chart.labels, function (val, index) {
                                                if (!tempData2[index]) {
                                                    tempData2[index] = 0;
                                                }
                                                if (val === $filter('date')(value[0], 'yyyy-MM-dd')) {
                                                    tempData2[index] = value[1];
                                                }
                                            });
                                        });
                                        $scope.chart.data.push(tempData);
                                        $scope.chart.data.push(tempData2);
                                    });
                                });
                    }
                };

                $scope.totalQty = function () {
                    angular.forEach($scope.model.weighDetailsList, function (val) {
                        if (!$scope.ui.totalGross || $scope.ui.totalGross === null) {
                            $scope.ui.totalGross = val[1];
                            $scope.ui.totalDeduction = val[2];
                            $scope.ui.totalNet = val[3];
                        } else {
                            $scope.ui.totalGross += val[1];
                            $scope.ui.totalDeduction += val[2];
                            $scope.ui.totalNet += val[3];
                        }
                    });
                };

                $scope.ui.onSelectBranch = function ($item, $model, $label) {
                    $scope.model.supplierList = [];
                    $scope.model.routeList = [];
                    $scope.model.collectorList = [];
                    angular.forEach($scope.model.routeListTemp, function (data) {
                        if ($model === data.branch) {
                            $scope.model.routeList.push(data);
                        }
                    });
                    angular.forEach($scope.model.collectorListTemp, function (data) {
                        if ($model === data.branch) {
                            $scope.model.collectorList.push(data);
                        }
                    });
                    angular.forEach($scope.model.supplierListTemp, function (data) {
                        if ($model === data.branch) {
                            $scope.model.supplierList.push(data);
                        }
                    });

                };

                $scope.ui.changeCollector = function () {
                    $scope.model.route = null;
                    $scope.model.supplier = null;
                };
                $scope.ui.changeSupplier = function () {
                    $scope.model.route = null;
                    $scope.model.collector = null;
                };
                $scope.ui.changeRoute = function () {
                    $scope.model.supplier = null;
                    $scope.model.collector = null;
                };
                $scope.ui.selectLineChart = function (){
                    $scope.ui.chartmode = 'line';
                };
                $scope.ui.selectBarChart = function (){
                    $scope.ui.chartmode = 'bar';
                };
                $scope.ui.selectPieChart = function (){
                    $scope.ui.chartmode = 'pie';
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
                $scope.ui.routeLabel = function ($model) {
                    var displayText;
                    angular.forEach($scope.model.routeList, function (data) {
                        if (data.indexNo === $model) {
                            displayText = data.rname;
                            return;
                        }
                    });
                    return displayText;
                };
                $scope.ui.supplierLabel = function ($model) {
                    var displayText;
                    angular.forEach($scope.model.supplierList, function (data) {
                        if (data.indexNo === $model) {
                            displayText = data.supname;
                            return;
                        }
                    });
                    return displayText;
                };
                $scope.ui.collectorLabel = function ($model) {
                    var displayText;
                    angular.forEach($scope.model.collectorList, function (data) {
                        if (data.indexNo === $model) {
                            displayText = data.name;
                            return;
                        }
                    });
                    return displayText;
                };


                $scope.ui.init = function () {
                    weighFactory.findAllBranch(
                            function (data) {
                                $scope.model.branchList = data;
                            });
                    weighFactory.findAllSuppliers(
                            function (data) {
//                                $scope.model.supplierList = data;
                                $scope.model.supplierListTemp = data;
                            });
                    weighFactory.findAllRoutes(
                            function (data) {
                                $scope.model.routeListTemp = data;
                            });
                    weighFactory.findAllCollectors(
                            function (data) {
//                                $scope.model.collectorList = data;
                                $scope.model.collectorListTemp = data;
                            });
                };

                $scope.ui.init();


            });
}());


