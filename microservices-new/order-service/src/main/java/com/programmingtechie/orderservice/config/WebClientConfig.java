package com.programmingtechie.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration 
public class WebClientConfig {

	/*
	 * //Without Load balancing
	@Bean
	public WebClient webClient() {
		return WebClient.builder().build();
	}
	*/
	
	@Bean
	@LoadBalanced  //for Client side Loadbalancing
	public WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}
}
