/**
 * Created by winio_000 on 2015-12-13.
 */
var app = angular.module('mainModule', []);

app.directive('navigationBar', function() {
    return {
        restrict: 'E',
        templateUrl: 'partials/NavigationBar.html'
    }
});