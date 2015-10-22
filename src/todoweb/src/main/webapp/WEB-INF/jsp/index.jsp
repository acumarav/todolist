<!DOCTYPE html >
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>todo list app</title>

    <link rel="stylesheet" type="text/css" href="static/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="static/css/bootstrap-theme.css"/>

    <script type="text/javascript" src="static/js/libs/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="static/js/libs/bootstrap.js"></script>
    <script src="static/js/libs/angular.js"></script>
    <script src="static/js/libs/angular-route.js"></script>
    <script src="static/js/libs/angular-resource.js"></script>
    <script src="static/js/libs/angular-cookies.js"></script>

    <script src="static/ang/js/app.js"></script>
    <script src="static/ang/js/controllers.js"></script>
    <script src="static/ang/js/listControllers.js"></script>
    <script src="static/ang/js/userTasksControllers.js"></script>
    <script src="static/ang/js/services.js"></script>
    <script src="static/ang/js/businessServices.js"></script>

</head>
<body>

<nav id="myNavbar" class="navbar navbar-default navbar-inverse navbar-fixed-top" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbarCollapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">ToDo App</a>
        </div>

        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href=""><spring:message code="header.home.link.label"/></a></li>
                <li><a href="https://cz.linkedin.com/pub/alex-tsumarau/10/b10/93a" target="_blank">Profile</a></li>
                <li><a id="lo" href="#!logOut">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>


    <div class="container">
        <div class="jumbotron" ng-app="tasksApp">
            <div ng-view></div>
        </div>

        <div class="row">
            <div class="col-sm-12">
                <footer>

                </footer>
            </div>
        </div>
    </div>



</body>
</html>