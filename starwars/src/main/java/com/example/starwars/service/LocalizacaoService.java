package com.example.starwars.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.starwars.models.Localizacao;
import com.example.starwars.repository.LocalizacaoRepository;

@Service
public class LocalizacaoService {
	@Autowired
	private LocalizacaoRepository repository;
	
	public List<Localizacao> findAll() {
		return this.repository.findAll();
	}

	public Localizacao findById(long id) {
		return this.repository.findById(id).get();
	}
	public Localizacao save(Localizacao inventario) {
		return this.repository.save(inventario);
	}

	public ResponseEntity<?> delete(Long id) {
		Optional<Localizacao> tipoRecurso = repository.findById(id);
		if(!tipoRecurso.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			this.repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	public ResponseEntity<?> update(Long id, Localizacao localizacao ) {
		Optional<Localizacao> localizacaoSemEdicao = repository.findById(id);
		if(!localizacaoSemEdicao.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			localizacao.setId(localizacaoSemEdicao.get().getId());
			return new ResponseEntity<Localizacao>(repository.save(localizacao), HttpStatus.OK);
		}
	}
}
