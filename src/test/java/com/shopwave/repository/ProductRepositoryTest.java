// Name: Wendesen Teshale
// Student Number: ATE/4671/14
package com.shopwave.repository;

import com.shopwave.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    void testFindByNameContainingIgnoreCase() {
        Product p1 = Product.builder().name("Gaming Laptop").price(BigDecimal.valueOf(1500)).build();
        Product p2 = Product.builder().name("Wireless Mouse").price(BigDecimal.valueOf(50)).build();
        Product p3 = Product.builder().name("laptop stand").price(BigDecimal.valueOf(30)).build();

        productRepository.saveAll(List.of(p1, p2, p3));

        List<Product> results = productRepository.findByNameContainingIgnoreCase("laptop");

        assertEquals(2, results.size(), "Should find 'Gaming Laptop' and 'laptop stand'");
    }
}

