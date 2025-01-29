package com.inditex.prices.infrastructure.jpaadapter;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.domain.port.output.PriceRepository;
import com.inditex.prices.infrastructure.jpaadapter.mapper.PriceMapper;
import com.inditex.prices.infrastructure.jpaadapter.output.persistence.JpaPriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PriceServiceAdapterImpl implements PriceRepository {

    private final JpaPriceRepository jpaPriceRepository;
    private final PriceMapper mapper;
    public static final String PRIORITY = "priority";

    @Override
    public Optional<Price> filterPrices(LocalDateTime filterDate, Long productId, Long brandId) {
        return jpaPriceRepository.filterPrices(filterDate, productId, brandId, Sort.by(PRIORITY).descending())
                .stream().findFirst().map(mapper::toDomain);
    }
}
