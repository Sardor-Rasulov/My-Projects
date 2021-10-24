package com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.FruitsAndVegetables;
import com.example.project.repository.FruitsAndVegsRepository;

@Service
public class FruitsAndVegsService {

	@Autowired
	public FruitsAndVegsRepository fvRepo;
	
	public FruitsAndVegetables addFruitsAndVegs(FruitsAndVegetables fAndV) {
		return fvRepo.save(fAndV);
		
	}
	
	public Iterable<FruitsAndVegetables> getAllFruitsAndVegs(){
		return fvRepo.findAll();
	}
}
