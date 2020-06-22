package br.com.learning.ws.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.learning.ws.model.Cliente;
import br.com.learning.ws.repository.ClienteRespository;

@Service
public class ClienteService {

	@Autowired
	ClienteRespository clienteRepository;
		
	public Cliente cadastrar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Collection<Cliente> buscarTodos(){
		// Retorna a Collection
		return (Collection<Cliente>) clienteRepository.findAll();
	}
	
	public void excluir(Cliente cliente) {
		clienteRepository.delete(cliente);
	}
	
	public Optional<Cliente> buscarPorId(Integer id) {
		return clienteRepository.findById(id);
	}
	
	public Cliente alterar(Cliente cliente) {
		return clienteRepository.save(cliente);				
	}
}
