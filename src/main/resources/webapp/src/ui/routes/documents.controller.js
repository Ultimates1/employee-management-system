var angular = require('angular');

require('../data/access.service.js');

angular
	.module('ems')
	.controller('DocumentsController', [
		'$rootScope',
		'$scope',
		'$http',
		'$window',
		'Access',
		function ($rootScope, $scope, $http, $window, Access) {
			$scope.htmlReady = false;
			$scope.userID = Access.getAccessContent().userID;
			$scope.currentDocument = {};

			$scope.ready = function () {
				if (!Access.getLoginStatus()) {
					$rootScope.goTo('login');
				}
				if (!$rootScope.hasFunction('MY_DOCUMENTS')) {
					$rootScope.goTo('home');
				}
				return $scope.htmlReady;
			};

			// Fired when html ready
			$scope.htmlLoaded = function () {
				$scope.htmlReady = true;
			};

			$scope.displayMsg = function (display, message, type) {
				let element = angular.element(document.getElementById('documentsError'));
				element.text(message ? message : 'All fields are required.');
				element.css('color', type === 'error' ? 'red' : '#67AB9F');
				element.css('visibility', display ? 'visible' : 'hidden');
			};

			$scope.getDocuments = function (userID) {
				return [
					{
						template: {
							name: 'Template 1',
							url: 'url_to_template_1.pdf'
						},
						fields: [
							{
								id: 'name',
								text: 'Full Name'
							},
							{
								id: 'position',
								text: 'Position'
							}
						]
					},
					{
						template: {
							name: 'Template 2',
							url: 'url_to_template_2.pdf'
						},
						fields: [
							{
								id: 'name',
								text: 'Full Name'
							},
							{
								id: 'offer',
								text: 'Offer'
							}
						]
					}
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

			$scope.submit = function () {
				if (!$scope.selectedDocument) {
					return;
				}
				if ($scope.selectedDocument.fields.length !== Object.keys($scope.currentDocument).length) {
					$scope.displayMsg(true, '', 'error');
				} else {
					console.log(
						'Hello', $scope.currentDocument.name + ',\nWe would like to offer you the position of',
						$scope.currentDocument.position
					);
					$scope.displayMsg(false);
					// $window.open('generated_pdf_page', '_blank');
				}
			};

			$scope.documents = $scope.getDocuments($scope.userID);
		}
	]);
