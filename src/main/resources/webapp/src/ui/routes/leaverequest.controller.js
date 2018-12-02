var angular = require('angular');

require('../data/access.service.js');

angular
	.module('ems')
	.controller('LeaveRequestController', [
		'$rootScope',
		'$scope',
		'$http',
		'Access',
		function ($rootScope, $scope, $http, Access) {
			$scope.htmlReady = false;
			$scope.userID = Access.getAccessContent().userID;
			$scope.adding = false;
			$scope.leaveTypes = ['PERSONAL_LEAVE', 'SICK_LEAVE', 'VACATION_LEAVE'];
			$scope.datePickerOn = {
				from: false,
				to: false
			};
			$scope.format = 'MM-dd-yyyy';
			$scope.dateOptions = {
				dateDisabled: disableCalendarDays,
				formatYear: 'yy',
				minMode: 'day',
				startingDay: 1
			};
			$scope.newRequest = {
				leaveFrom: '',
				leaveTo: '',
				status: 'P',
				leaveType: '',
				comment: ''
			};

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

			$scope.displayMsg = function (display, message, type) {
				let element = angular.element(document.getElementById('leaveRequestError'));
				element.text(message ? message : 'Unable to complete your request.');
				element.css('color', type === 'error' ? 'red' : '#67AB9F');
				element.css('visibility', display ? 'visible' : 'hidden');
			};

			$scope.toggleDatePicker = function (which) {
				$scope.datePickerOn[which] = !$scope.datePickerOn[which];
			};

			// Disable weekend selection
			function disableCalendarDays(date, mode) {
				return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
			};

			// leave/getrquesthistory/{userID}
			$scope.getLeaveRequests = function (userID) {
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
						'status': 'P'
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
						'status': 'R'
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
						'status': 'A'
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
						'status': 'ED'
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

			$scope.addRequest = function () {
				$scope.adding = true;
				$scope.newRequest = {
					leaveFrom: '',
					leaveTo: '',
					status: 'P',
					leaveType: '',
					comment: ''
				};
				$scope.leaveRequests.unshift($scope.newRequest);
			};

			$scope.validateRequest = function (request) {
				if (request && request.leaveFrom && request.leaveTo && request.leaveType && $scope.convertDate(request.leaveFrom) && $scope.convertDate(request.leaveTo)) {
					return true;
				}
				return false;
			};

			$scope.submitRequest = function () {
				if (!$scope.validateRequest($scope.newRequest)) {
					$scope.displayMsg(true, 'Request is not valid.', 'error');
					return;
				}

				$scope.newRequest.createDate = new Date();
				$scope.newRequest.leaveFrom = new Date($scope.newRequest.leaveFrom);
				$scope.newRequest.leaveTo = new Date($scope.newRequest.leaveTo);
				$scope.leaveRequests[0] = $scope.newRequest;
				$scope.adding = false;
				$scope.displayMsg(false);
			};

			$scope.cancelRequest = function (index) {
				// Delete using leaveRequestId
				$scope.leaveRequests.splice(index, 1);

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

			$scope.dateIsInFuture = function (datestring) {
				return new Date(datestring) > new Date();
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

			$scope.leaveRequests = $scope.getLeaveRequests($scope.userID);
		}
	]);
