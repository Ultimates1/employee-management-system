var angular = require('angular');

require('../data/access.service.js');

angular
	.module('ems')
	.controller('WorkflowController', [
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
				if (!$rootScope.hasFunction('LEAVE_REQUEST')) {
					$rootScope.goTo('home');
				}
				return $scope.htmlReady;
			};

			// Fired when html ready
			$scope.htmlLoaded = function () {
				$scope.htmlReady = true;
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

				return (mmChars[1] ? mm : '0' + mmChars[0]) + '-' + (ddChars[1] ? dd : '0' + ddChars[0]) + '-' + yyyy;
			};

			$scope.getWorkflows = function (userID) {
				return [
					{
						'leaveRequestId': 1,
						'createDate': '2018-11-29T15:55:48.345+0000',
						'updateDate': null,
						'userId': 1,
						'leaveType': 'PERSONAL_LEAVE',
						'leaveFrom': '2019-11-26T08:00:00.000+0000',
						'leaveTo': '2019-11-28T08:00:00.000+0000',
						'comment': 'My leaves, none of your business',
						'status': 'P',
						'employeeID': 'eA',
						'employeeName': 'Employee A'
					},
					{
						'leaveRequestId': 2,
						'createDate': '2018-11-29T23:19:14.493+0000',
						'updateDate': null,
						'userId': 1,
						'leaveType': 'PERSONAL_LEAVE',
						'leaveFrom': '2018-11-26T08:00:00.000+0000',
						'leaveTo': '2018-11-28T08:00:00.000+0000',
						'comment': 'My leaves, none of your business',
						'status': 'P',
						'employeeID': 'eC',
						'employeeName': 'Employee C'
					},
					{
						'leaveRequestId': 3,
						'createDate': '2018-11-29T23:19:32.154+0000',
						'updateDate': null,
						'userId': 1,
						'leaveType': 'SICK_LEAVE',
						'leaveFrom': '2018-11-26T08:00:00.000+0000',
						'leaveTo': '2018-11-28T08:00:00.000+0000',
						'comment': 'My leaves, none of your business',
						'status': 'P',
						'employeeID': 'eC',
						'employeeName': 'Employee C'
					},
					{
						'leaveRequestId': 4,
						'createDate': '2018-11-29T23:20:17.350+0000',
						'updateDate': null,
						'userId': 1,
						'leaveType': 'SICK_LEAVE',
						'leaveFrom': '2018-11-26T08:00:00.000+0000',
						'leaveTo': '2018-11-28T08:00:00.000+0000',
						'comment': 'My leaves, none of your business',
						'status': 'P',
						'employeeID': 'eH',
						'employeeName': 'Employee H'
					}
				];

				// $http.get('leave/getrquesthistory/' + $scope.userID)
				// 	.then(function success(response) {
				// 		if (response.data.success) {
				// 			return response.data.leavebalance;
				// 		} else {
				// 			$scope.displayMsg(true, response.data.message, 'error');
				// 		}
				// 	}, function error(response) {
				// 		$scope.displayMsg(true, response.data.message, 'error');
				// 	});
			};

			$scope.acceptRequest = function (index) {
				console.log($scope.workflows[index].leaveRequestId);
				console.log($scope.workflows[index].addedComment);
				// Accept using leaveRequestId
				$scope.workflows.splice(index, 1);

				// $http.get('leave/getrquesthistory/' + $scope.userID)
				// 	.then(function success(response) {
				// 		if (response.data.success) {
				// 			return response.data.leavebalance;
				// 		} else {
				// 			$scope.displayMsg(true, response.data.message, 'error');
				// 		}
				// 	}, function error(response) {
				// 		$scope.displayMsg(true, response.data.message, 'error');
				// 	});
			};

			$scope.rejectRequest = function (index) {
				console.log($scope.workflows[index].leaveRequestId);
				console.log($scope.workflows[index].addedComment);
				// Reject using leaveRequestId
				$scope.workflows.splice(index, 1);

				// $http.get('leave/getrquesthistory/' + $scope.userID)
				// 	.then(function success(response) {
				// 		if (response.data.success) {
				// 			return response.data.leavebalance;
				// 		} else {
				// 			$scope.displayMsg(true, response.data.message, 'error');
				// 		}
				// 	}, function error(response) {
				// 		$scope.displayMsg(true, response.data.message, 'error');
				// 	});
			};

			$scope.addComment = function (index) {
				console.log($scope.workflows[index].leaveRequestId);
				console.log($scope.workflows[index].addedComment);

				// $http.get('leave/getrquesthistory/' + $scope.userID)
				// 	.then(function success(response) {
				// 		if (response.data.success) {
				// 			return response.data.leavebalance;
				// 		} else {
				// 			$scope.displayMsg(true, response.data.message, 'error');
				// 		}
				// 	}, function error(response) {
				// 		$scope.displayMsg(true, response.data.message, 'error');
				// 	});
			};

			$scope.workflows = $scope.getWorkflows($scope.userID);
		}
	]);
