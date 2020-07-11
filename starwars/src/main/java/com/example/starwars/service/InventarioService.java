package com.example.starwars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.starwars.models.Inventario;
import com.example.starwars.repository.InventarioRepository;

public class InventarioService {
	@Autowired
	private InventarioRepository repository;
	
	public List<Inventario> findAll() {
		return this.repository.findAll();
	}

	public Inventario findById(long id) {
		return this.repository.findById(id).get();
	}
}