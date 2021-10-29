package com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.Meat;
import com.example.project.repository.MeatReapository;

@Service
public class MeatService {

	@Autowired
	public MeatReapository mRepo;
	
	public Meat addMeat(Meat meat) {
		return mRepo.save(meat);
	}
	

	public Iterable<Meat> getAllMeatProducts(){
		return mRepo.findAll();
	}
	
}
