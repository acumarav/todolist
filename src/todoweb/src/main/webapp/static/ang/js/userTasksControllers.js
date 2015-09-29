'use strict';

/*Controllers*/

var userTasksControllers= angular.module('userTasksControllers',[]);

userTasksControllers.controller(
    'UserTasksCtrl',
    ['$scope','$location','$http', '$route','Users', 'Task', 'Tasks','Statuses','Priorities', 'Lists', 'checkCreds', 'getToken', 'getUserId',
        function NewListCtrl($scope, $location, $http, $route, Users, Task, Tasks, Statuses,Priorities, Lists, checkCreds, getToken, getUserId){

        $scope.message='Manage User Assignments';



        if(!checkCreds()){
            $location.path('/login');
            return;
        }

        $http.defaults.headers.common['Authorization'] = 'Basic ' + getToken();

        $scope.userId=getUserId();

        $scope.allTasks=[];

        $scope.allLists=[];

        $scope.getListTasks=function(list){

        };

        Lists.get({},function success(response){
                $scope.allLists=response;},
            $scope.logError);

        Tasks.get({},
            function success(response) {
                console.log("Success:" + JSON.stringify(response));
                $scope.allTasks = response;
            },
            $scope.logError
        );


        $scope.logError= function(errorResponse){
            console.log("Error:   " + JSON.stringify(errorResponse));
        };

        $scope.deleteTask = function(taskId){
            Task.delete({id:taskId},
                function success(response) {
                    console.log('deleteOk');
                    $route.reload();
                },
                $scope.logError
            );
        };

            $scope.updateTask = function(task){

                var taskToUpdate= {};
                taskToUpdate.status=task.status;
                taskToUpdate.priority=task.priority;
                taskToUpdate.id=task.id;

                console.log(JSON.stringify(taskToUpdate));


                Task.update(taskToUpdate, function success(response) {
                        console.log('updateOk');
                        $route.reload();
                    },

                    $scope.logError);
            };


        Users.get({},
            function success(response) {
                console.log("Success:" + JSON.stringify(response));
                $scope.allUsers = response;
            },
            $scope.logError
        );

        Statuses.get({},
            function success(response) {
                console.log("Success:" + JSON.stringify(response));
                $scope.allStatuses = response;
            },
            $scope.logError
        );

        Priorities.get({},
            function success(response) {
                console.log("Success:" + JSON.stringify(response));
                $scope.allPriorities = response;
            },
            $scope.logError
        );
    }]
);
