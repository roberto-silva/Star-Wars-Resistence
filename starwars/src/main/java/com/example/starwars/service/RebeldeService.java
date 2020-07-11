package com.example.starwars.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.starwars.models.Localizacao;
import com.example.starwars.models.Rebelde;
import com.example.starwars.models.Recurso;
import com.example.starwars.repository.RebeldeRepository;

@Service
public class RebeldeService {
	
	@Autowired
	private RebeldeRepository repository;
	
	@Autowired
	private RecursoService recursoService;
	
	@Autowired
	private LocalizacaoService localizacaoService;
	
	public List<Rebelde> findAll() {
		ArrayList<Rebelde> rebeldes = (ArrayList<Rebelde>) this.repository.findAll();
		ArrayList<Rebelde> rebeldesNaoTraidores = (ArrayList<Rebelde>) this.repository.findAll();
		for (Rebelde rebelde : rebeldes) {
			if(!rebelde.isTraidor()) {
				rebeldesNaoTraidores.add(rebelde);
			}
		}
		return rebeldesNaoTraidores;
	}

	public Rebelde findById(long id) {
		return this.repository.findById(id).get();
	}
	
	public Rebelde save(Rebelde rebelde) {
		if (rebelde.getInventario().size() > 0) {
			ArrayList<Recurso> inventario = new ArrayList<Recurso>();
			for (Recurso recurso : rebelde.getInventario()) {
					Recurso recursoNovo = recurso;
					this.repository.save(rebelde);
					recursoNovo.setRebelde(rebelde);					
					inventario.add(this.recursoService.save(recursoNovo));
			}
			rebelde.setInventario(inventario);
		}
		if (rebelde.getLocalizacao() != null) {
			rebelde.setLocalizacao(this.localizacaoService.save(rebelde.getLocalizacao()));
		}
		return this.repository.save(rebelde);
	}

	public ResponseEntity<?> delete(Long id) {
		Optional<Rebelde> rebelde = repository.findById(id);
		if(!rebelde.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			this.repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	public ResponseEntity<?> update(Long id, Rebelde rebelde ) {
		Optional<Rebelde> rebeldeSemEdicao = repository.findById(id);
		if(!rebeldeSemEdicao.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			rebelde.setId(rebeldeSemEdicao.get().getId());
			return new ResponseEntity<Rebelde>(repository.save(rebelde), HttpStatus.OK);
		}
	}

	public ResponseEntity<?> updateLocalizacao(Long id, Localizacao localizacao) {
		Localizacao novaLocalizacao = this.localizacaoService.save(localizacao);
		Optional<Rebelde> rebelde = repository.findById(id);
		if(!rebelde.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			rebelde.get().setLocalizacao(novaLocalizacao);
			return new ResponseEntity<Rebelde>(repository.save(rebelde.get()), HttpStatus.OK);
		}
	}

	public ResponseEntity<?> reporteRebelde(Long id) {
		Optional<Rebelde> rebelde = repository.findById(id);
		if(!rebelde.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			Rebelde rebeldeReportador = rebelde.get();
			rebeldeReportador.addReportes();
			if(rebeldeReportador.getReportes() >= 3) {
				rebeldeReportador.setTraidor(true);
			}
			return new ResponseEntity<Rebelde>(repository.save(rebeldeReportador), HttpStatus.OK);
		}
	}	
	public ResponseEntity<?> trocaEntreRebeldes(Long id, Long idTroca, Long idRecurso1, Long idRecurso2) {

		if(this.testaTroca(id, idTroca) == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			Optional<Rebelde> rebelde1 = repository.findById(id);
			Optional<Rebelde> rebelde2 = repository.findById(id);
			Recurso recursoTemp1 = this.recursoService.findById(idRecurso1);
			Recurso recursoTemp2 = this.recursoService.findById(idRecurso2);
			rebelde1.get().getInventario().remove(this.recursoService.findById(idRecurso1));
			rebelde1.get().getInventario().add(recursoTemp2);
			rebelde2.get().getInventario().remove(this.recursoService.findById(idRecurso2));
			rebelde2.get().getInventario().add(recursoTemp1);
			this.update(id, rebelde1.get());
			this.update(idTroca, rebelde2.get());
			return new ResponseEntity<Rebelde>(HttpStatus.OK);
		}
	}
	
	public boolean testaTroca(Long id, Long idTroca) {
		Optional<Rebelde> rebelde1 = repository.findById(id);
		Optional<Rebelde> rebelde2 = repository.findById(id);
		if(rebelde1.get().isTraidor() || rebelde2.get().isTraidor() ) {
			return false;
		}else {
			return true;
		}
	}
}
