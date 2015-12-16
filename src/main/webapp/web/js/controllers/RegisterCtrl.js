/**
 * Created by winio_000 on 2015-12-15.
 */
app.controller('RegisterCtrl', ['$scope', '$controller', function ($scope, $controller) {
    var clientCtrlScope = $scope.$new();
    $controller('ClientCtrl', {$scope: clientCtrlScope});

    $scope.loggedIn = false;

    $scope.afterLogIn = function (client) {
        if ($scope.loggedIn == true) {
            alert("Dziękujemy za utworzenie konta");
            clientCtrlScope.addClient(client);
        }
    };

    $scope.setLoginTrue = function () {
        $scope.loggedIn = true;
    };
}]);