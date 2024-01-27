package com.inditex.prices.infrastructure.rest.controller;

import com.inditex.prices.infrastructure.jpaadapter.input.PriceInputAdapter;
import com.inditex.prices.infrastructure.rest.dto.request.PriceRequest;
import com.inditex.prices.infrastructure.rest.dto.response.PriceResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/prices")
public class PricesController {

    private final PriceInputAdapter priceInputAdapter;

    @GetMapping("/filter")
    public ResponseEntity<PriceResponse> filterPrices(@Valid @ModelAttribute PriceRequest priceRequest, BindingResult result) {
        if (result.hasErrors()) { //TODO review
            PriceResponse errorResponse = new PriceResponse();
            errorResponse.setErrors(result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage
                    ))
            );
            return ResponseEntity.badRequest().body(errorResponse);
        }
        Optional<PriceResponse> optionalPrice = priceInputAdapter.filterPrices(
                priceRequest.getDate(),
                priceRequest.getProductId(),
                priceRequest.getBrandId()
        );
        return optionalPrice.map(ResponseEntity::ok) // TODO missing map with other. mapper.
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
