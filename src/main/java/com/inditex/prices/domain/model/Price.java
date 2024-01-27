package com.inditex.prices.domain.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
public class Price {
    @NotNull
    @Min(value = 1)
    private long brandId;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull(message = "Price list cannot be null")
    @Min(value = 1, message = "Price list must be greater than 0")
    private long priceList;

    @NotNull(message = "Product ID cannot be null")
    @Min(value = 1, message = "Product ID must be greater than 0")
    private long productId;

    @NotNull(message = "Priority cannot be null")
    private int priority;

    @NotNull(message = "Price cannot be null")
    @Min(value = 1, message = "Price must be greater than 0")
    private BigDecimal price; // Big decimal for better precision and escalation

    @NotNull(message = "Currency cannot be null")
    private String currency;

}
