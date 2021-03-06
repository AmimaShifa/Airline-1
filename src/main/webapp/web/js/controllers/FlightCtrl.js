/**
 * Created by winio_000 on 2015-12-16.
 */
app.controller('FlightCtrl', ['$scope', '$http', function ($scope, $http) {

    /////////////CRUD FUNCTIONS////////////////////////
    $scope.getFlights = function () {
        $http({
            method: 'GET',
            url: '/airlines/flights',
            params: $scope.parameters
        }).then(function successCallback(response) {
            $scope.flights = response.data;
        }, function errorCallback() {
            console.log('Error during fetching flights list.');
        });
    };

    $scope.addFlight = function (flight) {
        $http({
            method: 'POST',
            url: '/airlines/flights',
            data: {
                "airlineName": flight.airlineName,
                "departure": flight.departure,
                "arrival": flight.arrival,
                "source": flight.source,
                "destination": flight.destination,
                "flightPrice": flight.flightPrice,
                "seats": flight.seats
            }
        }).then(function successCallback(response) {
            $scope.getFlights();
        }, function erorrCallback() {
            console.log('Unable to add flight ');
        });
    };

    $scope.deleteFlight = function (flight) {
        $http({
            method: 'DELETE',
            url: '/airlines/flights' + '/' + flight.id
        }).then(function successCallback(response) {
            $scope.getFlights();
        }, function errorCallback() {
            console.log('Unable to delete flight ');
        });
    };

    $scope.getFlghtsUsingQueryParams = function (_source_, _destination_, _arrival_, _departure_) {
        $scope.parameters = {
            source: _source_,
            destination: _destination_,
            arrival: _arrival_,
            departure: _departure_
        };
        $scope.getFlights();
    };

    //ON INITIALIZE
    (function () {
        $scope.enableForm = false;
        $ADD_NEW_FLIGHT = "Add new flight";
        $HIDE_FORM = "Hide form";
        $scope.toggleFormTxt = $ADD_NEW_FLIGHT;
        $scope.controllerName = 'FlightCtrl';

        $scope.showForm = function () {
            $scope.enableForm = !$scope.enableForm;
            $scope.toggleFormTxt = $scope.enableForm ? $HIDE_FORM : $ADD_NEW_FLIGHT;
        };

        $scope.getFlights();
    })();

}]);