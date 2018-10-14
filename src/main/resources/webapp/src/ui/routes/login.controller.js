import $ from 'jquery';
var angular = require('angular');
//-------------------------
// Login controller
//-------------------------
angular
    .module('ems')
    .controller('LoginController', [
        '$scope',
        '$http',
        function ($scope, $http) {
            $scope.htmlReady = false;
            $scope.user, $scope.Pass;

            $scope.ready = function () {
                return $scope.htmlReady;
            };

            // Fired when html ready
            $scope.htmlLoaded = function () {
                $scope.htmlReady = true;
            };

            $scope.login = function () {
                //?user= :=signify to http that 
                $http.get("/ems/access/login/?user=" + $scope.user + "&password=" + $scope.Pass)
                    .then(function success(response) {
                        $scope.response = response.data;
                    }, function error(err) {
                        $scope.response = err;
                    });
            };
        }
    ]);
