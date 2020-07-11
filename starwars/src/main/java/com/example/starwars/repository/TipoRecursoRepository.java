package com.example.starwars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.starwars.models.Recurso;
import com.example.starwars.models.TipoRecurso;

public interface TipoRecursoRepository extends JpaRepository<TipoRecurso, Long>{
	public Recurso findById(Integer id);
}
