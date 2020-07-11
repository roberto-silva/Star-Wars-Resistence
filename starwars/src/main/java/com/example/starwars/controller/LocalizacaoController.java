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

import com.example.starwars.models.Localizacao;
import com.example.starwars.service.LocalizacaoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/starwars")
public class LocalizacaoController {
	
	@Autowired
	private LocalizacaoService service;
	
	//Enpoint listar as localizações cadastradas
	@GetMapping("/localizacao")
	public List<Localizacao> listarRebeldes(){
		return this.service.findAll();
	}
	
	//Enpoint listar uma localização pelo id
	@GetMapping("/localizacao/{id}")
	public Localizacao getRebeldeById(@PathVariable(value="id") Long id) {
		return this.service.findById(id);
	}
	
	//Enpoint cadastrar uma localização
	@RequestMapping(value="/localizacao", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Localizacao localizacao) {
		Localizacao novaLocalizacao = this.service.save(localizacao);
        return ResponseEntity.ok().body(novaLocalizacao);
    }
	
	//Enpoint editar uma localização
	@PutMapping("/localizacao/edit/{id}")
	public ResponseEntity<?> edit(@PathVariable(value="id") Long id, 
			@RequestBody @Validated Localizacao localizacao){
		return this.service.update(id, localizacao);
		
	}
	
	//Enpoint deletar uma localização
	@DeleteMapping("/localizacao/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id") Long id){
		return this.service.delete(id);
	}
}
