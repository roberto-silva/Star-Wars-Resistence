package com.example.starwars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.starwars.models.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Long>{
	public Inventario findById(Integer id);
}