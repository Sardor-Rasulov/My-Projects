package com.example.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.project.entity.FruitsAndVegetables;

@Repository
public interface FruitsAndVegsRepository extends CrudRepository<FruitsAndVegetables, Long>{

}
