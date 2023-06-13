package com.programmingtechie.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.programmingtechie.orderservice.dto.InventoryResponse;
import com.programmingtechie.orderservice.dto.OrderLineItemsDto;
import com.programmingtechie.orderservice.dto.OrderRequest;
import com.programmingtechie.orderservice.model.Order;
import com.programmingtechie.orderservice.model.OrderLineItems;
import com.programmingtechie.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
	
	private final OrderRepository orderRepository;
	
	private final WebClient.Builder webClientBuilder;

	public void placeOrder(OrderRequest orderRequest) {
		Order order=new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItems> orderLineItems= orderRequest.getOrderLineItemsDtoList()
		.stream()
		.map(this::mapToDto)
		.toList();
		order.setOrderLineItemsList(orderLineItems);
		
		List<String> skuCodes=order.getOrderLineItemsList().stream()
				.map(orderLineItem->orderLineItem.getSkuCode())
				.toList();
		
		//Call inventory service, and place order if in stock
		InventoryResponse[] inventoryResponseArray=webClientBuilder.build().get()
			//.uri("http://localhost:8082/api/inventory",
				.uri("http://inventory-service/api/inventory",
					uriBuilder->uriBuilder.queryParam("skuCode", skuCodes).build())
			.retrieve()
			.bodyToMono(InventoryResponse[].class)
			.block();
		boolean allProdcutInStock=Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);
		if(allProdcutInStock) {
			orderRepository.save(order);
		}else {
			throw new IllegalArgumentException("Product is not in Stock, Please try later");
		}
		
	}

	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems=new OrderLineItems();
		BeanUtils.copyProperties(orderLineItemsDto, orderLineItems);
		return orderLineItems;
	}
}
