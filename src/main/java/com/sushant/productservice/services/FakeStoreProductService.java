package com.sushant.productservice.services;

import com.sushant.productservice.dtos.GenericProductDto;
import com.sushant.productservice.models.Product;
import com.sushant.productservice.thirdpartyclients.fakestore.FakeStoreProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    @Autowired
    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return fakeStoreProductServiceClient.getAllProducts()
                .map(source -> new GenericProductDto(source.getId(), source.getTitle(),
                        source.getDescription(), source.getImage(),
                        source.getCategory(), source.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public GenericProductDto getProductById(Long id) {
        return Optional.ofNullable((fakeStoreProductServiceClient.getProductById(id)))
                .map(source -> new GenericProductDto(source.getId(), source.getTitle(), source.getDescription(), source.getImage(), source.getCategory(), source.getPrice())).get();
    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long id) {
        return false;
    }


    public Product addNewProduct(String title, String description, String imageUrl, double price, String categoryName) {
        return null;
    }

    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        //FakeStoreProductDto fakeStoreProductDto = fakeStoreProductServiceClient.createProduct(genericProductDto);
        return Stream.of(fakeStoreProductServiceClient.createProduct(genericProductDto))
                .map(source -> new GenericProductDto(source.getId(), source.getTitle(), source.getDescription(),
                        source.getImage(), source.getCategory(), source.getPrice())).findFirst().get();
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void postToKafka() {

    }
}
