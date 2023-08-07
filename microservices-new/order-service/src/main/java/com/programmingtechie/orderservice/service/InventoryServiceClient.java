package com.programmingtechie.orderservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.programmingtechie.orderservice.dto.InventoryResponse;



@FeignClient(name="inventory-service")
public interface InventoryServiceClient {
	
	@GetMapping("/api/inventoryy")
	public List<InventoryResponse> isInStock(@RequestParam("skuCode") List<String> skuCode)
			throws InterruptedException ;

}
