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
@Table(name="dairy")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Dairy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	public String productName;
	public Long productQuantity;
	public String purchaseDate;
	public String expirationDate;
}
