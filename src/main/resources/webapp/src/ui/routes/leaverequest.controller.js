var angular = require('angular');

require('../data/access.service');
require('../data/leaverequest.service');

angular
	.module('ems')
	.controller('LeaveRequestController', [
		'$rootScope',
		'$scope',
		'$http',
		'$route',
		'Access',
		'LeaveRequest',
		async function ($rootScope, $scope, $http, $route, Access, LeaveRequest) {
			$scope.htmlReady = false;
			$scope.userID = Access.getAccessContent().userID;
			$scope.adding = false;
			$scope.leaveTypes = ['PERSONAL_LEAVE', 'SICK_LEAVE', 'UNPLANNED_LEAVE'];
			$scope.datePickerOn = {
				from: false,
				to: false
			};
			$scope.format = 'yyyy-MM-dd';
			$scope.dateOptions = {
				dateDisabled: disableCalendarDays,
				formatYear: 'yy',
				minMode: 'day',
				startingDay: 1
			};
			$scope.newRequest = {
				leavefrom: '',
				leaveto: '',
				leavetype: '',
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

			$scope.updateList = async function () {
				$scope.leaveRequests = await LeaveRequest.getLeaveRequests($scope.userID, $scope.displayMsg);
				$scope.$apply();

				if ($scope.leaveRequests.length === 0) {
					$scope.displayMsg(true, 'You have no leave request.', 'error');
				}
			};

			$scope.addRequest = function () {
				$scope.adding = true;
				$scope.displayMsg(false);
				$scope.newRequest = {
					leavefrom: '',
					leaveto: '',
					leavetype: '',
					comment: ''
				};
				$scope.leaveRequests.unshift($scope.newRequest);
			};

			$scope.validateRequest = function (request) {
				if (request && request.leavefrom && request.leaveto && request.leavetype && $scope.convertDate(request.leavefrom) && $scope.convertDate(request.leaveto)) {
					return true;
				}
				return false;
			};

			$scope.submitRequest = function () {
				if (!$scope.validateRequest($scope.newRequest)) {
					$scope.displayMsg(true, 'Request is not valid.', 'error');
					return;
				}

				$scope.newRequest.leavefrom = $scope.convertDate($scope.newRequest.leavefrom);
				$scope.newRequest.leaveto = $scope.convertDate($scope.newRequest.leaveto);

				$http
					.get(
						'ems/leave/requestleave/' + $scope.userID + '?' +
						'leavetype=' + $scope.newRequest.leavetype + '&' +
						'leavefrom=' + $scope.newRequest.leavefrom + '&' +
						'leaveto=' + $scope.newRequest.leaveto + '&' +
						'comment=' + $scope.newRequest.comment
					)
					.then(function success(response) {
						if (response.data.success) {
							$scope.adding = false;
							$route.reload();
						} else {
							$scope.displayMsg(true, response.data.message, 'error');
						}
					}, function error(response) {
						$scope.displayMsg(true, response.data.message, 'error');
					});
			};

			$scope.cancelRequest = function (index) {
				$http.get('ems/leave/deleteleave/' + $scope.userID + '?leaveid=' + $scope.leaveRequests[index].leaveRequestId)
					.then(function success(response) {
						console.log(response);
						if (response.data.success) {
							$route.reload();
						} else {
							$scope.displayMsg(true, response.data.message, 'error');
						}
					}, function error(response) {
						$scope.displayMsg(true, response.data.message, 'error');
					});
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

				return yyyy + '-' + (mmChars[1] ? mm : '0' + mmChars[0]) + '-' + (ddChars[1] ? dd : '0' + ddChars[0]);
			};

			$scope.updateList();
		}
	]);
