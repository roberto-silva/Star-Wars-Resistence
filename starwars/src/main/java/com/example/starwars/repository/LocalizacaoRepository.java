package com.example.starwars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.starwars.models.Localizacao;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {
	public Localizacao findById(Integer id);

}
