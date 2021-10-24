package com.example.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		Meat pMeat = new Meat();
		
		try {
			pMeat = mService.addMeat(meat);
			
			res.put("meat", pMeat);
			//if the request was fine status 0 will be sent to the client
			res.put("status", 0); 
			
		}catch(Exception e) {
			res.put("status", 1);
			res.put("msg", "Somethig went wrong! \n There is an exception");
		}
		
		return res;
	}
	
	/* Get All Dairy */
	@GetMapping("/getAllMeat")
	public Iterable<Meat> getAllMeat(){
		return mService.getAllMeatProducts();
	}
}
