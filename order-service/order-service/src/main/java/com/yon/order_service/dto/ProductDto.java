package com.yon.order_service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
	private Long id;
	private String name;
	private Double price;
	private Integer stock;
}
