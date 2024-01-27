package com.inditex.prices.application.config;

import com.inditex.prices.domain.port.output.PriceRepository;
import com.inditex.prices.domain.service.PriceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public PriceService priceService(PriceRepository priceRepository) {
        return new PriceService(priceRepository); // or any appropriate constructor
    }
}
