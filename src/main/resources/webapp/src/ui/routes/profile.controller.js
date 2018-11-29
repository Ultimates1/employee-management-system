var angular = require('angular');

require('../data/access.service.js');

angular
	.module('ems')
	.controller('ProfileController', [
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
				if (!$rootScope.hasFunction('profile')) {
					$rootScope.goTo('home');
				}
				return $scope.htmlReady;
			};

			// Fired when html ready
			$scope.htmlLoaded = function () {
				$scope.htmlReady = true;
			};

			$scope.getProfileInfo = function (userID) {
				return {
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
				};

				// $http.get('ems/profile/getProfile/?user=' + $scope.userID)
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

			$scope.getProfileImgPath = function () {
				return 'image' in $scope.profileInfo && $scope.profileInfo.image !== '' ? 'profileInfo.image' : 'assets/images/square_profile.jpg';
			};

			$scope.profileInfo = $scope.getProfileInfo($scope.userID);
		}
	]);
