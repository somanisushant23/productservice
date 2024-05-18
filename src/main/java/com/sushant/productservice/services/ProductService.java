package com.sushant.productservice.services;

import com.sushant.productservice.dtos.GenericProductDto;
import com.sushant.productservice.thirdpartyclients.fakestore.FakeStoreProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<GenericProductDto> getAllProducts();

    Optional<GenericProductDto> getProductById(Long id);

    GenericProductDto createProduct(GenericProductDto genericProductDto);
}
