package com.sushant.productservice.services;

import com.sushant.productservice.dtos.GenericProductDto;
import com.sushant.productservice.models.Product;
import com.sushant.productservice.repositories.CategoryRepository;
import com.sushant.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<GenericProductDto> getAllProducts() {
        return null;
    }

    @Override
    public Optional<GenericProductDto> getProductById(Long id) {
        return Optional.empty();
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }
}
