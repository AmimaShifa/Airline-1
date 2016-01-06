/**
 * Created by winio_000 on 2015-12-15.
 */
app.controller('ReservationCtrl', ['$scope', '$http', '$filter', function ($scope, $http, $filter) {

    $scope.departureDate = $filter('date')(new Date(), 'dd-MM-yyyy');
    $scope.arrivalDate = $filter('date')(new Date(new Date().getTime() + (3 * 60 * 60 * 24 * 1000)), 'dd-MM-yyyy');

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

}]);