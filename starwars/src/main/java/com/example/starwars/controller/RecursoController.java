package com.example.starwars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.starwars.models.Recurso;
import com.example.starwars.service.RecursoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/starwars")
public class RecursoController {
	
	@Autowired
	private RecursoService service;
	
	@GetMapping("/recursos")
	public List<Recurso> listarRebeldes(){
		return this.service.findAll();
	}
	
	@GetMapping("/recursos/{id}")
	public Recurso getRebeldeById(@PathVariable(value="id") Long id) {
		return this.service.findById(id);
	}
}
