package com.inditex.prices.domain.port.output;

import com.inditex.prices.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<Price> filterPrices(LocalDateTime filterDate, Long productId, Long brandId);
}
