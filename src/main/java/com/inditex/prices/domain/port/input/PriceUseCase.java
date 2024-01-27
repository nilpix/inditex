package com.inditex.prices.domain.port.input;

import com.inditex.prices.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceUseCase {
    Optional<Price> filterPrices(LocalDateTime filterDate, Long productId, Long brandId);
}
