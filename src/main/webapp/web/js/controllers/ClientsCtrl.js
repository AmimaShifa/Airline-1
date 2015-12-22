/**
 * Created by winio_000 on 2015-12-13.
 */
app.controller('ClientCtrl', ['$scope', '$http', function ($scope, $http) {

    $scope.enableForm = false;
    $ADD_NEW_CLIENT = "Add new client";
    $HIDE_FORM = "Hide form";
    $scope.toggleFormTxt = $ADD_NEW_CLIENT;
    $scope.clients = {};

    /////////////CRUD FUNCTIONS////////////////////////
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
                "lastName": client.lastName,
                "email" : client.email,
                "password" : client.password
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

    $scope.updateClient = function(client) {
        $http({
            method: 'PUT',
            url: '/airlines/clients' + '/' + client.id,
            data: {
                "firstName": client.firstName,
                "lastName": client.lastName,
                "email" : client.email,
                "password" : client.password
            }
        }).then(function successCallback(response) {
            $scope.getClients();
        }, function errorCallback() {
            console.log('Unable to update client ', client.firstName);
        });
    };

    $scope.getClients();

    /////////////EDITING CLIENT///////////////////////
    $scope.selected = {};

    // gets the template to ng-include for a table row / item
    $scope.getTemplate = function (client) {
        if (client.id === $scope.selected.id) return 'edit';
        else return 'display';
    };

    $scope.editClient = function (client) {
        $scope.selected = angular.copy(client);
    };

    $scope.saveClient = function () {
        $scope.updateClient($scope.selected);
        $scope.reset();
    };

    $scope.reset = function () {
        $scope.selected = {};
    };

}]);