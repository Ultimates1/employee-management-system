var angular = require('angular');

angular
	.module('ems')
	.factory('Workflow', [
		'$http',
		function ($http) {
			return {
				getWorkflows: function (userID, callback) {
					return $http.get('ems/workflow/getpending/' + userID)
						.then(function success(response) {
							if (response.data.success) {
								callback(false);
								return response.data.pendinglist;
							} else {
								callback(true, response.data.message, 'error');
								return [];
							}
						}, function error(response) {
							callback(true, response.data.message, 'error');
							return [];
						});
				},
				updateWorkflow: function (userID, workflowID, action, comment, callback) {
					return $http.get('ems/workflow/action/' + userID + '?workflowid=' + workflowID + '&action=' + action + '&comment=' + comment)
						.then(function success(response) {
							if (response.data.success) {
								callback(true, response.data.message);
							} else {
								callback(true, response.data.message, 'error');
							}
						}, function error(response) {
							callback(true, response.data.message, 'error');
						});
				}
			}
		}
	]);
