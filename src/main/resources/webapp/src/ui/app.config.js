var angular = require('angular');

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
				.when('/profile', {
					templateUrl: 'src/ui/routes/profile.template.html',
					controller: 'ProfileController'
				})
				.when('/timekeeping', {
					templateUrl: 'src/ui/routes/timekeeping.template.html',
					controller: 'TimekeepingController'
				})
				.when('/leaverequest', {
					templateUrl: 'src/ui/routes/leaverequest.template.html',
					controller: 'LeaveRequestController'
				})
				.when('/documents', {
					templateUrl: 'src/ui/routes/documents.template.html',
					controller: 'DocumentsController'
				})
				.when('/management', {
					templateUrl: 'src/ui/routes/management.template.html',
					controller: 'ManagementController'
				})
				.when('/budget', {
					templateUrl: 'src/ui/routes/budget.template.html',
					controller: 'BudgetController'
				})
				.when('/workflow', {
					templateUrl: 'src/ui/routes/workflow.template.html',
					controller: 'WorkflowController'
				})
				.otherwise('/');
		}
	]);
