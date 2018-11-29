var angular = require('angular');

require('../data/access.service.js');

angular
	.module('ems')
	.controller('LeaveRequestController', [
		'$rootScope',
		'$scope',
		'Access',
		function ($rootScope, $scope, Access) {
			$scope.htmlReady = false;
			$scope.accessContent = Access.getAccessContent();

			$scope.ready = function () {
				if (!Access.getLoginStatus()) {
					$rootScope.goTo('login');
				}
				if (!$rootScope.hasFunction('LEAVE_REQUEST')) {
					$rootScope.goTo('home');
				}
				return $scope.htmlReady;
			};

			// Fired when html ready
			$scope.htmlLoaded = function () {
				$scope.htmlReady = true;
			};
		}
	]);
