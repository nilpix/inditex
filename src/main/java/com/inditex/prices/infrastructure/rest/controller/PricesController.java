package com.inditex.prices.infrastructure.rest.controller;

import com.inditex.prices.domain.port.exception.ProductNotFoundException;
import com.inditex.prices.infrastructure.jpaadapter.input.PriceInputAdapter;
import com.inditex.prices.infrastructure.rest.dto.request.PriceRequest;
import com.inditex.prices.infrastructure.rest.dto.response.PriceResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/prices")
public class PricesController {

    private final PriceInputAdapter priceInputAdapter;

    @GetMapping("/filter")
    public ResponseEntity<PriceResponse> filterPrices(@Valid @ModelAttribute PriceRequest priceRequest, BindingResult result) {
        if (result.hasErrors()) {
            PriceResponse errorResponse = new PriceResponse();
            Map<String, String> validationErrors = new HashMap<>();

            result.getFieldErrors().forEach(error ->
                    validationErrors.put(error.getField(), error.getDefaultMessage())
            );

            errorResponse.setErrors(validationErrors);
            return ResponseEntity.badRequest().body(errorResponse);
        }
        Optional<PriceResponse> optionalPrice = priceInputAdapter.filterPrices(
                priceRequest.getDate(),
                priceRequest.getProductId(),
                priceRequest.getBrandId()
        );
        return optionalPrice.map(ResponseEntity::ok) // TODO missing map with other. mapper.
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found " + priceRequest.getProductId() +
                                " on date " + priceRequest.getDate()
                ));
    }
}
