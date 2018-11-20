var angular = require('angular');

// -------------------------
// Forgot controller
// -------------------------
angular
	.module('ems')
	.controller('ResetController', [
		'$rootScope',
		'$scope',
		'$routeParams',
		'$http',
		function ($rootScope, $scope, $routeParams, $http) {
			$scope.htmlReady = false;
			$scope.user = $routeParams.user;
			$scope.newPass = '';
			$scope.confirmPass = '';

			$scope.ready = function () {
				return $scope.htmlReady;
			};

			// Fired when html ready
			$scope.htmlLoaded = function () {
				$scope.htmlReady = true;
			};

			$scope.validatePassword = function () {
				if (!$scope.newPass && !$scope.confirmPass) {
					return;
				}

				if ($scope.newPass !== $scope.confirmPass) {
					angular.element('#resetError').css('visibility', 'visible');
				} else {
					angular.element('#resetError').css('visibility', 'hidden');
				}

				// Optional: Strong password validation
				// - >= 8 chars
				// - 1 Upper and 1 lower
				// - 1 Number
				// - 1 Symbol
			};

			$scope.resetPassword = function () {
				if (!$scope.validatePassword()) {
					return;
				}

				$http.get('ems/access/resetpassword/?user=' + $scope.user + '&newpass=' + $scope.newPass)
					.then(function success(response) {
						$scope.response = response.data;
						$rootScope.goTo('login');
					}, function error(err) {
						$scope.response = err;
					});
			};
		}
	]);
