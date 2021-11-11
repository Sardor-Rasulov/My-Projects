package com.example.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.entity.Dairy;
import com.example.project.entity.Meat;
import com.example.project.service.MeatService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value="/api/v1/")
public class MeatController {
	
	@Autowired
	public MeatService mService;
	
	@PostMapping("/addMeat")
	public Map<String, Object> addMeat(@RequestBody Meat meat) {
		
		Map<String, Object> res = new HashMap<>();
		
		try {
			
			//Check if meat section is full 
			ArrayList<Meat> getProducts = (ArrayList<Meat>) mService.getAllMeatProducts();
			float total = 0;
			for(Meat loopProducts : getProducts) {
				total = total + loopProducts.getProductQuantity();
			}
			
			/*
			 * Before adding, check if there is a place in the fridge
			 */			
			float sum = total + meat.getProductQuantity();
			if(sum <= 30) {
			Meat pMeat = mService.addMeat(meat);
			res.put("meat", pMeat);
			//if the request was fine status 0 will be sent to the client
			res.put("status", 0); 
			} else {
				res.put("status", "Meat Section is Full. \n" + " Please empty it first");
			}
		  
		}catch(Exception e) {
			res.put("status", 1);
			res.put("msg", "Somethig went wrong! \n There is an exception");
		}
		
		return res;
	}
	
	/* Update product quantity */
	@PostMapping("/updateMeatQuantity")
	public Map<String, Object> updateQuantity(@RequestParam (name="id") Long id,
											  @RequestParam (name="productQuantity") Float quantity){
		
		Map<String, Object> res = new HashMap<>(); 
		
		try {
			
			/* Get product by id */
			Meat meat = new Meat();
			meat = mService.getById(id);
			Float quantityInDb = meat.getProductQuantity();
			
			/*
			 * Check if amount which comes from user 
			 * is lesser than the amount in DB
			 */ 
			if(quantity < quantityInDb) {
				
				Float quntityRes = quantityInDb - quantity;
				
				meat.setProductQuantity(quntityRes);
				mService.updateQuantity(meat);
				
				res.put("fruitsAndVegs", meat);
				res.put("status", 0);

			} else {
				res.put("msg", "You don't have enough product");
			}
			
		}catch (Exception e) {
			
			res.put("status", 1);
			res.put("msg", "Somethig went wrong! \\n There is an exception");
		}
		
		return res;
	}

	
	/* Get All Meat */
	@GetMapping("/getAllMeat")
	public Iterable<Meat> getAllFruitsAndVegs() {
		return mService.getAllMeatProducts();
	}
}
