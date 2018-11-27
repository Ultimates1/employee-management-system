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
						Access.setAccessContent({
							name: 'Mi Nguyen',
							info: {
								name: 'Mi Nguyen',
								title: 'Software Engineer',
								group: 'Project group',
								image: '',
								phoneNumbers: [
									['Office', '123-456-7890'],
									['Cell', '098-765-4321']
								],
								contact: [
									'123 Some St.',
									'Some State, CA 12345',
									'abc@xyz.com'
								],
								skills: ['Skill 1', 'Skill 2', 'Skill 3', 'Skill 4'],

								experiences: [
									{
										title: 'ABC',
										location: 'XYZ company',
										from: '1/1/2016',
										to: '1/1/2017',
										description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.'
									},
									{
										title: 'DEF',
										location: 'TUV company',
										from: '1/1/2017',
										to: 'Present',
										description: 'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'
									}
								],
								achievements: [
									{
										title: 'ABC',
										description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.'
									},
									{
										title: 'Degree DEF',
										description: 'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'
									}
								],
								educations: [
									{
										title: 'ABC',
										from: '1/1/2016',
										to: '1/1/2017',
										description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.'
									}
								]
							},
							// Manager
							fns: ['profile', 'timekeeping', 'leaverequest', 'documents', 'management', 'budget', 'evaluation']
							// // Employee
							// fns: ['profile', 'timekeeping', 'leaverequest', 'documents']
						});

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
