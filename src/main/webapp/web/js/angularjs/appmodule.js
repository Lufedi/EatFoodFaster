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
            $scope.productos = [];
            $scope.txt = 'controladorfuncionando';

            this.buscar =  function(){
                alert('buscar');
            };

            this.buscarProductos =  function(nombre){
               
              $http.get('http://localhost:8084/EatFoodFaster/rest/productos/' + nombre)
                  .success(function (data, status, headers, config) {
                      //  alert('success'  +  data);
                      $scope.productos =  data;//adf
                  })
                  .error(function (data, status, headers, config) {
                      alert('error  consultado productos!') });

            };

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





