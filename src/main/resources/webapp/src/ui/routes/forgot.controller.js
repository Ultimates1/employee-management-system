var angular = require('angular');

// -------------------------
// Forgot controller
// -------------------------
angular
	.module('ems')
	.controller('ForgotController', [
		'$scope',
		'$location',
		'$http',
		function ($scope, $location, $http) {
			$scope.htmlReady = false;
			$scope.newPass = '';
			$scope.oldPass = '';

			$scope.ready = function () {
				return $scope.htmlReady;
			};

			// Fired when html ready
			$scope.htmlLoaded = function () {
				$scope.htmlReady = true;
			};

			$scope.forgotID = function () {
				if (!$scope.email) {
					return;
				}
				$http.get('ems/access/forgotid/?email=' + $scope.email)
					.then(function success(response) {
						$scope.response = response.data;
						angular.element('#emailError').css('visibility', 'hidden');
					}, function error(err) {
						$scope.response = err;
						angular.element('#emailError').css('visibility', 'visible');
					});
			};

			$scope.forgotPassword = function () {
				if (!$scope.user) {
					return;
				}
				$http.get('ems/access/forgotpassword/?user=' + $scope.user)
					.then(function success(response) {
						$scope.response = response.data;
						angular.element('#userError').css('visibility', 'hidden');
					}, function error(err) {
						$scope.response = err;
						angular.element('#userError').css('visibility', 'visible');
					});
			};

			$scope.back = function () {
				$location.path('/login');
			};
		}
	]);
