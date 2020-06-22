package br.com.learning.ws.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.learning.ws.model.Estado;
import br.com.learning.ws.repository.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	EstadoRepository estadoRepository;

	public Estado cadastar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public Collection<Estado> buscarTodos(){
		return estadoRepository.findAll();
	}
	
	public Estado buscarPorId(Integer id) {
		return estadoRepository.findById(id).orElse(null);
	}
}
