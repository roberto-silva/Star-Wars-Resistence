package com.example.starwars.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.starwars.models.TipoRecurso;
import com.example.starwars.repository.TipoRecursoRepository;

@Service
public class TipoRecursoService {
	@Autowired
	private TipoRecursoRepository repository;
	
	public List<TipoRecurso> findAll() {
		return this.repository.findAll();
	}

	public TipoRecurso findById(long id) {
		return this.repository.findById(id).get();
	}
	
	public TipoRecurso save(TipoRecurso tipoRecurso) {
		return this.repository.save(tipoRecurso);
	}

	public ResponseEntity<?> delete(Long id) {
		Optional<TipoRecurso> tipoRecurso = repository.findById(id);
		if(!tipoRecurso.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			this.repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	public ResponseEntity<?> update(Long id, TipoRecurso tipoRecurso ) {
		Optional<TipoRecurso> tipoRecursoSemEdicao = repository.findById(id);
		if(!tipoRecursoSemEdicao.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			tipoRecurso.setId(tipoRecursoSemEdicao.get().getId());
			return new ResponseEntity<TipoRecurso>(repository.save(tipoRecurso), HttpStatus.OK);
		}
	}
}
