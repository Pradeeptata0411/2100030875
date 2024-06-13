package com.exam.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import org.springframework.boot.web.client.RestTemplateBuilder;


import java.time.Duration;

@Configuration
public class AppConfig {

	  @Bean
	    public RestTemplate restTemplate(RestTemplateBuilder builder) {
	        return builder
	                .setConnectTimeout(Duration.ofMillis(500))
	                .setReadTimeout(Duration.ofMillis(500))
	                .build();
	    }
}
