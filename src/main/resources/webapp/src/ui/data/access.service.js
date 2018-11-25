var angular = require('angular');

angular
	.module('ems')
	.factory('Access', [
		'$rootScope',
		'$window',
		function ($rootScope, $window) {
			return {
				getLoginStatus: function () {
					return JSON.parse($window.localStorage.getItem('loggedIn'));
				},
				setLoginStatus: function (status) {
					$window.localStorage.setItem('loggedIn', JSON.stringify(status));
					if (!status) {
						$window.localStorage.setItem('accessContent', {});
					}
				},
				getAccessContent: function () {
					return JSON.parse($window.localStorage.getItem('accessContent'));
				},
				setAccessContent: function (content) {
					$window.localStorage.setItem('accessContent', JSON.stringify(content));
				}
			};
		}
	]);
