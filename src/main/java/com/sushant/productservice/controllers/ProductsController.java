package com.sushant.productservice.controllers;

import com.sushant.productservice.dtos.GenericProductDto;
import com.sushant.productservice.exceptions.NotFoundException;
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
        List<GenericProductDto> fakeStoreProductDtoList = productService.getAllProducts();
        return new ResponseEntity<>(
                fakeStoreProductDtoList,
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws Exception {
        Optional<GenericProductDto> genericProductDto = productService.getProductById(id);
        if(genericProductDto.isPresent()) return genericProductDto.get();
        else throw new NotFoundException("Product not found!!");
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto) {
        return productService.createProduct(genericProductDto);
    }
}
