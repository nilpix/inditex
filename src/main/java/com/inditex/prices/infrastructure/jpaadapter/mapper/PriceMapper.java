package com.inditex.prices.infrastructure.jpaadapter.mapper;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.jpaadapter.entity.PriceAdapter;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {
    public Price toDomain(PriceAdapter entity) {
        return Price.builder()
                .brandId(entity.getBrandId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .priceList(entity.getPriceList())
                .productId(entity.getProductId())
                .priority(entity.getPriority())
                .price(entity.getPrice())
                .currency(entity.getCurrency())
                .build();
    }
}
