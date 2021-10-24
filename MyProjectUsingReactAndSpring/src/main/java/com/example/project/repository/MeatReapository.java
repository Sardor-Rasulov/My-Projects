package com.example.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.project.entity.Meat;

@Repository
public interface MeatReapository extends CrudRepository<Meat, Long>{

}
