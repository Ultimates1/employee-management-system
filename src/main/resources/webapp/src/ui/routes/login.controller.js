var angular = require('angular');

require('../data/access.service.js');

angular
	.module('ems')
	.controller('LoginController', [
		'$rootScope',
		'$scope',
		'$http',
		'Access',
		function ($rootScope, $scope, $http, Access) {
			$scope.htmlReady = false;
			$scope.user = '';
			$scope.pass = '';

			$scope.ready = function () {
				return $scope.htmlReady;
			};

			// Fired when html ready
			$scope.htmlLoaded = function () {
				$scope.htmlReady = true;
			};

			$scope.displayError = function (display) {
				angular.element(document.getElementById('loginError')).css('visibility', display ? 'visible' : 'hidden');
			};

			$scope.login = function () {
				$http.post(
					'/ems/access/login',
					{
						user: $scope.user,
						password: $scope.pass
					})
					.then(function success(response) {
						Access.setLoginStatus(response.data.success);
						// Manager
						Access.setAccessContent({
							name: 'Mi Nguyen',
							fns: ['profile', 'timekeeping', 'leaverequest', 'documents', 'management', 'budget', 'evaluation']
						});
						// // Employee
						// Access.setAccessContent({
						//  name: 'Mi Nguyen',
						// 	fns: ['profile', 'timekeeping', 'leaverequest', 'documents']
						// });
						// Access.setAccessContent(response.data.message || {});
						if (response.data.success) {
							$scope.displayError(false);
							$rootScope.goTo('home');
						}
						$scope.displayError(true);
					}, function error(response) {
						Access.setLoginStatus(response.data.success);
						Access.setAccessContent({});
						$scope.displayError(true);
					});
			};
		}
	]);
