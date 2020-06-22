// Criação do controller
// O scopo é do html, acessa via Angular
appCliente.controller("clienteController", function($scope, $http){
/*
	$scope.nome = "João";
	$scope.sobreNome = "da Silva";
*/
	$scope.clientes =[];
	
	$scope.cliente = {};
	
	carregarClientes = function(){
		
		// Token para a API
		// Depois troquei para o Interceptor
		//token = localStorage.getItem("userToken");
		//$http.defaults.headers.common.Authorization = 'Bearer ' + token ;
		
		//console.log($http.defaults.headers.common.Authorization);
		
		$http({method: "GET", url: "http://localhost:8081/admin/clientes"})
		.then(function (response){
			$scope.clientes = response.data
		}, function(response){});
		$http({method: "GET", url: "http://localhost:8081/estados"})
		.then(function (response){
			$scope.estados = response.data
		}, function(response){});
	};
	
	carregarClientes();
	
	$scope.salvarCliente = function(){
		
		if($scope.frmCliente.$valid){
			$http({method:"POST", url: "http://localhost:8081/admin/clientes", data: $scope.cliente})
			.then(function(response){
				//$scope.clientes.push(response.data);
				carregarClientes();
				$scope.cancelarAlteracao();
				$scope.frmCliente.$setPristine(true);
			},function(response){})
		}
		else{
			window.alert("Dados inválidos!")
		}
		
		
	};
	
	$scope.excluirCliente = function(cliente){
		$http({method:"DELETE", url: "http://localhost:8081/admin/clientes/" + cliente.id})
		.then(function(response){
			var pos =$scope.clientes.indexOf(cliente);
			$scope.clientes.splice(pos,1);
			
			
		},function(response){})
		
	};
	
	$scope.alterarCliente = function(cliente){
		$scope.frmCliente.$dirty = true;
		$scope.cliente = angular.copy(cliente);
		console.log(cliente)
	};
	
	$scope.cancelarAlteracao = function(){
		$scope.cliente = {};
		$scope.frmCliente.$setPristine(true);
	};
	
})