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

    $scope.calendar = {
        opened: {},
        dateFormat: 'yyyy-MM-dd',
        open: function ($event, which) {
            $event.preventDefault();
            $event.stopPropagation();
            $scope.calendar.opened[which] = true;
        }
    };

    $scope.sourceOptions = [
        {name: 'Kraków', value: 'Kraków'},
        {name: 'Warszawa', value: 'Warszawa'},
        {name: 'Poznań', value: 'Poznań'},
        {name: 'Gdańsk', value: 'Gdańsk'}
    ];

    $scope.destinationOptions = [
        {name: 'Oslo', value: 'Oslo'},
        {name: 'Londyn', value: 'Londyn'},
        {name: 'Paryż', value: 'Paryż'},
        {name: 'Berlin', value: 'Berlin'}
    ];
    $scope.passengersOptions = [0, 1, 1, 3, 4];

    $scope.passengers = [0, 0, 0, 0];

    $scope.flightClass = [
        {value: 'Economy class'},
        {value: 'Premium class'},
        {value: 'Business class'}
    ];


    $scope.flight = {
        source: $scope.sourceOptions[0].value,
        destination: $scope.destinationOptions[0].value
    };


    var flightControllerScope = $scope.$new();
    $controller('FlightCtrl', {$scope: flightControllerScope});
    $scope.getFlights = function (source, destination, arrival, departure) {
        if ($scope.isReturnFlight == false) {
            flightControllerScope.getFlghtsUsingQueryParams(source, destination, null, departure);
        } else {
            flightControllerScope.getFlghtsUsingQueryParams(source, destination, arrival, departure);
        }
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
            $scope.reservationResultFlightListStyle = {};
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

            $scope.reservationResultFlightListStyle = {
                position: "absolute",
                padding: "0%",
                margin: "0 auto",
                'max-width': "200px"
            };

            $scope.formHorizontalStyle = {
                'min-width': "320px"
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