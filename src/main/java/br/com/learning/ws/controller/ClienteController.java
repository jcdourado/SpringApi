package br.com.learning.ws.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.learning.ws.model.Cliente;
import br.com.learning.ws.service.ClienteService;

@RestController
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	// End points
	@RequestMapping(
			method = RequestMethod.POST, value="/admin/clientes",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
		
		try {
			Cliente clienteCadastrado = clienteService.cadastrar(cliente);
			return new ResponseEntity<Cliente>(clienteCadastrado, HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value="/admin/clientes",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Cliente>> buscarTodosClientes() {
		Collection<Cliente> clientesBuscados = (Collection<Cliente>) clienteService.buscarTodos();
		
		return new ResponseEntity<Collection<Cliente>>(clientesBuscados, HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			value="/admin/clientes/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Optional<Cliente>> buscarClientePorId(@PathVariable Integer id) {
		Optional<Cliente> cliente = clienteService.buscarPorId(id);
		if(cliente.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
				
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.DELETE,
			value="/admin/clientes/{id}")
	public ResponseEntity<Cliente> excluirCliente(@PathVariable Integer id) {
		
		Optional<Cliente> clienteEncontrado = clienteService.buscarPorId(id);
		
		if (clienteEncontrado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		clienteService.excluir(clienteEncontrado.get());
		return new ResponseEntity<>(HttpStatus.OK);
	} 
	
	@RequestMapping(
			method = RequestMethod.PUT,
			value="/admin/clientes/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> alterarCliente(@RequestBody Cliente cliente) {
		
		Cliente clienteAlterado = clienteService.alterar(cliente);
		
		return new ResponseEntity<Cliente>(clienteAlterado,HttpStatus.OK);
	} 
	

	
	// End points
	@RequestMapping(
			method = RequestMethod.POST, value="/clientes",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> correctClienteRoute(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);		
	}
}
