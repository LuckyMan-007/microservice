package com.yon.kafka_order_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yon.kafka_order_service.entity.KOrder;
import com.yon.kafka_order_service.repository.KOrderRepository;
import com.yon.kafka_order_service.service.EventProducer;

import events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/kapka-order")
@RequiredArgsConstructor
public class KOrderController {

	private final KOrderRepository repo;
	private final EventProducer producer;
	@PostMapping
	public ResponseEntity<KOrder> saveOrder(@RequestBody KOrder order){

		order.setStatus("Pending");
		order = repo.save(order);

		OrderCreatedEvent orderEvent = new OrderCreatedEvent();
		orderEvent.setOrderId(order.getId());
		orderEvent.setProductId(order.getProductId());
		orderEvent.setQuantity(order.getQuantity());
		
		producer.sentOrder(orderEvent);
		return  ResponseEntity.ok(order);
	}
	@GetMapping()
	public List<KOrder> getOrder(){
		return repo.findAll();
	}
	
	@GetMapping("/{id}")
	public KOrder findById(@PathVariable Long id) {
		
		return repo.findById(id).orElseThrow(()-> new RuntimeException("Order not found "+id));
	}
}
