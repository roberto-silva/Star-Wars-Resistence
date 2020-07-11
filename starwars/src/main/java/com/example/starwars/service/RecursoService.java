package com.example.starwars.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.starwars.models.Recurso;
import com.example.starwars.repository.RecursoRepository;

@Service
public class RecursoService {
	@Autowired
	private RecursoRepository repository;
	
	@Autowired 
	private TipoRecursoService tipoRecursoService;
	
	
	public List<Recurso> findAll() {
		return this.repository.findAll();
	}

	public Recurso findById(long id) {
		return this.repository.findById(id).get();
	}
	
	public Recurso save(Recurso recurso) {
		if (recurso.getTipo().getId() != null) {
			recurso.setTipo(this.tipoRecursoService.findById(recurso.getTipo().getId()));
		}
		return this.repository.save(recurso);
	}

	public ResponseEntity<?> delete(Long id) {
		Optional<Recurso> recurso = repository.findById(id);
		if(!recurso.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			this.repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	public ResponseEntity<?> update(Long id, Recurso recurso ) {
		Optional<Recurso> recursoSemEdicao = repository.findById(id);
		if(!recursoSemEdicao.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			recurso.setId(recursoSemEdicao.get().getId());
			return new ResponseEntity<Recurso>(repository.save(recurso), HttpStatus.OK);
		}
	}
}
