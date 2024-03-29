package com.citi.gfts.base.multiauth.db.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String name;
}