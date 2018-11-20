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

			$scope.login = function () {
				$http.post(
					'/ems/access/login',
					{
						user: $scope.user,
						password: $scope.pass
					})
					.then(function success(response) {
						Access.setLoginStatus(response.data.success);
						Access.setAccessContent(response.data.message);
						$rootScope.goTo('home');
					}, function error(response) {
						Access.setLoginStatus(response.data.success);
					});
			};
		}
	]);
