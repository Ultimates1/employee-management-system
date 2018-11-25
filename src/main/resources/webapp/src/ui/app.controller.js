var angular = require('angular');

angular
	.module('ems')
	.controller('ApplicationController', [
		'$rootScope',
		'$scope',
		'$window',
		'$location',
		function ($rootScope, $scope, $window, $location) {
			$scope.htmlReady = false;

			// On resize
			angular.element($window).bind('resize', function () {
				var width = parseInt($window.innerWidth / 6);
				// Ensure we can divide by 2
				if (width % 2 !== 0) {
					width += 1;
				}
			});

			// Fired when html ready
			$scope.htmlLoaded = function () {
				$scope.htmlReady = true;
			};

			$scope.ready = function () {
				return $scope.htmlReady;
			};

			$rootScope.goTo = function (page) {
				switch (page) {
					case 'home':
						$location.path('/');
						break;
					case 'login':
						$location.path('/login');
						break;
					case 'forgot':
						$location.path('/forgot');
						break;
					case 'profile':
						$location.path('/profile');
						break;
					case 'timekeeping':
						$location.path('/timekeeping');
						break;
					case 'leaverequest':
						$location.path('/leaverequest');
						break;
					case 'documents':
						$location.path('/documents');
						break;
					case 'management':
						$location.path('/management');
						break;
					case 'budget':
						$location.path('/budget');
						break;
					case 'evaluation':
						$location.path('/evaluation');
						break;
					default:
						$location.path('/');
				}
			};
		}
	]);
