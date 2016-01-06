/**
 * Created by winio_000 on 2015-12-13.
 */
var app = angular.module('mainModule', ['ui.bootstrap']);

app.directive('navigationBar', function () {
    return {
        restrict: 'E',
        templateUrl: 'partials/NavigationBar.html'
    }
});

app.directive('clientAddForm', function () {
    return {
        restrict: 'E',
        templateUrl: 'partials/ClientAddForm.html'
    }
});

app.directive('flightAddForm', function () {
    return {
        restrict: 'E',
        templateUrl: 'partials/FlightAddForm.html'
    }
});