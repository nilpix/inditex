package com.inditex.prices.infrastructure.jpaadapter.input;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.domain.service.PriceService;
import com.inditex.prices.infrastructure.rest.dto.response.PriceResponse;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PriceInputAdapter {

    private final PriceService priceService;

    public Optional<PriceResponse> filterPrices(@NotNull LocalDateTime date, @NotNull Long productId, @NotNull Long brandId) {
        return priceService.filterPrices(date, productId, brandId)
                .map(this::toPrice);
    }

    private PriceResponse toPrice(Price price) {
        PriceResponse response = new PriceResponse();
        response.setProductId(price.getProductId());
        response.setBrandId(price.getBrandId());
        response.setPriceList(price.getPriceList());
        response.setStartDate(price.getStartDate());
        response.setEndDate(price.getEndDate());
        response.setPrice(price.getPrice());
        return response;
    }
}
