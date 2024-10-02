package com.sushant.productservice.controllers;

import com.sushant.productservice.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        mockMvc.perform(get("/products")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
        verify(productService, times(1)).getAllProducts();
        verifyNoMoreInteractions(productService);
    }

    //@Test
    public void testGetProductById() throws Exception {
        mockMvc.perform(get("/products/1")).andExpect(status().isOk());
        verify(productService, times(1)).getProductById(1L);
        verifyNoMoreInteractions(productService);
    }
}