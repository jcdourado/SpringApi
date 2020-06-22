// Criação do Modulo principal da aplicação
var appCliente = angular.module("appCliente", ['ngRoute']);


//$routeProvider -> rotas disponiveis
appCliente.config(function($routeProvider, $locationProvider){
	$routeProvider
	.when("/clientes", {
		templateUrl: "view/cliente.html",
		controller: "clienteController"
	})
	.when("/clientes/:clienteId", {
		templateUrl: "view/cliente-detalhe.html",
		controller: "clienteDetalheController"
	})
	.when("/cidades", {
		templateUrl: "view/cidade.html",
		controller: "cidadeController"
	})
	.when("/estados", {
		templateUrl: "view/estado.html",
		controller: "estadoController"
	})
	.when("/login", {
		templateUrl: "view/login.html",
		controller: "loginController"
	})
	.otherwise({redirectTo: "/"});
	
	// Habilita tirar o #
	$locationProvider.html5Mode(true);
	
});


appCliente.config(function($httpProvider){
	$httpProvider.interceptors.push('tokenInterceptor');
})