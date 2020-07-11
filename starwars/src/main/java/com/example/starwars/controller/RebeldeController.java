package com.example.starwars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value="/rebeldes", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Rebelde rebelde) {
		Rebelde novoRebelde = this.service.save(rebelde);
        return ResponseEntity.ok().body(novoRebelde);
    }
	@PutMapping("/rebeldes/{id}")
	public ResponseEntity<?> edit(@PathVariable(value="id") Long id, 
			@RequestBody @Validated Rebelde rebelde){
		return this.service.update(id, rebelde);
		
	}
	@DeleteMapping("/rebeldes/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id") Long id){
		return this.service.delete(id);
	}
}
