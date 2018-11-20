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
		'$mdDialog',
		function ($rootScope, $scope, $http, $mdDialog) {
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
						$scope.showAlert();
						angular.element('#userError').css('visibility', 'hidden');
					}, function error(err) {
						$scope.response = err;
						angular.element('#userError').css('visibility', 'visible');
					});
			};

			$scope.showAlert = function () {
				// Appending dialog to document.body to cover sidenav in docs app
				// Modal dialogs should fully cover application
				// to prevent interaction outside of dialog
				$mdDialog
					.show($mdDialog
						.alert()
						.parent(angular.element(document.querySelector('#forgot')))
						.clickOutsideToClose(false)
						.title('Request Submitted')
						.textContent("An email has been sent to you.\nPlease allow up to 10 minutes for it to arrive.\nPlease check your Spam mailbox if you still don't see it.")
						.ok('Back to Login page.')
					)
					.then($scope.back);
			};

			$scope.back = function () {
				$rootScope.goTo('login');
			};
		}
	]);
