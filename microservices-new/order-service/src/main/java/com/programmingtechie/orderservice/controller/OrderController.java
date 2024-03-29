package com.programmingtechie.orderservice.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.programmingtechie.orderservice.dto.OrderRequest;
import com.programmingtechie.orderservice.dto.OrderResponse;
import com.programmingtechie.orderservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.micrometer.core.annotation.TimedSet;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;
	
	@PostMapping(value ="/order")
	@ResponseStatus(HttpStatus.CREATED)
	@CircuitBreaker(name="inventory", fallbackMethod = "fallbackMehtod")
	//@TimeLimiter(name="inventory")
	//@Retry(name="inventory")
	public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
		return CompletableFuture.supplyAsync(()->orderService.placeOrder(orderRequest));
		//return "Order placed successfully";
		
	}
	
	@PostMapping(value  = "/orderByFeignClient")
	@ResponseStatus(HttpStatus.CREATED)
	public String placeOrderByFeignClient(@RequestBody OrderRequest orderRequest) {
		return orderService.placeOrderByFeignClient(orderRequest);
	}
	
	

	@GetMapping(value ="/order")
	@ResponseStatus(HttpStatus.OK)
	public List<OrderResponse> getOrders() {
		return orderService.getOrders();
	}
	
	
	public CompletableFuture<String> fallbackMehtod(OrderRequest orderRequest,
			RuntimeException runtimeException) {
		runtimeException.printStackTrace();
		//return "oops something went wrong, please order after some time!";
		return CompletableFuture.supplyAsync(()-> "oops something went wrong, please order after some time!");
		
	}

}
