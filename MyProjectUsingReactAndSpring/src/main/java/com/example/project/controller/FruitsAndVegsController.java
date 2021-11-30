package com.example.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.entity.FruitsAndVegetables;
import com.example.project.service.FruitsAndVegsService;


@RestController
@RequestMapping(value="/api/v1/")
@CrossOrigin(origins = "http://localhost:3001/")
public class FruitsAndVegsController {
	
	@Autowired
	public FruitsAndVegsService fAndvService;
	
	@PostMapping("/addFruitsAndVegs")
	public Map<String, Object> addMeat(@RequestBody FruitsAndVegetables fruitAndVegs) {
		
		Map<String, Object> res = new HashMap<>();
		
		try {
			//Check if meat section is full 
			ArrayList<FruitsAndVegetables> getFruitsAndVegs = (ArrayList<FruitsAndVegetables>) fAndvService.getAllFruitsAndVegs();
			float total = 0;
			for(FruitsAndVegetables loopProducts : getFruitsAndVegs) {
				total = total + loopProducts.getProductQuantity();
			}
			
			/*
			 * Before adding, check if there is a place in the fridge
			 */			
			float sum = total + fruitAndVegs.getProductQuantity();
			if(sum <= 30) {
			FruitsAndVegetables pFV = fAndvService.addFruitsAndVegs(fruitAndVegs);
			res.put("meat", pFV);
			//if the request was fine status 0 will be sent to the client
			res.put("status", 0); 
			} else {
				res.put("status", "Fruits/Vegitables Section is Full. \n" + " Please empty it first");
			}
			
		}catch(Exception e) {
			res.put("status", 1);
			res.put("msg", "Somethig went wrong! \n There is an exception");
		}
		
		return res;
	}
	
	//Get all fruits and vegetables
	@GetMapping("/getFruitsAndVegs")
	public Iterable<FruitsAndVegetables> getAllFruitsAndVegs() {
		return fAndvService.getAllFruitsAndVegs();
	}

	
	//Update product quantity
	@PostMapping("/updateFruitsAndVegsQuantity")
	public Map<String, Object> updateQuantity(@RequestParam (name="id") Long id,
											  @RequestParam (name="productQuantity") Float quantity){
		
		Map<String, Object> res = new HashMap<>(); 
		
		try {
			
			/* Get product by id */
			FruitsAndVegetables fruitsAndVegs = new FruitsAndVegetables();
			fruitsAndVegs = fAndvService.getById(id);
			Float quantityInDb = fruitsAndVegs.getProductQuantity();
			
			/*
			 * Check if amount which comes from user 
			 * is lesser than the amount in DB
			 */ 
			if(quantity < quantityInDb) {
				
				Float quntityRes = quantityInDb - quantity;
				
				fruitsAndVegs.setProductQuantity(quntityRes);
				fAndvService.updateQuantity(fruitsAndVegs);
				
				res.put("fruitsAndVegs", fruitsAndVegs);
				res.put("status", 0);

			} else {
				res.put("status", 3);
				res.put("msg", "You don't have enough product");
			}
			
		}catch (Exception e) {
			
			res.put("status", 1);
			res.put("msg", "Somethig went wrong! \\n There is an exception");
		}
		
		return res;
	}
	
	//Delete expired product		
	@GetMapping("/deleteProduct")
	public ResponseEntity<?> remove(@RequestParam(name="id") Long id) {
	  try{
	    fAndvService.deleteExpiredProduct(Long.valueOf(id)); 
	    return ResponseEntity.ok("Product Saccessfully Deleted");      
	   }
	   catch (EmptyResultDataAccessException e){
	      return ResponseEntity.notFound().build();
	  }                                
	 }
	
}
