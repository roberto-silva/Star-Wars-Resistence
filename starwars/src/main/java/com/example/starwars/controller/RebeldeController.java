package com.example.starwars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.starwars.models.Rebelde;
import com.example.starwars.service.RebeldeService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/starwars")
public class RebeldeController {
	
	@Autowired
	private RebeldeService service;
	
	@GetMapping("/rebeldes")
	public List<Rebelde> listarRebeldes(){
		return this.service.findAll();
	}
	
	@GetMapping("/rebeldes/{id}")
	public Rebelde getRebeldeById(@PathVariable(value="id") Long id) {
		return this.service.findById(id);
	}
}
