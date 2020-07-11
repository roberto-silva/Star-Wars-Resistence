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

import com.example.starwars.models.Recurso;
import com.example.starwars.service.RecursoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/starwars")
public class RecursoController {
	
	@Autowired
	private RecursoService service;
	//Enpoint listar recursos
	@GetMapping("/recursos")
	public List<Recurso> listarRebeldes(){
		return this.service.findAll();
	}
	//Enpoint listar um recurso pelo id
	@GetMapping("/recursos/{id}")
	public Recurso getRebeldeById(@PathVariable(value="id") Long id) {
		return this.service.findById(id);
	}
	//Enpoint cadastrar um recursos
	@RequestMapping(value="/recursos", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Recurso recurso) {
		Recurso novoRecurso = this.service.save(recurso);
        return ResponseEntity.ok().body(novoRecurso);
    }
	//Enpoint editar um recurso
	@PutMapping("/recursos/edit/{id}")
	public ResponseEntity<?> edit(@PathVariable(value="id") Long id, 
			@RequestBody @Validated Recurso localizacao){
		return this.service.update(id, localizacao);
		
	}
	//Enpoint deletar um recursos
	@DeleteMapping("/recursos/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id") Long id){
		return this.service.delete(id);
	}
}
