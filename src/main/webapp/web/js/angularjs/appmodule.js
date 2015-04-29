(function () {
    var app = angular.module('eff', ['ngRoute']);

    app.controller('ctrl1' ,  
        function($scope ,  $http){
            this.producto = {
                nombre:'camisa',
                idproducto:12,
                precio:13
            }
            
            
            this.registrar = function (api) {
               // alert("registrando un producto");
            
            $http.post('rest/products/' + api, this.producto)
                    .success(function (data, status, headers, config) {
                        alert('success');
                     })
                    .error(function (data, status, headers, config) {
                        alert('error!') });
            };
        }
    );
    
    app.controller('catalogoClr' ,
        function($scope , $http){
            $scope.productos = [];
            
            this.actualizar =  function(api){
                //alert('rest/products/' + api + '/');
                 $http.get('rest/products/' + api)
                    .success(function (data, status, headers, config) {
                     //  alert('success'  +  data);
                        $scope.productos =  data;//adf
                     })
                    .error(function (data, status, headers, config) {
                        alert('error!') });
            
            };
            
        }
    );
    app.config(function ($routeProvider) {
        $routeProvider

                // route for the about page
                .when('/consultarproductos', {
                    templateUrl: 'eff_consultar_productos.html'

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





