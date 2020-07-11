package com.example.starwars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.starwars.models.Localizacao;
import com.example.starwars.repository.LocalizacaoRepository;

public class LocalizacaoService {
	@Autowired
	private LocalizacaoRepository repository;
	
	public List<Localizacao> findAll() {
		return this.repository.findAll();
	}

	public Localizacao findById(long id) {
		return this.repository.findById(id).get();
	}
}
