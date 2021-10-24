package com.example.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="fruits_vegetables")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FruitsAndVegetables {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String productName;
	private String productQuantity;
	private String purchaseDate;
	private String expirationDate;
	
}
