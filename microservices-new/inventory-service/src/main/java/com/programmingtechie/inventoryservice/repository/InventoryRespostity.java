package com.programmingtechie.inventoryservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.programmingtechie.inventoryservice.model.Inventory;

public interface InventoryRespostity extends JpaRepository<Inventory, Long> {

	//public Optional<Inventory> findBySkuCode(String skuCode);

	public List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
