package com.programmingtechie.productservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

	private String id;
	private String description;
	private String name;
	private BigDecimal price;
}
