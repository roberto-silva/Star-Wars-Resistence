package com.example.starwars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.starwars.models.Recurso;

public interface RecursoRepository extends JpaRepository<Recurso, Long>{
	public Recurso findById(Integer id);
}
