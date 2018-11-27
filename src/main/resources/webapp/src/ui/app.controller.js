var angular = require('angular');

require('./data/access.service.js');

angular
	.module('ems')
	.controller('ApplicationController', [
		'$rootScope',
		'$scope',
		'$window',
		'$location',
		'Access',
		function ($rootScope, $scope, $window, $location, Access) {
			$scope.htmlReady = false;
			$scope.view = {};
			$scope.panelHidden = true;
			$scope.panelClass = 'hidden';
			$scope.bannerImgPath = '';

			// On resize
			angular.element($window).bind('resize', function () {
				var width = parseInt($window.innerWidth / 6);
				// Ensure we can divide by 2
				if (width % 2 !== 0) {
					width += 1;
				}
			});

			// Fired when html ready
			$scope.htmlLoaded = function () {
				$scope.htmlReady = true;
			};

			$scope.ready = function () {
				return $scope.htmlReady;
			};

			$scope.logout = function () {
				Access.setLoginStatus(false);
				Access.setAccessContent({});
				$location.path('/login');
			};

			$scope.togglePanel = function () {
				$scope.panelHidden = !$scope.panelHidden;
				$scope.panelClass = $scope.panelHidden ? 'hidden' : '';
			}

			$scope.$on('$routeChangeStart', function (event, next, current) {
				let route = next.$$route || current.$$route;

				switch (route.controller) {
					case 'LoginController':
					case 'ForgotController':
					case 'ResetController':
						$scope.view.logout = false;
						$scope.view.banner = false;
						$scope.view.nav = false;
						break;
					case 'HomeController':
						$scope.view.logout = true;
						$scope.view.banner = false;
						$scope.view.nav = false;
						break;
					case 'ProfileController':
						$scope.view.logout = true;
						$scope.view.banner = true;
						$scope.view.nav = true;
						$scope.navs = {
							profile: false,
							timekeeping: $rootScope.hasFunction('timekeeping'),
							leaveRequest: $rootScope.hasFunction('leaverequest'),
							documents: $rootScope.hasFunction('documents'),
							management: $rootScope.hasFunction('management'),
							budget: $rootScope.hasFunction('budget'),
							evaluation: $rootScope.hasFunction('evaluation')
						};
						$scope.bannerImgPath = 'assets/images/banner_profile.jpg';
						break;
					case 'TimekeepingController':
						$scope.view.logout = true;
						$scope.view.banner = true;
						$scope.view.nav = true;
						$scope.navs = {
							profile: $rootScope.hasFunction('profile'),
							timekeeping: false,
							leaveRequest: $rootScope.hasFunction('leaverequest'),
							documents: $rootScope.hasFunction('documents'),
							management: $rootScope.hasFunction('management'),
							budget: $rootScope.hasFunction('budget'),
							evaluation: $rootScope.hasFunction('evaluation')
						};
						$scope.bannerImgPath = 'assets/images/banner_timekeeping.jpg';
						break;
					case 'LeaveRequestController':
						$scope.view.logout = true;
						$scope.view.banner = true;
						$scope.view.nav = true;
						$scope.navs = {
							profile: $rootScope.hasFunction('profile'),
							timekeeping: $rootScope.hasFunction('timekeeping'),
							leaveRequest: false,
							documents: $rootScope.hasFunction('documents'),
							management: $rootScope.hasFunction('management'),
							budget: $rootScope.hasFunction('budget'),
							evaluation: $rootScope.hasFunction('evaluation')
						};
						$scope.bannerImgPath = 'assets/images/banner_leave.jpg';
						break;
					case 'DocumentsController':
						$scope.view.logout = true;
						$scope.view.banner = true;
						$scope.view.nav = true;
						$scope.navs = {
							profile: $rootScope.hasFunction('profile'),
							timekeeping: $rootScope.hasFunction('timekeeping'),
							leaveRequest: $rootScope.hasFunction('leaverequest'),
							documents: false,
							management: $rootScope.hasFunction('management'),
							budget: $rootScope.hasFunction('budget'),
							evaluation: $rootScope.hasFunction('evaluation')
						};
						$scope.bannerImgPath = 'assets/images/banner_documents.jpg';
						break;
					case 'ManagementController':
						$scope.view.logout = true;
						$scope.view.banner = true;
						$scope.view.nav = true;
						$scope.navs = {
							profile: $rootScope.hasFunction('profile'),
							timekeeping: $rootScope.hasFunction('timekeeping'),
							leaveRequest: $rootScope.hasFunction('leaverequest'),
							documents: $rootScope.hasFunction('documents'),
							management: false,
							budget: $rootScope.hasFunction('budget'),
							evaluation: $rootScope.hasFunction('evaluation')
						};
						$scope.bannerImgPath = 'assets/images/banner_management.jpg';
						break;
					case 'BudgetController':
						$scope.view.logout = true;
						$scope.view.banner = true;
						$scope.view.nav = true;
						$scope.navs = {
							profile: $rootScope.hasFunction('profile'),
							timekeeping: $rootScope.hasFunction('timekeeping'),
							leaveRequest: $rootScope.hasFunction('leaverequest'),
							documents: $rootScope.hasFunction('documents'),
							management: $rootScope.hasFunction('management'),
							budget: false,
							evaluation: $rootScope.hasFunction('evaluation')
						};
						$scope.bannerImgPath = 'assets/images/banner_budget.jpg';
						break;
					case 'EvaluationController':
						$scope.view.logout = true;
						$scope.view.banner = true;
						$scope.view.nav = true;
						$scope.navs = {
							profile: $rootScope.hasFunction('profile'),
							timekeeping: $rootScope.hasFunction('timekeeping'),
							leaveRequest: $rootScope.hasFunction('leaverequest'),
							documents: $rootScope.hasFunction('documents'),
							management: $rootScope.hasFunction('management'),
							budget: $rootScope.hasFunction('budget'),
							evaluation: false
						};
						$scope.bannerImgPath = 'assets/images/banner_evaluation.jpg';
						break;
					default:
						break;
				}
			});

			$rootScope.goTo = function (page) {
				switch (page) {
					case 'home':
						$location.path('/');
						break;
					case 'login':
						$location.path('/login');
						break;
					case 'forgot':
						$location.path('/forgot');
						break;
					case 'profile':
						$location.path('/profile');
						break;
					case 'timekeeping':
						$location.path('/timekeeping');
						break;
					case 'leaverequest':
						$location.path('/leaverequest');
						break;
					case 'documents':
						$location.path('/documents');
						break;
					case 'management':
						$location.path('/management');
						break;
					case 'budget':
						$location.path('/budget');
						break;
					case 'evaluation':
						$location.path('/evaluation');
						break;
					default:
						$location.path('/');
				}
			};

			$rootScope.hasFunction = function (fn) {
				return Access.getAccessContent().fns.indexOf(fn) !== -1;
			};
		}
	]);
