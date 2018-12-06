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
			$scope.userID = Access.getAccessContent().userID;

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

			$scope.getProfileName = function (userID) {
				return 'Mi Nguyen';

				// $http.get('ems/profile/getProfile/?user=' + $scope.userID)
				// 	.then(function success(response) {
				// 		if (response.data.success) {
				// 			return response.data.result.name;
				// 		} else {
				// 			console.error(response.data.message);
				// 			return 'Unknown';
				// 		}
				// 	}, function error(err) {
				// 		console.error(err);
				// 		return 'Unknown';
				// 	});
			};

			$scope.profileInfoName = $scope.getProfileName($scope.userID);
		}
	]);
