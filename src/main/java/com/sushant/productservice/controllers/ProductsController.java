package com.sushant.productservice.controllers;

import com.sushant.productservice.commons.AuthCommons;
import com.sushant.productservice.dtos.GenericProductDto;
import com.sushant.productservice.exceptions.NotFoundException;
import com.sushant.productservice.models.Product;
import com.sushant.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private ProductService productService;

    private AuthCommons authCommons;

    public ProductsController(@Qualifier("selfProductService") ProductService productService, AuthCommons authCommons) {
        //qualifier determines which service, if multiple implements ProductService interface, should
        //be used as primary service. @Primary annotation can also be used on service but that is NOT flawless!!

        this.productService = productService;
        this.authCommons = authCommons;
    }

    @GetMapping
    public ResponseEntity<List<GenericProductDto>> getAllProducts(@RequestHeader("Authorization") String token) {
        if(!authCommons.validateToken(token)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
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

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(Long id) {
        if(productService.deleteProduct(id)) {
            return new ResponseEntity<>("Product deleted successfully",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Something went wrong!!",HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PatchMapping
    public Product updateProduct(Product product) {
        return productService.updateProduct(product);
    }
}
