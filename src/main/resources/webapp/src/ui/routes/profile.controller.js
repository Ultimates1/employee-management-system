var angular = require('angular');

require('../data/access.service.js');

angular
	.module('ems')
	.controller('ProfileController', [
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

			$scope.getProfileImgPath = function () {
				return 'image' in $scope.accessContent.info && $scope.accessContent.info.image !== '' ? 'accessContent.info.image' : 'assets/images/square_profile.jpg';
			}
		}
	]);
