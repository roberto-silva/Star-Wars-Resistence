package com.example.starwars.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.starwars.models.Rebelde;
import com.example.starwars.models.Recurso;
import com.example.starwars.repository.RebeldeRepository;

@Service
public class RebeldeService {
	
	@Autowired
	private RebeldeRepository repository;
	
	@Autowired
	private RecursoService recursoService;
	
	@Autowired
	private LocalizacaoService localizacaoService;
	
	public List<Rebelde> findAll() {
		return this.repository.findAll();
	}

	public Rebelde findById(long id) {
		return this.repository.findById(id).get();
	}
	
	public Rebelde save(Rebelde rebelde) {
		if (rebelde.getInventario() != null) {
			ArrayList<Recurso> inventario = new ArrayList<Recurso>();
			for (Recurso recurso : rebelde.getInventario()) {
				this.recursoService.save(recurso);
				inventario.add(recurso);
			}
			rebelde.setInventario(inventario);
		}
		if (rebelde.getLocalizacao() != null) {
			rebelde.setLocalizacao(this.localizacaoService.save(rebelde.getLocalizacao()));
		}
		return this.repository.save(rebelde);
	}

	public ResponseEntity<?> delete(Long id) {
		Optional<Rebelde> rebelde = repository.findById(id);
		if(!rebelde.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			this.repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	public ResponseEntity<?> update(Long id, Rebelde rebelde ) {
		Optional<Rebelde> rebeldeSemEdicao = repository.findById(id);
		if(!rebeldeSemEdicao.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			rebelde.setId(rebeldeSemEdicao.get().getId());
			return new ResponseEntity<Rebelde>(repository.save(rebelde), HttpStatus.OK);
		}
	}
}
