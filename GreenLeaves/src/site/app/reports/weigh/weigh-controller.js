(function () {
//    angular.module("greenLeavesWeighModule", ['chart.js', 'ngAnimate', 'ngSanitize', 'ui.bootstrap']);
    angular.module("greenLeavesWeighModule", ['ui.bootstrap']);

    angular.module("greenLeavesWeighModule")
            .factory("weighFactory", function ($http, systemConfig) {
                var factory = {};

                //load weigh detail
                factory.weighDetail = function (callback) {
                    var url = systemConfig.apiUrl + "/api/green-leaves/";
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
            .controller("greenLeavesWeighController", function ($scope, weighFactory, optionPane) {
                //ui models
                $scope.ui = {};


                $scope.ui.init = function (){
                    console.log("sssssssssssss")
                };
                
                $scope.ui.init();


            });
}());


