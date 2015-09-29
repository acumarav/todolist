
'use strict';

var todoBusinessServices = angular.module('todoBusinessServices', ['ngCookies']);

todoBusinessServices.factory('checkCreds',
    ['$cookies', function($cookies) {

        return function() {
            var returnVal = false;
            //var creds = $cookies.creds;
            var creds = $cookies.get("creds");
            if (creds !== undefined && creds !== "") {
                returnVal = true;
            }

            return returnVal;
        };
    }]);

todoBusinessServices.factory('getToken',
    ['$cookies', function($cookies) {
        return function() {
            var returnVal = "";
            //var creds = $cookies.creds;
            var creds = $cookies.get("creds");

            if (creds !== undefined && creds !== "") {
                returnVal = btoa(creds);
            }
            return returnVal;
        };
    }]);

todoBusinessServices.factory('getUsername',
    ['$cookies', function($cookies) {
        return function() {
            var returnVal = "";
            //var username = $cookies.username;
            var username = $cookies.get("username");
            if (username !== undefined && username !== "") {
                returnVal = username;
            }
            return returnVal;
        };
    }]);

todoBusinessServices.factory('getUserId',
    ['$cookies', function($cookies) {
        return function() {
            var returnVal = "";
            //var userId = $cookies.userId;
            var userId = $cookies.get("userId");
            if (userId !== undefined && userId !== "") {
                returnVal = userId;
            }
            return returnVal;
        };
    }]);


todoBusinessServices.factory('setCreds',
    ['$cookies', function($cookies) {
        return function(un, pw, uid) {

            var expireDate = new Date();
            expireDate.setDate(expireDate.getDate() + 1);

            var token = un.concat(":", pw);
            //$cookies.put("creds", token, {'expires': expireDate});
            $cookies.put("creds", token, {'expires': 0});
            $cookies.put("username", un, {'expires': 0});
            $cookies.put("userId", uid, {'expires': 0});
            /*$cookies.creds = token;
            $cookies.username = un;
            $cookies.userId = uid;*/
        };
    }]);


todoBusinessServices.factory('deleteCreds',
    ['$cookies', function($cookies) {
        return function() {

            $cookies.remove('creds');
            $cookies.remove('username');
            $cookies.remove('userId');
        };
    }]);




