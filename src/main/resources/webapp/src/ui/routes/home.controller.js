var angular = require('angular');


//-------------------------
// Home controller
//-------------------------
angular
	.module('ems')
	.controller('HomeController', [
		'$scope',
		'$http',
		function ($scope, $http) {
			$scope.htmlReady = false;
			$scope.user, $scope.oldPass, $scope.newPass, $scope.response;

			$scope.ready = function () {
				return $scope.htmlReady;
			};

			// Fired when html ready
			$scope.htmlLoaded = function () {
				$scope.htmlReady = true;
			};

			$scope.updatePassword = function () {
				$http.get("ems/access/updatepassword/" + $scope.user + "?oldpass=" + $scope.oldPass + "&newpass=" + $scope.newPass)
					.then(function success(response) {
						$scope.response = response.data;
					}, function error(err) {
						$scope.response = err;
					});
			}
		}
	]);
