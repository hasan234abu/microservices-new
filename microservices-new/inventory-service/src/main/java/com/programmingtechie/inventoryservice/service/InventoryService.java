package com.programmingtechie.inventoryservice.service;



import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmingtechie.inventoryservice.controller.InventoryController;
import com.programmingtechie.inventoryservice.dto.InventoryResponse;
import com.programmingtechie.inventoryservice.repository.InventoryRespostity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
	
	private final InventoryRespostity inventoryRespostity;

	@Transactional(readOnly=true)
	public List<InventoryResponse> isInStock(List<String> skuCode) throws InterruptedException {
		log.info("Thread started>>");
		//Thread.sleep(10000);
		log.info("Thread wait Ended>>");
		return inventoryRespostity.findBySkuCodeIn(skuCode)
				.stream()
				.map(inventory->
					InventoryResponse.builder()
					.skuCode(inventory.getSkuCode())
					.inStock(inventory.getQuantity()>0)
					.build()
				).toList();
	}
}
