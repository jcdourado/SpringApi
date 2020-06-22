package br.com.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import br.com.learning.ws.controller.TokenFilter;

@SpringBootApplication
public class ApiwebApplication {
	
	
	// Muito importante para o filtro
	@Bean
	public FilterRegistrationBean<TokenFilter> filtroJwt(){
		FilterRegistrationBean<TokenFilter> frb = new FilterRegistrationBean<TokenFilter>();
		frb.setFilter(new TokenFilter());
		frb.addUrlPatterns("/admin/*");
		return frb;
	}
	

	public static void main(String[] args) {
		SpringApplication.run(ApiwebApplication.class, args);
	}

}
