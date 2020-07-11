package com.example.starwars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.starwars.models.Recurso;
import com.example.starwars.repository.RecursoRepository;

@Service
public class RecursoService {
	@Autowired
	private RecursoRepository repository;
	
	public List<Recurso> findAll() {
		return this.repository.findAll();
	}

	public Recurso findById(long id) {
		return this.repository.findById(id).get();
	}
}
