package com.sushant.productservice.services;

import com.sushant.productservice.dtos.GenericProductDto;
import com.sushant.productservice.exceptions.NotFoundException;
import com.sushant.productservice.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<GenericProductDto> getAllProducts();

    GenericProductDto getProductById(Long id) throws NotFoundException;

    //Use builder design pattern
    Product addNewProduct(Product product);

    Product updateProduct(Product product);

    boolean deleteProduct(Long id);

    Product replaceProduct(Long id, Product product);

    void postToKafka();
}
