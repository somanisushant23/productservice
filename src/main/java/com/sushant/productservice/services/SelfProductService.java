package com.sushant.productservice.services;

import com.sushant.productservice.dtos.GenericProductDto;
import com.sushant.productservice.exceptions.NotFoundException;
import com.sushant.productservice.models.Product;
import com.sushant.productservice.repositories.CategoryRepository;
import com.sushant.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return productRepository.findByIdNotNullOrderByIdAsc().stream()
                .map(prod -> new GenericProductDto(prod.getId(), prod.getTitle(), prod.getDescription(), prod.getImageUrl(), prod.getCategory().getName(), prod.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) {
            throw new NotFoundException("Product not present in DB!!");
        } else {
            return product.stream().map(prod -> new GenericProductDto(prod.getId(), prod.getTitle(), prod.getDescription(), prod.getImageUrl(), prod.getCategory().getName(), prod.getPrice())).findFirst().get();
        }
    }

    @Override
    public Product addNewProduct(Product product) {
        //Add createdDate and updateDate here
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.updateIsDeletedAndTitleAndDescriptionAndImageUrlAndPriceById(product.isDeleted(), product.getTitle(),
                product.getDescription(), product.getImageUrl(), product.getPrice(), product.getId());
    }

    @Override
    public boolean deleteProduct(Long id) {
        productRepository.deleteById(id);
        return true;
    }


    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }
}