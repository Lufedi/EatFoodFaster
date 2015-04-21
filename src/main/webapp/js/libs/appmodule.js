(function () {
    var app = angular.module('modone', ['ngRoute']);

    app.config(function ($routeProvider) {
        $routeProvider

                // route for the about page
                .when('/estado', {
                    templateUrl: 'estado.html'
                });
    });
})();





