package com.inditex.prices.infrastructure.rest.exceptions;

import com.inditex.prices.domain.port.exception.ProductNotFoundException;
import com.inditex.prices.infrastructure.rest.dto.response.PriceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<PriceResponse> handleProductNotFoundException(ProductNotFoundException ex) {
        PriceResponse response = new PriceResponse();
        response.setErrors(Map.of("error", ex.getMessage()));;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}