

var app = angular.module("app", []);

app.controller("facturaController", function IndexController($scope,$http) {
    
    $scope.texto = "Holaaaa Mundo 333";
    $scope.registro ="";
    
    $scope.inicio = function() { 
    	
    	//alert("entro");
		$http({
			url : "/factura/listar",
			method : "GET"  
		}).success(function(data, status) {
 			
			$scope.registro = data; 
			
		});
	}
	
	$scope.getFactura = function() { 
    	
    	$scope.getDetalle();
    	
    	//alert("entro");
		$http({
			url : "/factura",
			method : "POST",
			params: {codigo:1} 
			 
		}).success(function(data, status) {
 			
			console.log(data);
			$scope.registro = data;   
			$scope.r1 = data[0];
			//alert($scope.r1.codigo);
			console.log($scope.r1.codigo, $scope.r1.nombre, $scope.r1.apellido);
			 
		});
	}
	
	$scope.getDetalle = function() { 
    	
    	//alert("entro");
		$http({
			url : "/detalle",
			method : "POST",
			params: {codigo:1} 
			 
		}).success(function(data, status) {
 			
			console.log(data);
			$scope.rd = data;   
			 
		});
	}
	
	$scope.getStock = function() { 
    	
    	bus =  document.getElementById('txtBuscar').value; console.log(bus);
    	
    	//alert("entro");
		$http({
			url : "/stock",
			method : "POST",
			params: {buscar:bus} 
			 
		}).success(function(data, status) {
 			
			console.log(data);
			$scope.stock = data;
			 
		});
	}
	
	
	
	$scope.addDetalle = function( inv, precio, cantidad ) { 
    	
    	can =  document.getElementById('cantidad'+inv).value; console.log(can);
    	 
    	//alert($scope.r1.codigo + ' ' + inv);
		$http({
			url : "/insertarDetalle",
			method : "POST",
			params: {inv:inv,
					precio:precio,
					cantidad:can,
					fac:$scope.r1.codigo} 
			 
		}).success(function(data, status) {
 			
 			$scope.getDetalle();
			console.log(data);
			$scope.addDetalleResultado = data;
			  
			 
		});
	}
	
	$scope.borrarDetalle = function( inv ) { 
    	
    	//alert('hola');
    	
		$http({
			url : "/borrarDetalle",
			method : "POST",
			params: {
					fac:$scope.r1.codigo,
					inv:inv
					} 
			 
		}).success(function(data, status) {
 			
 			$scope.getDetalle();
			console.log(data); 
			
		});
	}
	
});
