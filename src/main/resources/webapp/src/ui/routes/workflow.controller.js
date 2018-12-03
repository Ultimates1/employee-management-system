var angular = require('angular');

require('../data/access.service.js');
require('../data/workflow.service.js');

angular
	.module('ems')
	.controller('WorkflowController', [
		'$rootScope',
		'$scope',
		'Access',
		'Workflow',
		async function ($rootScope, $scope, Access, Workflow) {
			$scope.htmlReady = false;
			$scope.userID = Access.getAccessContent().userID;

			$scope.ready = function () {
				if (!Access.getLoginStatus()) {
					$rootScope.goTo('login');
				}
				if (!$rootScope.hasFunction('WORKFLOW')) {
					$rootScope.goTo('home');
				}
				return $scope.htmlReady;
			};

			// Fired when html ready
			$scope.htmlLoaded = function () {
				$scope.htmlReady = true;
			};

			$scope.displayMsg = function (display, message, type) {
				let element = angular.element(document.getElementById('workflowError'));
				element.text(message ? message : 'Unable to complete your request.');
				element.css('color', type === 'error' ? 'red' : '#67AB9F');
				element.css('visibility', display ? 'visible' : 'hidden');
			};

			$scope.convertDate = function (date) {
				date = new Date(date);

				if (date.toString() === 'Invalid Date') {
					return '';
				}

				var yyyy = date.getFullYear().toString();
				var mm = (date.getMonth() + 1).toString();
				var dd = date.getDate().toString();

				var mmChars = mm.split('');
				var ddChars = dd.split('');

				return  yyyy + '-' + (mmChars[1] ? mm : '0' + mmChars[0]) + '-' + (ddChars[1] ? dd : '0' + ddChars[0]);
			};

			$scope.updateList = async function () {
				$scope.workflows = await Workflow.getWorkflows($scope.userID, $scope.displayMsg);
				$scope.$apply();

				if ($scope.workflows.length === 0) {
					$scope.displayMsg(true, 'You have no workflow.', 'error');
				}
			};

			$scope.validateWorkflow = function (workflow) {
				if (!workflow.comment) {
					$scope.displayMsg(true, 'Comment is required.', 'error');
					return false;
				}
				return true;
			}

			$scope.updateWorkflow = async function (index, action) {
				if (!$scope.validateWorkflow($scope.workflows[index])) {
					return;
				}

				await Workflow.updateWorkflow(
					$scope.userID, $scope.workflows[index].workflowId,
					action, $scope.workflows[index].comment, $scope.displayMsg
				).then(() => {
					$scope.updateList();
				});
			}

			$scope.updateList();
		}
	]);
