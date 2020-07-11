package com.example.starwars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.starwars.models.Localizacao;
import com.example.starwars.service.LocalizacaoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/starwars")
public class LocalizacaoController {
	
	@Autowired
	private LocalizacaoService service;
	
	@GetMapping("/localizacao")
	public List<Localizacao> listarRebeldes(){
		return this.service.findAll();
	}
	
	@GetMapping("/localizacao/{id}")
	public Localizacao getRebeldeById(@PathVariable(value="id") Long id) {
		return this.service.findById(id);
	}
}
