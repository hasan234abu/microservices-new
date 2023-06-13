package com.programmingtechie.inventoryservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.programmingtechie.inventoryservice.model.Inventory;
import com.programmingtechie.inventoryservice.repository.InventoryRespostity;

@SpringBootApplication
@EnableEurekaClient
public class InvertoryServiceApplication implements org.springframework.boot.CommandLineRunner {
	
	
	@Autowired
    private InventoryRespostity inventoryRespostity;


	public static void main(String[] args) {
		SpringApplication.run(InvertoryServiceApplication.class, args);
	}
	

	@Override
	public void run(String... args) throws Exception {
		Inventory inventory =new Inventory();
		inventory.setSkuCode("iphone_13");
		inventory.setQuantity(100);
		
		Inventory inventory1 =new Inventory();
		inventory1.setSkuCode("iphone_13_red");
		inventory1.setQuantity(0);
		
		//inventoryRespostity.save(inventory);
		//inventoryRespostity.save(inventory1);
		
	}

}
