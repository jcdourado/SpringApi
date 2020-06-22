package br.com.learning.ws.controller;

import java.io.IOException;
import java.security.Key;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class TokenFilter extends GenericFilterBean{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// Request mais especifico		
		HttpServletRequest req = (HttpServletRequest) request;
		
		String header = req.getHeader("Authorization");
		
		if(header == null || !header.startsWith("Bearer ")) {
//			throw new ServletException("Token inexistente ou inválido");
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inexistente ou inválido");
		}
		
		String token = header.substring(7); // Extraindo somente a string do Token sem o Bearer
		System.out.println("token: " + token);
		
		// verificar se o token é valido
		//Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

		try {
			Jwts.parserBuilder().setSigningKey(LoginController.key).build().parseClaimsJws(token);
			chain.doFilter(request, response);
		}
		catch(JwtException e) {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
			//throw new ServletException(e.toString());
			//throw new ServletException("Token inválido");
		}
				
	}
	
}
