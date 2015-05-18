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
                //rutas felipe end º
                
                 // rutar fabian
                .when('/pedidosSinNotificar', {
                    templateUrl: 'eff_notificacion_pedidos.html',
                    controllerAs: 'cc',
                    controller:  'controlpedidosSinNotificar'
                })
                
                .when('/estado', {
                    templateUrl: 'eff_estado_pedidos.html',
                    controllerAs: 'cc',
                    controller:  'controlpedidos'
                })
                // rutas fabian end   
                
                // rutar Jenni
                .when('/crearCuenta', {
                    templateUrl: 'create_an_account.html',
                    controllerAs: 'cl',
                    controller:  'controlRegistro'
                })
                
                .when('/consultarSucursales', {
                    templateUrl: 'prueba.html',
                    controllerAs: 'suc',
                    controller:  'SucursalCrtl'
                });;
                // rutas jenni end 
                  

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
                    
                    this.notificarCliente=function(idPedido){
                        $scope.estado={estado:"Notificado a cliente"};  
                        $http.put('rest/notificacion/pedido/listo/'+idPedido,$scope.estado).                       
                        success(function(data, status, headers, config) {
                          // this callback will be called asynchronously
                          // when the response is available
                  
                        }).
                        error(function(data, status, headers, config) {
                          // called asynchronously if an error occurs
                          // or server returns response with an error status.
                          alert("error notificando el pedido");

                        });
                    };
                    
                    $scope.lista=[];
                    this.consultar=function(){
                        $http.get('rest/pedidos/').
                        success(function(data, status, headers, config) {
                          $scope.lista=data;
                        }).
                        error(function(data, status, headers, config) {
                          alert(status);
                        });  
                    };
                }                                   
    );
    /*---------------------------------------------------------------------*/
    
    app.controller('controlpedidosSinNotificar',
                function($scope,$http){
                    
                    $scope.sucursal=0;
                    
                    $scope.lista=[];
                      
                    
                    $scope.idPedidoEsco;
                    $scope.options = [];
                    $scope.correctlySelected = $scope.options[0];
                    
                    this.consultarSucursales=function(){
                        $http.get('rest/notificacion/').
                        success(function(data, status, headers, config) {
                          $scope.options=data;
                          $scope.correctlySelected = $scope.options[0];  
                               
                        }).
                        error(function(data, status, headers, config) {
                          alert(status);                          
                        });                        
                    };
                    
               
                    
                    this.consultar=function(){
                     $scope.sucursal=$scope.correctlySelected.idSucursales;
                      
                        $http.get('rest/pedidos/sucursal/sinNotificar/'+$scope.sucursal).                       
                        success(function(data, status, headers, config) {
                          $scope.lista=data;
                        }).
                        error(function(data, status, headers, config) {
                          alert("error!");
                          alert(status);                          
                        });
                    };
                    
                    
                    
                    this.enviarPedido=function(idPedido){
                        $scope.estado={estado:"Pedido recibido en sucursal"};
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
    app.controller('controlRegistro',
                function($scope,$http){
                    //crear un controlador para registro
                    this.detalleCliente={
                     correoCliente:'',
                     contrasena:'',
                     nombre: '',
                     apellido: '',
                     celular: ''
                    };

            
                    this.registrar=function(){
                    //accion
                    //alert(detalleCliente.correoCliente + detalleCliente.nombre +'holiiii');
                    $http.post('rest/clientes/', this.detalleCliente).
                    success(function (data, status, headers, config) {
                        alert('Registro Exitoso!');
                    }).
                    error(function (data, status, headers, config) {
                        alert('error al regisrar!');
                    });
                };
            });
    
    /*---------------------------------------------------------------------*/
    app.controller('SucursalCrtl',
        function($scope , $http , $location){
            
            $scope.seleccion;
            $scope.plazoletas = [];
            $scope.franquicias = [];
            $scope.sucursales=[];
            
            this.cargarDatos =  function(){
                this.cargarFranquicias();
                this.cargarPlazoletas();
            }
            
             this.cargarPlazoletas =  function(){
               
                    $http.get( 'rest/plazoleta')
                  .success(function (data, status, headers, config) {
                      $scope.plazoletas =  data;
                  })
                  .error(function (data, status, headers, config) {
                      alert('error  consultado plazoletas!') });
            }
            
            this.cargarFranquicias =  function(){
                
                   $http.get('rest/franquicia')
                  .success(function (data, status, headers, config) {
                      $scope.franquicias =  data;
                  })
                  .error(function (data, status, headers, config) {
                      alert('error  consultado franquicias!') });
            }
            
            this.franquiciaSeleccionada =  function(franquicia){
                   $scope.seleccion= franquicia.idFranquicia;
                   $http.get('rest/franquicia/'+ franquicia.idFranquicia)
                  .success(function (data, status, headers, config) {
                      $scope.sucursales =  data;
                  })
                  .error(function (data, status, headers, config) {
                      alert('error  consultado sucursales por franquicia!') });
            }
            
           this.plazoletaSeleccionada =  function(plazoleta){
                   $scope.seleccion =  plazoleta.id.idPlazoletaComidas + '-' + 
                        plazoleta.id.ciudad;
                
                   $http.get('rest/plazoleta/'+ plazoleta.id.idPlazoletaComidas)
                  .success(function (data, status, headers, config) {
                      $scope.sucursales =  data;
                  })
                  .error(function (data, status, headers, config) {
                      alert('error  consultado sucursales por Centro Comercial!') });
            }
        });

})();





