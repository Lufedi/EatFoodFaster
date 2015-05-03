(function () {
    var app = angular.module('eff', ['ngRoute']);

    app.controller('test' ,  
        function($scope){
            
            this.test =  function(){
                alert('test');
            }
        }
    );
   
    app.controller('ProductoCrtl',
        function($scope , $http , $location){
            
            var port = 'http://localhost:8084/EatFoodFaster/';
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
                       url = port + 'rest/productos/' + 
                               plazoleta.id.idPlazoletaComidas+'/'+plazoleta.id.ciudad +'/'+nombre;
                   }else if(franquicia != null){
                       url = port + 'rest/productos/' + franquicia.idFranquicia + '/' + nombre;
                   }else{
                       url = port + 'rest/productos/' + nombre;
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
               
                    $http.get(port + 'rest/plazoleta')
                  .success(function (data, status, headers, config) {
                      $scope.plazoletas =  data;
                  })
                  .error(function (data, status, headers, config) {
                      alert('error  consultado plazoletas!') });
            }
            
            this.obtenerFranquicias =  function(){
                
                   $http.get(port + 'rest/franquicia')
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
    app.config(function ($routeProvider) {
        $routeProvider

                // route for the about page
                .when('/consultarproductos', {
                    templateUrl: 'eff_consultar_productos.html',
                    controllerAs: 'prd',
                    controller:  'ProductoCrtl'
                })

                // route for the contact page
                .when('/foodcourts', {
                    templateUrl: 'eff_plazoletas_comidas.html'

                })
                
                .when('/autor' , {
                    templateUrl : 'autor.html'
                });
                       

    });



})();





