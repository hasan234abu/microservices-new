package com.programmingtechie.productservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programmingtechie.productservice.dto.OrderResponse;
import com.programmingtechie.productservice.dto.ProductRequest;
import com.programmingtechie.productservice.dto.ProductResponse;
import com.programmingtechie.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
	
	final Logger logger = LoggerFactory.getLogger(ProductController.class);

	
	private final ProductService productService;
	
	
	
	@Autowired
	private Environment env;
	
	@GetMapping("/statusCheck")
	@ResponseStatus(HttpStatus.OK)
	public String status() {
		logger.info("Hey ProductService statusCheck invoked");
		logger.debug("Hey ProductService statusCheck invoked");
		return "Working  on port "+env.getProperty("local.server.port")+
				" ,with custom prop value1="+env.getProperty("custom.prop.value1")+
				", with custom prop value2="+env.getProperty("custom.prop.value2")+
				", with custom prop value3="+env.getProperty("custom.prop.value3")+
				", with custom prop value4="+env.getProperty("custom.prop.value4");
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductRequest productRequest) {
		productService.createProduct(productRequest);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping(value ="/allOrders")
	@ResponseStatus(HttpStatus.OK)
	public List<OrderResponse> getAllOrders() {
		return productService.getAllOrders();
	}

}
