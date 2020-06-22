package br.com.learning.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.learning.ws.model.Cliente;

@Repository
public interface ClienteRespository extends JpaRepository<Cliente, Integer> {
	
}
