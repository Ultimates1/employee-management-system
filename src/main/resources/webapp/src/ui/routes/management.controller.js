var angular = require('angular');

require('../data/access.service.js');

angular
	.module('ems')
	.controller('ManagementController', [
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
				if (!$rootScope.hasFunction('PROJECT_MANAGEMENT')) {
					$rootScope.goTo('home');
				}
				return $scope.htmlReady;
			};

			// Fired when html ready
			$scope.htmlLoaded = function () {
				$scope.htmlReady = true;
			};

			$scope.getEmployeeList = function (userID) {
				return {
					'eA': 'employee A',
					'eB': 'employee B',
					'eC': 'employee C',
					'eD': 'employee D',
					'eE': 'employee E',
					'eF': 'employee F',
					'eG': 'employee G',
					'eH': 'employee H',
					'eI': 'employee I',
					'eJ': 'employee J',
					'eK': 'employee K',
					'eL': 'employee L',
					'eM': 'employee M',
					'eN': 'employee N',
					'eO': 'employee O',
					'eP': 'employee P'
				};
			}

			$scope.getProjectList = function (userID) {
				return [
					{
						id: 'p1',
						name: 'Project 1',
						members: ['eA', 'eB', 'eC']
					},
					{
						id: 'p2',
						name: 'Project 2',
						members: ['eD', 'eE', 'eH']
					},
					{
						id: 'p3',
						name: 'Project 3',
						members: ['eF', 'eG', 'eI', 'eJ', 'eK', 'eL', 'eM', 'eN', 'eO', 'eP']
					}
				]
			};

			$scope.addProjectEmployee = function () {
				$scope.selectedProject.members.push($scope.selectedEmployee);
			}

			$scope.removeProjectEmployee = function (index) {
				$scope.selectedProject.members.splice(index, 1);
			}

			$scope.projectList = $scope.getProjectList($scope.userID);
			$scope.employeeList = $scope.getEmployeeList($scope.userID);
		}
	]);
