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

import com.example.project.entity.FruitsAndVegetables;
import com.example.project.service.FruitsAndVegsService;


@RestController
@RequestMapping(value="/api/v1/")
@CrossOrigin(origins = "http://localhost:3000/")
public class FruitsAndVegsController {
	
	@Autowired
	public FruitsAndVegsService fAndvService;
	
	@PostMapping("/addFruitsAndVegs")
	public Map<String, Object> addMeat(@RequestBody FruitsAndVegetables frutAndVegs) {
		
		Map<String, Object> res = new HashMap<>();
		FruitsAndVegetables fAndV = new FruitsAndVegetables();
		
		try {
			fAndV = fAndvService.addFruitsAndVegs(frutAndVegs);
			
			res.put("fruits and vegetables", fAndV);
			//if the request was fine status 0 will be sent to the client
			res.put("status", 0); 
			
		}catch(Exception e) {
			res.put("status", 1);
			res.put("msg", "Somethig went wrong! \n There is an exception");
		}
		
		return res;
	}
	
	@GetMapping("/getFruitsAndVegs")
	public Iterable<FruitsAndVegetables> getAllFruitsAndVegs() {
		return fAndvService.getAllFruitsAndVegs();
	}
	
}