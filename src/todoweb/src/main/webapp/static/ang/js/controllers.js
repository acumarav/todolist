/**
 * Created by alex on 7/17/2015.
 */


'use strict';

/*Controllers*/

var taskControllers= angular.module('taskControllers',[]);

taskControllers.controller(
    'MainCtrl',
    ['$scope','$location','$http', 'Lists', 'getToken',function MainCtrl($scope, $location, $http ,Lists, getToken){
        $scope.message='Available Task Lists:';

        $http.defaults.headers.common['Authorization'] = 'Basic ' + getToken();

        Lists.get({},function success(response){
            $scope.allLists=response;},
            $scope.logError);

        $scope.showTasks = function(text) {
            $location.path('tasks');
        };

        $scope.showUserTasks = function(text) {
            $location.path('usertasks');
        };

        $scope.newList = function(text) {
            $location.path('lists');
        };

        $scope.logError= function(errorResponse){
            console.log("Error:   " + JSON.stringify(errorResponse));
        };
    }]
);

taskControllers.controller(
    'AllTasksCtrl',
    ['$scope','$location','$http','Tasks','Users', 'Task', 'Statuses','Priorities',function AllTasksCtrl($scope, $location, $http, Tasks, Users, Task, Statuses, Priorities){
        $scope.message='This is all tasks page!';

        Tasks.get({},
            function success(response) {
                console.log("Success:" + JSON.stringify(response));
                $scope.allTasks = response;
            },
            function error(errorResponse) {
                console.log("Error:" + JSON.stringify(errorResponse));
            }
        );

        $scope.deleteTask = function(taskId){
            Task.delete({id:taskId},
                function success(response) {
                    console.log('deleteOk');
                },
                function error(errorResponse) {
                    console.log("Error:" + JSON.stringify(errorResponse));
                }
            );
        };

        Users.get({},
            function success(response) {
                console.log("Success:" + JSON.stringify(response));
                $scope.allUsers = response;
            },
            function error(errorResponse) {
                console.log("Error:" + JSON.stringify(errorResponse));
            }
        );

        Statuses.get({},
            function success(response) {
                console.log("Success:" + JSON.stringify(response));
                $scope.allStatuses = response;
            },
            function error(errorResponse) {
                console.log("Error:" + JSON.stringify(errorResponse));
            }
        );

        Priorities.get({},
            function success(response) {
                console.log("Success:" + JSON.stringify(response));
                $scope.allPriorities = response;
            },
            function error(errorResponse) {
                console.log("Error:" + JSON.stringify(errorResponse));
            }
        );

    }]
);

taskControllers.controller('LoginCtrl',
    ['$scope', '$location', 'Login', 'setCreds',
        function LoginCtrl($scope, $location, Login, setCreds) {

            $scope.submit = function(){
                $scope.sub = true;
                var postData = {
                    "username" : $scope.username,
                    "password" : $scope.password
                };

                Login.login({}, postData,
                    function success(response) {

                        if(response.authenticated){
                            $scope.userId=response.userId;
                            setCreds($scope.username, $scope.password,$scope.userId);
                            $location.path('/usertasks');
                        }else{
                            $scope.error = "Login Failed"
                        }
                    },
                    function error(errorResponse) {
                        console.log("Error:" + JSON.stringify(errorResponse));
                    }
                );
            };
        }]);

taskControllers.controller('LogoutCtrl',
    ['$location', 'deleteCreds',
        function LogoutCtrl($location, deleteCreds) {

            deleteCreds();
            $location.path('/');

        }]);
