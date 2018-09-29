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
				.otherwise('/');
		}
	]);