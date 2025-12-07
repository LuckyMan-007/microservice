package com.yon.order_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yon.order_service.client.ProductClient;
import com.yon.order_service.dto.ProductDto;
import com.yon.order_service.entity.Order;
import com.yon.order_service.repository.OrderRepository;

@RestController
@RequestMapping("/order")
public class OrderController {
	OrderRepository rep ;
	ProductClient client;
	
	OrderController(OrderRepository rep,ProductClient client){
		this.rep= rep;
		this.client=client;
	}
	
	@GetMapping
	public List<Order>getAllOrder(){
		return rep.findAll();
	}

	@PostMapping
	public ResponseEntity<Order> save(@RequestBody Order order){
		System.out.println("product id ::::"+order.getProductId());
		ProductDto prod = client.getProduct(order.getProductId());
		
		order.setProductId(prod.getId());
		
		if(order.getQuantity() != null && prod.getStock()>=order.getQuantity()) {
		order.setStatus("Create");
		}
		else {
			order.setStatus("Cancel");
		}
		
		System.out.println("prod::: "+prod);
		return ResponseEntity.ok(rep.save(order));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id){
		return rep.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
}
