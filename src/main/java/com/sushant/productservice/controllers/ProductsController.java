package com.sushant.productservice.controllers;

import com.sushant.productservice.dtos.GenericProductDto;
import com.sushant.productservice.exceptions.NotFoundException;
import com.sushant.productservice.models.Product;
import com.sushant.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private ProductService productService;

    public ProductsController(@Qualifier("selfProductService") ProductService productService) {
        //qualifier determines which service, if multiple implements ProductService interface, should
        //be used as primary service. @Primary annotation can also be used on service but that is NOT flawless!!
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<GenericProductDto>> getAllProducts() {
        List<GenericProductDto> productsDtoList = productService.getAllProducts();
        return new ResponseEntity<>(
                productsDtoList,
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException{
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }
}
