package br.com.learning.ws.controller;

import br.com.learning.ws.model.Estado;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.learning.ws.service.EstadoService;

@RestController
public class EstadoController {
	
	@Autowired
	EstadoService estadoService;
	
	@RequestMapping(
			method = RequestMethod.POST,
			value = "/estados",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Estado> cadastrarEstado(@RequestBody Estado estado){
		return new ResponseEntity<Estado>(estadoService.cadastar(estado), HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			value = "/estados",
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<Estado>> buscarTodosEstados(){
		return new ResponseEntity<Collection<Estado>>(estadoService.buscarTodos(), HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			value = "/estados/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Estado> buscarEstadoPorId(@PathVariable Integer id){
		Estado estado = estadoService.buscarPorId(id);
		if(estado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Estado>(estadoService.buscarPorId(id), HttpStatus.OK);
	}
}
