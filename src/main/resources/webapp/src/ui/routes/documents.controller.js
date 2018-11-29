var angular = require('angular');

require('../data/access.service.js');

angular
	.module('ems')
	.controller('DocumentsController', [
		'$rootScope',
		'$scope',
		'$http',
		'Access',
		function ($rootScope, $scope, $http, Access) {
			$scope.htmlReady = false;
			$scope.userID = Access.getAccessContent().userID;

			$scope.ready = function () {
				if (!Access.getLoginStatus()) {
					$rootScope.goTo('login');
				}
				if (!$rootScope.hasFunction('documents')) {
					$rootScope.goTo('home');
				}
				return $scope.htmlReady;
			};

			// Fired when html ready
			$scope.htmlLoaded = function () {
				$scope.htmlReady = true;
			};

			$scope.getDocuments = function (userID) {
				return [
					['Doc 1', 'abc.com'],
					['Doc 2', 'xyz.com']
				];

				// $http.get('ems/documents/getDocuments/?user=' + $scope.userID)
				// 	.then(function success(response) {
				// 		if (response.data.success) {
				// 			return response.data.result;
				// 		} else {
				// 			console.error(response.data.message);
				// 			return {};
				// 		}
				// 	}, function error(err) {
				// 		console.error(err);
				// 		return {};
				// 	});
			};

			$scope.documents = getDocuments($scope.userID);
		}
	]);
