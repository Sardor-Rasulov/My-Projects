package com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.Dairy;
import com.example.project.entity.Meat;
import com.example.project.repository.MeatReapository;

@Service
public class MeatService {

	@Autowired
	public MeatReapository mRepo;
	
	/* Add Product */
	public Meat addMeat(Meat meat) {
		return mRepo.save(meat);
	}
	
	/* Get All Dairy */
	public Iterable<Meat> getAllMeatProducts(){
		return mRepo.findAll();
	}
	
	/* Get product by it's id */	
	public Meat getById(Long id) {
		return mRepo.findById(id).get();
	}
	
	/* Update product */
	public void updateQuantity(Meat productQuantity) {
		mRepo.save(productQuantity);
	}
	
}
