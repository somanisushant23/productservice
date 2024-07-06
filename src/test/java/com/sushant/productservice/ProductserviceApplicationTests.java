package com.sushant.productservice;

import com.sushant.productservice.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductserviceApplicationTests {

	@Autowired
	private ProductRepository productRepository;


	@Test
	void contextLoads() {
	}

	@Test
	void testQueries() {
		productRepository.findByTitleContaining("iPhone");

	}

}
