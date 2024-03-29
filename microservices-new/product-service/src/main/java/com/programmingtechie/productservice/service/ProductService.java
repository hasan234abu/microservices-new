package com.programmingtechie.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programmingtechie.productservice.dto.OrderResponse;
import com.programmingtechie.productservice.dto.ProductRequest;
import com.programmingtechie.productservice.dto.ProductResponse;
import com.programmingtechie.productservice.model.Product;
import com.programmingtechie.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

	
	@Autowired
	OrderServiceClient orderServiceClient;
	
	private final ProductRepository productRepository;
	
	public void createProduct(ProductRequest productRequest) {
		Product product=Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build();
				
		productRepository.save(product);
		log.info("Product {} is saved",product.getId());
	}

	public List<ProductResponse> getAllProducts() {
		List<Product> products=productRepository.findAll();
		return products.stream().map(this::mapToProductResposne).toList();
		
	}
	
	private ProductResponse mapToProductResposne(Product product) {
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}

	public List<OrderResponse> getAllOrders() {
		return orderServiceClient.getOrder();
	}
}
