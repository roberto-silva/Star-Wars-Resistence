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

import com.example.starwars.models.TipoRecurso;
import com.example.starwars.service.TipoRecursoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/starwars")
public class TipoRecursoController {
	
	@Autowired
	private TipoRecursoService service;
	
	@GetMapping("/recursos/tipos")
	public List<TipoRecurso> listarRebeldes(){
		return this.service.findAll();
	}
	
	@GetMapping("/recursos/tipos/{id}")
	public TipoRecurso getRebeldeById(@PathVariable(value="id") Long id) {
		return this.service.findById(id);
	}
	
	@RequestMapping(value="/recursos/tipos", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody TipoRecurso tipoRecurso) {
		TipoRecurso novoTipoRecurso = this.service.save(tipoRecurso);
        return ResponseEntity.ok().body(novoTipoRecurso);
    }
	@PutMapping("/recursos/tipos/edit/{id}")
	public ResponseEntity<?> edit(@PathVariable(value="id") Long id, 
			@RequestBody @Validated TipoRecurso tipoRecurso){
		return this.service.update(id, tipoRecurso);
		
	}
	@DeleteMapping("/recursos/tipos/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id") Long id){
		return this.service.delete(id);
	}
}
