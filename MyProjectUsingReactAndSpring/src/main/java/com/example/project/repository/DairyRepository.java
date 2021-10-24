package com.example.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.project.entity.Dairy;

@Repository
public interface DairyRepository extends CrudRepository<Dairy, Long> {
	
}
