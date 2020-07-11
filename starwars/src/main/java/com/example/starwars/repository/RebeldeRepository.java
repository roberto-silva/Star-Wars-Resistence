package com.example.starwars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.starwars.models.Rebelde;

public interface RebeldeRepository extends JpaRepository<Rebelde, Long> {
	public Rebelde findById(Integer id);

}
