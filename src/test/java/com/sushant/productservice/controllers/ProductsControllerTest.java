package com.sushant.productservice.controllers;

import com.sushant.productservice.services.ProductService;
import com.sushant.productservice.thirdpartyclients.fakestore.FakeStoreProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;

@SpringBootTest
class ProductsControllerTest {

    private MockMvc mockMvc;
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductsController productsController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(productsController).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        List<FakeStoreProductDto> fakeStoreProductDtoList = new ArrayList<>();
        FakeStoreProductDto product1 = new FakeStoreProductDto();
        FakeStoreProductDto product2 = new FakeStoreProductDto();

        fakeStoreProductDtoList.add(product1);
        fakeStoreProductDtoList.add(product2);

        when(productService.getAllProducts()).thenReturn(fakeStoreProductDtoList);
        mockMvc.perform(get("/products")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
        verify(productService, times(1)).getAllProducts();
        verifyNoMoreInteractions(productService);
    }
}