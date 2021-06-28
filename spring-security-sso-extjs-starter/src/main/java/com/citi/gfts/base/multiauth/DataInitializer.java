package com.citi.gfts.base.multiauth;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.citi.gfts.base.multiauth.db.access.AppUserRepository;
import com.citi.gfts.base.multiauth.db.access.ProductRepository;
import com.citi.gfts.base.multiauth.db.model.AppUser;
import com.citi.gfts.base.multiauth.db.model.Product;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

	@Autowired
	AppUserRepository appUserRepository;
	
	@Autowired 
	ProductRepository productRepository; 
	
	
	@Autowired 
	PasswordEncoder passwordEncoder; 
	
	@Override
	public void run(String... args) throws Exception {
		//userRepository.save(new User(null, "john", encoder.encode("john"), Arrays.asList(new Role(null, "ONE"), new Role(null, "TWO")))); 
		appUserRepository.deleteAll();
		appUserRepository.save(new AppUser(null, "user", passwordEncoder.encode("user"), "USER,API"));
		appUserRepository.save(new AppUser(null, "admin", passwordEncoder.encode("admin"), "ADMIN")); 
		log.info("user database initialized...");
		productRepository.deleteAll();
		productRepository.save(new Product(null,"one")); productRepository.save(new Product(null,"two")); productRepository.save(new Product(null,"three"));
		log.info("product database initialized...");
	}
}
