package com.inditex.prices.infrastructure.rest.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class PriceResponse {
    private Long productId;
    private Long brandId;
    private Long priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;

    private Map<String, String> errors;
}
