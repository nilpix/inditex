package com.inditex.prices.domain.service;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.domain.port.input.PriceUseCase;
import com.inditex.prices.domain.port.output.PriceRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
public class PriceService implements PriceUseCase {

    private PriceRepository priceRepository;

    @Override
    public Optional<Price> filterPrices(LocalDateTime filterDate, Long productId, Long brandId) {
        return priceRepository.filterPrices(filterDate, productId, brandId);
    }
}
