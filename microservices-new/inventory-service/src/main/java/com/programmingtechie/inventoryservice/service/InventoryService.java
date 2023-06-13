package com.programmingtechie.inventoryservice.service;



import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programmingtechie.inventoryservice.dto.InventoryResponse;
import com.programmingtechie.inventoryservice.repository.InventoryRespostity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {
	
	private final InventoryRespostity inventoryRespostity;

	@Transactional(readOnly=true)
	public List<InventoryResponse> isInStock(List<String> skuCode) {
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
