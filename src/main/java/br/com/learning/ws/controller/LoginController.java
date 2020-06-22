package br.com.learning.ws.controller;

import java.security.Key;
import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.learning.ws.model.Usuario;
import br.com.learning.ws.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@RestController
public class LoginController {
	
	@Autowired
	private UsuarioService usuarioservice;
	
	public static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	@RequestMapping(
			value = "/autenticar",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST
	)
	public LoginResponse autenticar(@RequestBody Usuario usuario) throws ServletException {
		if(usuario.getNome() == null || usuario.getSenha() == null) {
			throw new ServletException("Nome e senha obrigatório.");
		}
		
		Usuario usuAutenticado = usuarioservice.buscarPorNome(usuario.getNome());
		
		if (usuAutenticado == null) {
			throw new ServletException("Usuário não encontrado");
		}
		
		if (!usuAutenticado.getSenha().equals(usuario.getSenha())) {
			throw new ServletException("Usuário ou senha inválido");
		}
		//return new ResponseEntity<Usuario>(usuAutenticado, HttpStatus.OK);
		
		// Devemos retornar um Token
		// Token: {token ioajsdiofjdsifjdpofd}
		//Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		
		String token = Jwts.builder()
				.setSubject(usuAutenticado.getNome())
				.signWith(key)
				// 1 é o minuto, 60 é de milisegundos e 1000 tb
				.setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
				.compact();
		
		return new LoginResponse(token);
		
	}
	
	private class LoginResponse{
		public String token;
		
		public LoginResponse(String token) {
			this.token = token;
		}
	}
}
