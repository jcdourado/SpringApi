appCliente.controller("estadoController",function($scope, $http){
	$http.get("http://localhost:8081/estados").then(
			function(response){
				$scope.estados = response.data;
			},function(response){
				console.log(response)
			},)
});