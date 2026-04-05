// Name: Wendesen Teshale
// Student Number: ATE/4671/14
package com.shopwave.service;

import com.shopwave.dto.CreateProductRequest;
import com.shopwave.dto.ProductDTO;
import com.shopwave.model.Category;
import com.shopwave.model.Product;
import com.shopwave.repository.CategoryRepository;
import com.shopwave.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct_HappyPath() {
        CreateProductRequest request = CreateProductRequest.builder()
                .name("Test Product")
                .price(BigDecimal.valueOf(100.00))
                .stock(10)
                .categoryId(1L)
                .build();

        Category category = Category.builder().id(1L).name("Test Category").build();
        Product product = Product.builder().id(1L).name("Test Product").category(category).build();
        ProductDTO dto = ProductDTO.builder().id(1L).name("Test Product").build();

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productMapper.toDTO(any(Product.class))).thenReturn(dto);

        ProductDTO result = productService.createProduct(request);

        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        verify(categoryRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testCreateProduct_CategoryNotFound() {
        CreateProductRequest request = CreateProductRequest.builder()
                .name("Test Product")
                .categoryId(999L)
                .build();

        when(categoryRepository.findById(999L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.createProduct(request);
        });

        assertTrue(exception.getMessage().contains("Category not found"));
        verify(productRepository, never()).save(any(Product.class));
    }
}
