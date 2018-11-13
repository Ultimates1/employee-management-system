var angular = require('angular');

//---------------
// Config
//---------------
angular
	.module('ems')
	.config([
		'$routeProvider',
		function config($routeProvider) {
			// Configure routes
			$routeProvider
				.when('/', {
					templateUrl: 'src/ui/routes/home.template.html',
					controller: 'HomeController'
				})
				.when('/login', {
					templateUrl: 'src/ui/routes/login.template.html',
					controller: 'LoginController'
				})
				.when('/forgot', {
					templateUrl: 'src/ui/routes/forgot.template.html',
					controller: 'ForgotController'
				})
				.when('/reset/:user', {
					templateUrl: 'src/ui/routes/reset.template.html',
					controller: 'ResetController'
				})

				.otherwise('/');
		}
	]);
