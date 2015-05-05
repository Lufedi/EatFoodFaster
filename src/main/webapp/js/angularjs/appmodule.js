(function () {
    var app = angular.module('eff', ['ngRoute']);



app.config(function ($routeProvider) {
        $routeProvider

                // rutas felipe
                .when('/consultarproductos', {
                    templateUrl: 'eff_consultar_productos.html',
                    controllerAs: 'prd',
                    controller:  'ProductoCrtl'
                })
                .when('/foodcourts', {
                    templateUrl: 'eff_plazoletas_comidas.html'

                })
                
                //rutas felipe end 
                
                 // rutar fabian
                .when('/pedidosSinNotificar', {
                    templateUrl: 'eff_notificacion_pedidos.html'
                })
                
                .when('/estado', {
                    templateUrl: 'eff_estado_pedidos.html'
                });;
                // rutas fabian end       
                  

    });
    
    
    app.controller('test' ,  
        function($scope){
            
            this.test =  function(){
                alert('test');
            }
        }
    );
           
    app.controller('ProductoCrtl',
        function($scope , $http , $location){
            
            //var port = 'http://localhost:8084/EatFoodFaster/';
            $scope.filtro = false;
            $scope.seccion;
            $scope.plazoletaSelected;
            $scope.franquiciaSelected;
            $scope.productos = [];
            $scope.plazoletas = [];
            $scope.franquicias = [];
            //$scope.txt = 'controladorfuncionando';

            this.buscar =  function(){
                alert('buscar');
            };

            this.buscarProductos =  function(nombre ,  plazoleta ,  franquicia){
               
               
               //set path
               
               var url ;
               if(nombre != null){
                   if( plazoleta != null){
                       url = 'rest/productos/' + 
                               plazoleta.id.idPlazoletaComidas+'/'+plazoleta.id.ciudad +'/'+nombre;
                   }else if(franquicia != null){
                       url =  'rest/productos/' + franquicia.idFranquicia + '/' + nombre;
                   }else{
                       url = 'rest/productos/' + nombre;
                   }
                   
               }
               console.log(url);
               
               
               
               
              $http.get(url)
                  .success(function (data, status, headers, config) {
                      //  alert('success'  +  data);
                      $scope.productos =  data;//adf
                  })
                  .error(function (data, status, headers, config) {
                      alert('error  consultado productos!') });

            };
            
            this.obtenerDatos =  function(){
                this.obtenerFranquicias();
                this.obtenerPlazoletas();
            }
            
            this.obtenerPlazoletas =  function(){
               
                    $http.get( 'rest/plazoleta')
                  .success(function (data, status, headers, config) {
                      $scope.plazoletas =  data;
                  })
                  .error(function (data, status, headers, config) {
                      alert('error  consultado plazoletas!') });
            }
            
            this.obtenerFranquicias =  function(){
                
                   $http.get('rest/franquicia')
                  .success(function (data, status, headers, config) {
                      $scope.franquicias =  data;
                  })
                  .error(function (data, status, headers, config) {
                      alert('error  consultado franquicias!') });
            }
            
            this.seleccionarPlazoleta =  function(plazoleta){
                $scope.seccion =  plazoleta.id.idPlazoletaComidas + '-' + 
                        plazoleta.id.ciudad;
                $scope.plazoletaSelected =  plazoleta;
                $scope.franquiciaSelected =  null;
                $scope.filtro =  true;
                $scope.productos = [];
                 /*$http.get('http://localhost:8084/EatFoodFaster/rest/plazoleta/' 
                         + idPlazoleta.idPlazoletaComidas)
                  .success(function (data, status, headers, config) {
                      // alert('success'  +  'http://localhost:8084/EatFoodFaster/rest/plazoleta/' 
                       //  + idPlazoleta.idPlazoletaComidas);
                      $scope.sucursales =  data;//adf
                  })
                  .error(function (data, status, headers, config) {
                      alert('error  consultado productos!') });*/
            }
            this.seleccionarFranquicia =  function(franquicia){
                
                $scope.plazoletaSelected = null;
                $scope.franquiciaSelected = franquicia;
                $scope.seccion =  franquicia.idFranquicia;
                $scope.filtro  =  false;
                $scope.productos = [];
            }

        }
    );
    

    /*---------------------------------------------------------------------*/
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
    
    /*---------------------------------------------------------------------*/

})();





