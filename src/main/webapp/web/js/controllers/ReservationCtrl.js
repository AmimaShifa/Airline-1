/**
 * Created by winio_000 on 2015-12-15.
 */
app.controller('ReservationCtrl', ['$scope', '$http', '$filter', '$controller', '$timeout', function ($scope, $http, $filter, $controller, $timeout) {

    /////////////CRUD FUNCTIONS////////////////////////
    $scope.addReservation = function (fl) {
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

    $scope.setOneWayFlight = function () {
        $scope.isReturnFlight = false;
    };

    $scope.setReturnFlight = function () {
        $scope.isReturnFlight = true;
    };

    $scope.chooseFlight = function (flight) {
        if (confirm('Are you sure you want to reserve this flight?' +
                '\nsource : ' + flight.source +
                '\ndestination : ' + flight.destination +
                '\ndeparture : ' + flight.arrival +
                '\narrival : ' + flight.arrival + '')) {
            $scope.addReservation(flight);
        } else {
            console.log("DUPA");
        }
    };

    $scope.hoverIn = function () {
        $scope.hoverReservationButton = true;
    };

    $scope.hoverOut = function () {
        $scope.hoverReservationButton = false;
    };

    var setProcessedFlights = function () {
        $scope.flights = flightControllerScope.flights;
        if ($scope.flights.length == 0) {
            $scope.labelColumnSize = 3;
            $scope.selectColumnSize = 3;
            $scope.reservationContainerStyle = {};
            $scope.reservationResultStyle = {};
            $scope.formHorizontalStyle = {};
            $scope.containerStyle = {};
            $scope.containerStyle = {
                'padding-left': '0px'
            };
            $scope.flightType = {
                'margin-left': '20%',
                'margin-top': '25px'
            };

        } else {
            $scope.labelColumnSize = 4;
            $scope.selectColumnSize = 7;

            $scope.reservationContainerStyle = {
                display: 'inline-flex'
            };

            $scope.reservationResultStyle = {
                padding: "0%",
                margin: "0 auto",
                'max-width': "200px"
            };

            $scope.formHorizontalStyle = {
                'min-width': "300"
            };

            $scope.containerStyle = {
                'padding-left': '0px'
            };
            $scope.flightType = {
                'margin-left': '10%',
                'margin-top': '25px'
            };

        }
    };

    (function () {
        $scope.departureDate = $filter('date')(new Date(), 'yyyy-MM-dd');
        $scope.arrivalDate = $filter('date')(new Date(new Date().getTime() + (3 * 60 * 60 * 24 * 1000)), 'yyyy-MM-dd');
        $scope.controllerName = 'ReservationCtrl';
        $scope.labelColumnSize = 3;
        $scope.selectColumnSize = 3;
        $scope.reservationSubmit = {
            'margin-left': "40%"
        };
        $scope.containerStyle = {
            'padding-left': '0px'
        };
        $scope.flightType = {
            'margin-left': '20%',
            'margin-top': '25px'
        };
    })();
}]);