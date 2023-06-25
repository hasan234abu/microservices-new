package com.programmingtechie.orderservice.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programmingtechie.orderservice.dto.OrderRequest;
import com.programmingtechie.orderservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.micrometer.core.annotation.TimedSet;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CircuitBreaker(name="inventory", fallbackMethod = "fallbackMehtod")
	//@TimeLimiter(name="inventory")
	//@Retry(name="inventory")
	public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
		return CompletableFuture.supplyAsync(()->orderService.placeOrder(orderRequest));
		//return "Order placed successfully";
		
	}
	
	
	public CompletableFuture<String> fallbackMehtod(OrderRequest orderRequest,
			RuntimeException runtimeException) {
		runtimeException.printStackTrace();
		//return "oops something went wrong, please order after some time!";
		return CompletableFuture.supplyAsync(()-> "oops something went wrong, please order after some time!");
		
	}

}
