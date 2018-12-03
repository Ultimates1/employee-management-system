var angular = require('angular');

angular
	.module('ems')
	.factory('LeaveRequest', [
		'$http',
		function ($http) {
			return {
				getLeaveRequests: function (userID, callback) {
					return $http.get('ems/leave/getrequesthistory/' + userID)
						.then(function success(response) {
							if (response.data.success) {
								callback(false);
								return response.data.leavebalance;
							} else {
								callback(true, response.data.message, 'error');
								return {};
							}
						}, function error(response) {
							callback(true, response.data.message, 'error');
							return {};
						});
				}
			}
		}
	]);
