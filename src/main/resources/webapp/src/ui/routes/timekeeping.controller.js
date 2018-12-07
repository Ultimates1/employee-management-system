var angular = require('angular');

require('../data/access.service.js');

angular
	.module('ems')
	.controller('TimekeepingController', [
		'$rootScope',
		'$scope',
		'Access',
		function ($rootScope, $scope, Access) {
			$scope.htmlReady = false;
			$scope.userID = Access.getAccessContent().userID;
			$scope.hours = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
			$scope.hrs = {
				mon: {},
				tue: {},
				wed: {},
				thu: {},
				fri: {},
				sat: {},
				sun: {}
			};

			$scope.ready = function () {
				if (!Access.getLoginStatus()) {
					$rootScope.goTo('login');
				}
				if (!$rootScope.hasFunction('TIME_SHEET')) {
					$rootScope.goTo('home');
				}
				return $scope.htmlReady;
			};

			// Fired when html ready
			$scope.htmlLoaded = function () {
				$scope.htmlReady = true;
			};

			$scope.save = function () {
				console.log('Saving entries...');
			};

			$scope.submit = function () {
				console.log('Submitting entries...');
			};
		}
	]);
