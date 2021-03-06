var angular = require('angular');

require('../data/access.service.js');
require('../data/management.service.js');

angular
	.module('ems')
	.controller('ManagementController', [
		'$rootScope',
		'$scope',
		'Access',
		'Management',
		async function ($rootScope, $scope, Access, Management) {
			$scope.htmlReady = false;
			$scope.userID = Access.getAccessContent().userID;
			$scope.members = [];
			$scope.newProject = {};

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

			$scope.displayMsg = function (display, message, type) {
				let element = angular.element(document.getElementById('managementError'));
				element.text(message || 'Unable to complete your request.');
				element.css('color', type === 'error' ? 'red' : '#67AB9F');
				element.css('visibility', display ? 'visible' : 'hidden');
			};

			$scope.updateList = async function () {
				$scope.projectList = await Management.getProjectList($scope.userID, $scope.displayMsg);
				$scope.employeeList = await Management.getEmployeeList($scope.displayMsg);
				$scope.$apply();

				if ($scope.projectList.length === 0) {
					$scope.displayMsg(true, 'You have no current project.', 'error');
				}
			};

			$scope.getProjectEmployees = async function () {
				$scope.members = await Management.getProjectEmployees($scope.userID, $scope.selectedProject.projectId, $scope.displayMsg);
				$scope.$apply();
			};

			$scope.employeeNotInList = function () {
				for (let i = 0; i < $scope.members.length; i++) {
					if ($scope.selectedEmployee.userId === $scope.members[i].userId) {
						return false;
					}
				}
				return true;
			};

			$scope.addProjectEmployee = function () {
				Management
					.addProjectEmployee($scope.selectedProject.projectId, $scope.selectedEmployee.userId, $scope.displayMsg)
					.then(() => {
						$scope.getProjectEmployees();
					});
			};

			$scope.removeProjectEmployee = function (index) {
				Management
					.removeProjectEmployee($scope.selectedProject.projectId, $scope.members[index].userId, $scope.displayMsg)
					.then(() => {
						$scope.getProjectEmployees();
					});
			};

			$scope.addProject = function () {
				if (!(
					$scope.newProject.name && $scope.newProject.manager &&
					$scope.newProject.hr && $scope.newProject.description
				)) {
					$scope.displayMsg(true, 'Missing project information', 'error');
					return;
				}

				Management
					.addProject($scope.newProject, $scope.displayMsg)
					.then(async () => {
						$scope.projectList = await Management.getProjectList($scope.userID, $scope.displayMsg);
						$scope.$apply();
					});
			};

			$scope.updateList();
		}
	]);
