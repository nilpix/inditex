package com.inditex.prices.domain.service;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.jpaadapter.PriceServiceAdapterImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PriceServiceTest {
    @Autowired
    private PriceServiceAdapterImpl priceService;

    // Example testing the domain

    @Test
    void filterPrice() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        Optional<Price> result = priceService.filterPrices(date, 35455L, 1L);

        assertTrue(result.isPresent());
        assertEquals(BigDecimal.valueOf(35.50).setScale(2, RoundingMode.HALF_UP), result.get().getPrice());
    }
}
