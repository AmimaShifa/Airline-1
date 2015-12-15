/**
 * Created by winio_000 on 2015-12-13.
 */
app.controller('ClientCtrl', function ($scope, $http) {

    $scope.enableForm = false;
    $ADD_NEW_CLIENT = "Add new client";
    $HIDE_FORM = "Hide form";
    $scope.toggleFormTxt = $ADD_NEW_CLIENT;

    $scope.getClients = function () {
        $http({
            method: 'GET',
            url: '/airlines/clients'
        }).then(function successCallback(response) {
            $scope.clients = response.data;
        }, function errorCallback() {
            console.log('Error during fetching clients list.');
        });
    };

    $scope.showForm = function () {
        $scope.enableForm = !$scope.enableForm;
        $scope.toggleFormTxt = $scope.enableForm ? $HIDE_FORM : $ADD_NEW_CLIENT;
    };

    $scope.addClient = function (client) {
        $http({
            method: 'POST',
            url: '/airlines/clients',
            data: {
                "firstName": client.firstName,
                "lastName": client.lastName
            }
        }).then(function successCallback(response) {
            $scope.getClients();
        }, function erorrCallback() {
            console.log('Unable to add client ', client.firstName);
        });
    };

    $scope.deleteClient = function (client) {
        $http({
            method: 'DELETE',
            url: '/airlines/clients' + '/' + client.id
        }).then(function successCallback(response) {
            $scope.getClients();
        }, function errorCallback() {
            console.log('Unable to delete client ', client.firstName);
        });
    };

    $scope.getClients();
});