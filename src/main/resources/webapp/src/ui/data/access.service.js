var angular = require('angular');

angular
	.module('ems')
	.factory('Access', [
		'$rootScope',
		'$window',
		function ($rootScope, $window) {
			return {
				getLoginStatus: function () {
					return $window.localStorage.getItem('loggedIn');
				},
				setLoginStatus: function (status) {
					$window.localStorage.setItem('loggedIn', status);
					if (!status) {
						$window.localStorage.setItem('accessContent', {});
					}
				},
				getAccessContent: function () {
					return $window.localStorage.getItem('accessContent');
				},
				setAccessContent: function (content) {
					$window.localStorage.setItem('accessContent', content);
				}
			};
		}
	]);
