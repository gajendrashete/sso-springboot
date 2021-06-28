package com.citi.gfts.base.multiauth.db.access;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.citi.gfts.base.multiauth.db.model.Product;


@Component
public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
