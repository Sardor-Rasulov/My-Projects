package com.example.project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.Dairy;
import com.example.project.entity.FruitsAndVegetables;
import com.example.project.repository.DairyRepository;

@Service
public class DairyService {
	
	@Autowired
	public DairyRepository dRepo;
	
	
	/* Add Product */
	public Dairy addProduct(Dairy dairy) {
		return dRepo.save(dairy);
	}
	
	/* Get product by it's id */	
	public Dairy getById(Long id) {
		return dRepo.findById(id).get();
	}
	
	/* Update product */
	public void updateQuantity(Dairy productQuantity) {
		dRepo.save(productQuantity);
	}
	
	/* Get All Dairy */
	public Iterable<Dairy> getAllDairyProducts(){
		return dRepo.findAll();
	}
}
