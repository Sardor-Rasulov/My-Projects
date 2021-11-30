package com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.FruitsAndVegetables;
import com.example.project.repository.FruitsAndVegsRepository;

@Service
public class FruitsAndVegsService {

	@Autowired
	public FruitsAndVegsRepository fvRepo;
	
	/* Add Product */
	public FruitsAndVegetables addFruitsAndVegs(FruitsAndVegetables fAndV) {
		return fvRepo.save(fAndV);
		
	}
	
	/* Get All Fruits & Vegs */
	public Iterable<FruitsAndVegetables> getAllFruitsAndVegs(){
		return fvRepo.findAll();
	}
	
	/* Get product by it's id */
	public FruitsAndVegetables getById(Long id) {
		return fvRepo.findById(id).get();
	}
	
	/* Update product */
	public void updateQuantity(FruitsAndVegetables productQuantity) {
		fvRepo.save(productQuantity);
	}
	
	/* Delete expired product */
	public void deleteExpiredProduct(Long id) {
		fvRepo.deleteById(id);
		
	}
}
