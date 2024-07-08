package com.sushant.productservice.services;

import com.sushant.productservice.models.Product;
import com.sushant.productservice.repositories.CategoryRepository;
import com.sushant.productservice.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SelfProductServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void getAllProducts() {
        List<Product> products = productRepository.findByIdNotNullOrderByIdAsc();
        assertNotNull(products);
    }

    @Test
    void getProductById() {
        Optional<Product> product = productRepository.findById(129312312L);
        assert(product.isEmpty());
    }
}