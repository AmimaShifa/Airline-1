/**
 * Created by winio_000 on 2015-12-13.
 */
app.controller('NavigationCtrl', function($scope, $http) {

// functions ////////////////////////////////////////
    $scope.getNavigationMap = function(){
        $http.get('menu.json').success(function(response) {
            $scope.navigationMap = response;
        });
    };

    $scope.navigateTo = function($navigationPart){
        if (typeof $navigationPart.url != 'undefined') {
            $scope.includedPartUrl = $navigationPart.url;
        }
    };
//////////////////////////////////////////////////////
    $scope.includedPartUrl = "partials/SummaryPage.html";
    $scope.navigationMap = [];
    $scope.getNavigationMap();
});