// Name: Wendesen Teshale
// Student Number: ATE/4671/14
package com.shopwave.controller;

import com.shopwave.dto.ProductDTO;
import com.shopwave.exception.ProductNotFoundException;
import com.shopwave.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void testGetAllProductsPaginated() throws Exception {
        ProductDTO productDTO = ProductDTO.builder().id(1L).name("Paginated Product").build();
        PageImpl<ProductDTO> page = new PageImpl<>(Collections.singletonList(productDTO), PageRequest.of(0, 10), 1);

        when(productService.getAllProducts(any())).thenReturn(page);

        mockMvc.perform(get("/api/products")
                .param("page", "0")
                .param("size", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Paginated Product"))
                .andExpect(jsonPath("$.totalElements").value(1));
    }

    @Test
    void testGetProductNotFound() throws Exception {
        when(productService.getProductById(999L)).thenThrow(new ProductNotFoundException("Product not found with ID: 999"));

        mockMvc.perform(get("/api/products/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").value("Product not found with ID: 999"))
                .andExpect(jsonPath("$.path").value("/api/products/999"));
    }
}
