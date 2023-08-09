package com.programmingtechie.productservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import com.programmingtechie.productservice.dto.OrderResponse;





@FeignClient(name="order-service")
public interface OrderServiceClient {
	
	@GetMapping("/api/order")
	public List<OrderResponse> getOrder();

}
