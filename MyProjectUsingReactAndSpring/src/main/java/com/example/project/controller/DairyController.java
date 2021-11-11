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
import com.example.project.entity.FruitsAndVegetables;
import com.example.project.service.DairyService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value="/api/v1/")
public class DairyController {
	
	@Autowired
	public DairyService dService;
	
	/* Add Dairy Product */
	@PostMapping("/addProduct")
	public Map<String, Object> addDairyProduct(@RequestBody Dairy product) {
		
		Map<String, Object> result = new HashMap<>();
		
		try {
			
			//Check if the dairy section is full 
			ArrayList<Dairy> getProducts = (ArrayList<Dairy>) dService.getAllDairyProducts();
			if(getProducts.size() <= 4) {
				Dairy dProduct = dService.addProduct(product);
				result.put("Dairy Product", dProduct);
				result.put("status", 0);
			}else {
				result.put("status", "Dairy Section is Full. \n" + " Please empty it first");
			}
			
		} catch(Exception e) {
			result.put("status", 9);
			result.put("msg", "There is an exception");
		}
		
		return result;
	}
	
	/* Update product quantity */
	@PostMapping("/updateDairyQuantity")
	public Map<String, Object> updateQuantity(@RequestParam (name="id") Long id,
											  @RequestParam (name="productQuantity") Float quantity){
		
		Map<String, Object> res = new HashMap<>(); 
		
		try {
			
			/* Get product by id */
			Dairy dairy = new Dairy();
			dairy = dService.getById(id);
			Float quantityInDb = dairy.getProductQuantity();
			
			/*
			 * Check if amount which comes from user 
			 * is lesser than the amount in DB
			 */ 
			if(quantity < quantityInDb) {
				
				Float quntityRes = quantityInDb - quantity;
				
				dairy.setProductQuantity(quntityRes);
				dService.updateQuantity(dairy);
				
				res.put("fruitsAndVegs", dairy);
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

	
	/* Get All Dairy */
	@GetMapping("/getAllDairy")
	public Iterable<Dairy> getAllDairy(){
		return dService.getAllDairyProducts();
	}
}
