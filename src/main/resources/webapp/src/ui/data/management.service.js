var angular = require('angular');

angular
	.module('ems')
	.factory('Management', [
		'$http',
		function ($http) {
			return {
				getProjectList: function (userID, callback) {
					return $http.get('ems/utils/getprojects/' + userID)
						.then(function success(response) {
							if (response.data.success) {
								callback(false);
								return response.data.projectList;
							} else {
								callback(true, response.data.message, 'error');
								return [];
							}
						}, function error(response) {
							callback(true, response.data.message, 'error');
							return [];
						});
				},
				getEmployeeList: function (callback) {
					return $http.get('ems/utils/getactiveemployee')
						.then(function success(response) {
							if (response.data.success) {
								callback(false);
								return response.data.employeeList;
							} else {
								callback(true, response.data.message, 'error');
								return [];
							}
						}, function error(response) {
							callback(true, response.data.message, 'error');
							return [];
						});
				},
				getProjectEmployees: function (userID, projectID, callback) {
					return $http.get('ems/utils/getemployeesofproject/' + userID + '?projectid=' + projectID)
						.then(function success(response) {
							if (response.data.success) {
								callback(false);
								return response.data.employeeList;
							} else {
								callback(true, response.data.message, 'error');
								return [];
							}
						}, function error(response) {
							callback(true, response.data.message, 'error');
							return [];
						});
				},
				addProjectEmployee: function (projectID, employeeID, callback) {
					return $http.get('ems/utils/addemployeetoproject?projectid=' + projectID + '&employeeid=' + employeeID)
						.then(function success(response) {
							if (response.data.success) {
								callback(true, response.data.message);
							} else {
								callback(true, response.data.message, 'error');
							}
						}, function error(response) {
							callback(true, response.data.message, 'error');
						});
				},
				removeProjectEmployee: function (projectID, employeeID, callback) {
					return $http.get('ems/utils/deleteemployeetoproject?projectid=' + projectID + '&employeeid=' + employeeID)
						.then(function success(response) {
							if (response.data.success) {
								callback(true, response.data.message);
							} else {
								callback(true, response.data.message, 'error');
							}
						}, function error(response) {
							callback(true, response.data.message, 'error');
						});
				},
				addProject: function (project, callback) {
					return $http
						.get(
							'ems/utils/addproject?projectname=' + project.name +
							'&projectdescription=' + project.description +
							'&projectmanager=' + project.manager.userId +
							'&projecthr=' + project.hr.userId +
							'&ongoingproject=' + project.ongoing
						)
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
			};
		}
	]);
