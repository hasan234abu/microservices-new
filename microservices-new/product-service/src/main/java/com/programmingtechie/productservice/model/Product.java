package com.programmingtechie.productservice.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value="product")
@Builder
public class Product {

	@Id
	private String id;
	private String description;
	private String name;
	private BigDecimal price;
}
