package com.sushant.productservice.thirdpartyclients.fakestore;

import com.sushant.productservice.dtos.GenericProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FakeStoreProductServiceClient {

    @Value("${fakestore.api.url}")
    private String fakeStoreApiUrl;

    @Value("${fakestore.api.paths.product}")
    private String fakeStoreProductsApiPath;

    private String specificProductRequestUrl ;
    private String productRequestsBaseUrl ;
    private RestTemplate restTemplate;

    public FakeStoreProductServiceClient(RestTemplate restTemplate,
                                         @Value("${fakestore.api.url}") String fakeStoreApiUrl,
                                         @Value("${fakestore.api.paths.product}") String fakeStoreProductsApiPath) {
        this.restTemplate = restTemplate;
        this.productRequestsBaseUrl = fakeStoreApiUrl + fakeStoreProductsApiPath;
        this.specificProductRequestUrl = fakeStoreApiUrl + fakeStoreProductsApiPath + "/{id}";
    }

    public Stream<FakeStoreProductDto> getAllProducts() {

        ResponseEntity<FakeStoreProductDto[]> responseEntity =
                restTemplate.getForEntity(productRequestsBaseUrl, FakeStoreProductDto[].class);

        return Arrays.stream(responseEntity.getBody());
    }

    public FakeStoreProductDto getProductById(Long id) {
        ResponseEntity<FakeStoreProductDto> response =restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }

    public FakeStoreProductDto createProduct(GenericProductDto genericProductDto) {
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(productRequestsBaseUrl, genericProductDto, FakeStoreProductDto.class);
        return response.getBody();
    }
}
