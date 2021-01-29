

var app = angular.module("app", []);

app.controller("facturaController", function IndexController($scope,$http) {
    
    $scope.texto = "Holaaaa Mundo 333";
    
    $scope.inicio = function() { 
    	
    	//alert("entro");
		$http({
			url : "/factura/listar",
			method : "GET"  
		}).success(function(data, status) {
 			
			console.log(data);
			$scope.registro = data; 
			
		});
	}
	
});
