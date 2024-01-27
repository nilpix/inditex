package com.inditex.prices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.inditex.prices")
public class InditexPricesApplication {

	public static void main(String[] args) {
		SpringApplication.run(InditexPricesApplication.class, args);
	}

}
