(function () {
    var app = angular.module('modone', ['ngRoute']);

    app.config(function ($routeProvider) {
        $routeProvider

                // route for the about page
                .when('/pedidosSinNotificar', {
                    templateUrl: 'pedidosSinNotificar.html'
                })
                
                .when('/estado', {
                    templateUrl: 'estado.html'
                });
    });
    
    app.controller('controlpedidos',
                function($scope,$http){
                    
                    $scope.lista=[1,2,3,4];
                    $scope.error;
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
                          
                          alert(status);
                          error=data;
                          alert(error);
                        });
                        
                        
                        
                    };
                }
                                   
    );
    
    app.controller('controlpedidosSinNotificar',
                function($scope,$http){
                    
                    //$scope.lista=[1,2,3,4];
                    $scope.sucursal=0;
                    
                    $scope.lista=[];
                      
                    
                    $scope.idPedidoEsco;
                    $scope.options = [];
                     $scope.correctlySelected = $scope.options[0];
                    
                    this.consultarSucursales=function(){
                        //Arreglar URL
                        $http.get('rest/notificacion/').
                        //$http.get('https://eatfoodfaster.herokuapp.com/rest/pedidos/').
                        success(function(data, status, headers, config) {
                          // this callback will be called asynchronously
                          // when the response is available
                          $scope.options=data;
                          $scope.correctlySelected = $scope.options[0];  
                               
                        }).
                        error(function(data, status, headers, config) {
                          // called asynchronously if an error occurs
                          // or server returns response with an error status.
                          alert("error!");
                          
                          alert(status);
                          
                        });
                        
                    };
                    
                    
                    this.consultar=function(){
                     $scope.sucursal=$scope.correctlySelected.idSucursales;
                      
                        $http.get('rest/pedidos/sucursal/sinNotificar/'+$scope.sucursal).                       
                        success(function(data, status, headers, config) {
                          // this callback will be called asynchronously
                          // when the response is available
                          alert("Success!");
                          $scope.lista=data;
                        
                               
                        }).
                        error(function(data, status, headers, config) {
                          // called asynchronously if an error occurs
                          // or server returns response with an error status.
                          alert("error!");
                          
                          alert(status);
                          
                        });
                        
                        
                        
                    };
                    
                    $scope.estado={estado:"Pedido recibido en sucursal"};
                    
                    this.enviarPedido=function(idPedido){
                        $scope.idPedidoEsco=idPedido;
                        
                        $http.put('rest/notificacion/pedido/sucursal/'+$scope.idPedidoEsco,$scope.estado).                       
                        success(function(data, status, headers, config) {
                          // this callback will be called asynchronously
                          // when the response is available
                                alert("Success!");
                            this.consultar();
                               
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





