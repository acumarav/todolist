'use strict';
/* Services */

var tasksServices =
    angular.module('tasksServices', ['ngResource']);

tasksServices.factory('Tasks', ['$resource',
    function($resource) {

        return $resource("/rs/tasks", {}, {
            get: {method: 'GET', cache: false, isArray: true}
        });

    }]);

tasksServices.factory('Task', ['$resource',
    function($resource) {

        return $resource("/rs/task/:id", {}, {
            get: {method: 'GET', cache: false, isArray: true},
            save: {method: 'POST', cache: false, isArray: true},
            update: {method: 'PUT', cache: false, isArray: true},
            delete: {method: 'DELETE', cache: false, isArray: false}
        });
    }]);

tasksServices.factory('Login', ['$resource',
    function($resource) {
        return $resource( "/rs/login", {}, {
                login: {method: 'POST', cache: false, isArray: false}
            });
    }]);

var lookupService =
    angular.module('lookupService', ['ngResource']);

    lookupService.factory('Users', ['$resource',
    function($resource) {

        return $resource("/rs/users", {}, {
            get: {method: 'GET', cache: false, isArray: true}
        });
    }]);

    lookupService.factory('Statuses', ['$resource',
        function($resource) {

            return $resource("/rs/statuses", {}, {
                get: {method: 'GET', cache: false, isArray: true}
            });
    }]);

lookupService.factory('Priorities', ['$resource',
    function($resource) {

        return $resource("/rs/priorities", {}, {
            get: {method: 'GET', cache: false, isArray: true}
        });
    }]);

var listService =
    angular.module('listService', ['ngResource']);

listService.factory('Lists', ['$resource',
    function($resource) {

        return $resource("/rs/lists", {}, {
            get: {method: 'GET', cache: false, isArray: true},
            save: {method: 'POST', cache: false, isArray: false}
        });
    }]);



