/**
 * Created by winio_000 on 2015-12-15.
 */
app.controller('ReservationCtrl', ['$scope', '$http', '$filter', '$controller', '$timeout', function ($scope, $http, $filter, $controller, $timeout) {

    $scope.departureDate = $filter('date')(new Date(), 'yyyy-MM-dd');
    $scope.arrivalDate = $filter('date')(new Date(new Date().getTime() + (3 * 60 * 60 * 24 * 1000)), 'yyyy-MM-dd');
    $scope.controllerName = 'ReservationCtrl';
    $scope.flights = [];

    //MAKE RESERVATION
    $scope.makeReservation = function (clientId) {
        $http({
            method: 'POST',
            url: '/airlines/reservations',
            data: {}
        }).then(function successCallback(response) {
            console.log("Reserwacja udana");
        }, function errorCallback() {
            console.log("Reserwacja nieudana! ");
        });
    };

    var flightControllerScope = $scope.$new();
    $controller('FlightCtrl', {$scope: flightControllerScope});

    $scope.getFlights = function (source, destination, arrival, departure) {
        flightControllerScope.getFlghtsUsingQueryParams(source, destination, arrival, departure);
        //TODO replace this with promises!!!!
        $timeout(function () {
            setProcessedFlights();
        }, 100);
    };
    var setProcessedFlights = function () {
        $scope.flights = flightControllerScope.flights;
    };
}]);