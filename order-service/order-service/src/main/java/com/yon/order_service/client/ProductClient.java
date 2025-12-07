package com.yon.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yon.order_service.dto.ProductDto;

@FeignClient(name="product-service")
public interface ProductClient {
	
	@GetMapping("/product/{id}")
	ProductDto getProduct(@PathVariable Long id);
}
