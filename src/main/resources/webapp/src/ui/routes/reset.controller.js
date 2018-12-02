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
			$scope.sent = false;

			$scope.ready = function () {
				return $scope.htmlReady;
			};

			// Fired when html ready
			$scope.htmlLoaded = function () {
				$scope.htmlReady = true;
			};

			$scope.validatePassword = function () {
				if (!$scope.newPass && !$scope.confirmPass) {
					return false;
				}

				if ($scope.newPass !== $scope.confirmPass) {
					$scope.displayMsg(true, '', 'error');
					return false;
				} else {
					$scope.displayMsg(false);
					return true;
				}

				// Optional: Strong password validation
				// - >= 8 chars
				// - 1 Upper and 1 lower
				// - 1 Number
				// - 1 Symbol
			};

			$scope.displayMsg = function (display, message, type) {
				let element = angular.element(document.getElementById('resetError'));
				element.text(message ? message : "Password entries don't match.");
				element.css('color', type === 'error' ? 'red' : '#67AB9F');
				element.css('visibility', display ? 'visible' : 'hidden');
			};

			$scope.resetPassword = function () {
				console.log('here');
				console.log($scope.newPass, $scope.confirmPass);
				if (!$scope.validatePassword()) {
					return;
				}

				if ($scope.send) {
					return;
				}

				$http.get('ems/access/resetpassword/' + $scope.user + '?newpass=' + $scope.newPass)
					.then(function success(response) {
						if (response.data.success) {
							$scope.displayMsg(true, response.data.message);
							$scope.send = true;
							$rootScope.goTo('login');
						} else {
							$scope.send = false;
							$scope.displayMsg(true, response.data.message, 'error');
						}
					}, function error(response) {
						$scope.displayMsg(true, response.data.message, 'error');
					});
			};
		}
	]);
