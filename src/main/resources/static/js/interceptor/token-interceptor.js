// Interceptador de requests

appCliente.factory("tokenInterceptor", function($q, $location){
	return {
		'request': function(config){
			config.headers.Authorization = "Bearer " + localStorage.getItem("userToken");
			
			return config;
		},
		// Resultados com 200
		'response': function(response){
			return response;
		},
		// Erros 400 e 500
		'responseError': function(rejection){
			console.log(rejection.status)
			if(rejection.status == 401){
				$location.path("/login");
			}
			
			return $q.reject(rejection);
		}
	};
});
