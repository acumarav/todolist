/**
 * Created by alex on 7/17/2015.
 */

'use strict';
/*App Module*/

var tasksApp=angular.module('tasksApp',[
    'ngRoute',
    'ngResource',
    'ngCookies',
    'taskControllers',
    'listControllers',
    'tasksServices',
    'todoBusinessServices',
    'listService',
    'userTasksControllers',
    'lookupService'
]);

tasksApp.config([ '$routeProvider','$locationProvider',
function($routeProvider, $locationProvider){
    $routeProvider.when('/',{
        templateUrl:'/static/ang/partials/main.html',
        controller:'MainCtrl'
    })
        .when('/tasks',{
            templateUrl:'/static/ang/partials/tasks.html',
            controller:'AllTasksCtrl'
        })
        .when('/lists',{
            templateUrl:'/static/ang/partials/lists.html',
            controller:'NewListCtrl'
        })
        .when('/usertasks',{
            templateUrl:'/static/ang/partials/usertasks.html',
            controller:'UserTasksCtrl'
        })
        .when('/login', {
            templateUrl: '/static/ang/partials/login.html',
            controller: 'LoginCtrl'
        }).when('/logOut', {
            templateUrl: '/static/ang/partials/login.html',
            controller: 'LogoutCtrl'
        });


    $locationProvider.html5Mode(false).hashPrefix('!');
}
]);

tasksApp.filter('getById', function() {
    return function(input, id) {
        var i=0, len=input.length;
        for (; i<len; i++) {
            if (+input[i].id == +id) {
                return input[i];
            }
        }
        return null;
    }
});