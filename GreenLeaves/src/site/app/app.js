(function () {
    //index module
    angular.module("appModule", [
        "ngRoute",
        "ngCookies",
        "ui.bootstrap",
        "greenLeavesWeighModule",
        "firewoodsModule"

    ]);

    //constants
    angular.module("appModule")
            .constant("systemConfig", {
                apiUrl:
                        location.hostname === 'localhost'
                        ? "http://localhost:8098"
                        : location.protocol + "//" + location.hostname + (location.port ? ":" + location.port : "")
            });

    //route config
    angular.module("appModule")
            .config(function ($routeProvider) {
                $routeProvider
                        //system
                        .when("/", {
                            redirectTo: "/home"
                        })
                        .when("/login", {
                            templateUrl: "app/system/login/login.html",
                            controller: "LoginController"
                        })
                        //home
                        .when("/home", {
                            templateUrl: "app/dashboard/dash-board.html"
                        })

                        //reports
                        .when("/reports/weigh", {
                            templateUrl: "app/reports/weigh/weigh.html",
                            controller: "greenLeavesWeighController"
                        })
                        .when("/reports/firewoods", {
                            templateUrl: "app/reports/firewoods/firewoods.html",
                            controller: "firewoodsController"
                        })


                        //otherwise
                        .otherwise({
                            redirectTo: "/"
                        });
            });

}());
