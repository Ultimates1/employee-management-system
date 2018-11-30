var angular = require('angular');

// -------------------------
// Forgot controller
// -------------------------
angular
	.module('ems')
	.controller('ForgotController', [
		'$rootScope',
		'$scope',
		'$http',
		function ($rootScope, $scope, $http) {
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

			$scope.displayMsg = function (display, message, type) {
				let element = angular.element(document.getElementById('userError'));
				element.text(message ? message : "Sorry, we can't find this email in our records.");
				element.css('color', type === 'error' ? 'red' : '#67AB9F');
				element.css('visibility', display ? 'visible' : 'hidden');
			};

			$scope.forgotPassword = function () {
				if (!$scope.user) {
					return;
				}
				$http.get('ems/access/resetpasswordrequest?user=' + $scope.user)
					.then(function success(response) {
						if (response.data.success) {
							$scope.displayMsg(true, response.data.message);
						} else {
							$scope.displayMsg(true, response.data.message, 'error');
						}
					}, function error(response) {
						$scope.displayMsg(true, response.data.message, 'error');
					});
			};

			$scope.back = function () {
				$rootScope.goTo('login');
			};
		}
	]);
