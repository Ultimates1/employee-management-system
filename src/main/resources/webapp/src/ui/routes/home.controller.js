var angular = require('angular');

require('../data/access.service.js');

angular
	.module('ems')
	.controller('HomeController', [
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
				return $scope.htmlReady;
			};

			// Fired when html ready
			$scope.htmlLoaded = function () {
				$scope.htmlReady = true;
			};

			$scope.hasFunction = function (fn) {
				return $scope.accessContent.fns.indexOf(fn) !== -1;
			}
		}
	]);
