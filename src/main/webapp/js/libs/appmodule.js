(function () {
    var app = angular.module('modone', ['ngRoute']);

    app.config(function ($routeProvider) {
        $routeProvider

                // route for the about page
                .when('/estado', {
                    templateUrl: 'estado.html'
                });
    });
    
    app.controller('controlpedidos',
                function($scope,$http){
                    
                    $scope.lista=[1,2,3,4];
                    this.consultar=function(){
                     
                        
                        $http.get('rest/pedidos/').
                        success(function(data, status, headers, config) {
                          // this callback will be called asynchronously
                          // when the response is available
                          $scope.lista=data;
                            
                               
                        }).
                        error(function(data, status, headers, config) {
                          // called asynchronously if an error occurs
                          // or server returns response with an error status.
                          alert("error!");
                        });
                        
                        
                        
                    };
                }
                                   
    );
    
})
();





