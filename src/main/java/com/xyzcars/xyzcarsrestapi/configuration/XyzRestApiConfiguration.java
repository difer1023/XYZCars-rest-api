package com.xyzcars.xyzcarsrestapi.configuration;

import com.xyzcars.xyzcarsrestapi.model.Customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XyzRestApiConfiguration {
	@Bean
	public Customer customer() {
		return new Customer();
	}
}
