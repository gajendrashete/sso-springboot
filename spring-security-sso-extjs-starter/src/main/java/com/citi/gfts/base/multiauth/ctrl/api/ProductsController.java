package com.citi.gfts.base.multiauth.ctrl.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.citi.gfts.base.multiauth.db.access.ProductRepository;
import com.citi.gfts.base.multiauth.db.model.Product;
import com.citi.gfts.base.multiauth.sec.svc.ContextService;

import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping("/api")
@Slf4j
public class ProductsController {

	@Autowired
	ContextService contextService;
	
	@Autowired
	ProductRepository productRepository; 

	@GetMapping("/products")
	public @ResponseBody List<Product> getProducts() {
		log.info(contextService.getContextDetails());
		return productRepository.findAll();
	}

}
