/**
 * Created by alexts on 18.07.15.
 */

'use strict';

/*Controllers*/

var listControllers= angular.module('listControllers',[]);

listControllers.controller(
    'NewListCtrl',
    ['$scope','$location','$http', 'Users', 'Task', 'Statuses','Priorities', 'Lists', function NewListCtrl($scope, $location, $http, Users, Task, Statuses,Priorities, Lists){

        $scope.message='Create Task List Screen';

        $scope.listTitle="NONAME...";

        $scope.newTask={};

        $scope.listTasks=[];

        $scope.addTask=function(){

            if($scope.currentTaskList==null){
                Lists.save({title:$scope.listTitle},
                    function success(response) {

                        $scope.currentTaskList=response;
                        $scope.saveNewTask();
                    },
                    $scope.logError
                );
            }
            else{
                $scope.saveNewTask();
            }

        };

        $scope.logError= function(errorResponse){
            console.log("Error:   " + JSON.stringify(errorResponse));
        };

        $scope.saveNewTask=function(){
            $scope.newTask.taskListId=$scope.currentTaskList.id;

            var taskToSave= angular.copy($scope.newTask);

            console.log(JSON.stringify(taskToSave));


            Task.save(taskToSave, function success(response) {
                    [].push.apply($scope.listTasks, response);
                    $scope.newTask={};
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

        $scope.showTasks = function(text) {
            $location.path('tasks');
        }
    }]
);
