package com.example.starwars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.starwars.models.Rebelde;
import com.example.starwars.repository.RebeldeRepository;

@Service
public class RebeldeService {
	
	@Autowired
	private RebeldeRepository repository;
	
	public List<Rebelde> findAll() {
		return this.repository.findAll();
	}

	public Rebelde findById(long id) {
		return this.repository.findById(id).get();
	}
}
