package com.yon.kafka_product_service.service;


import java.util.Optional;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.yon.kafka_product_service.entity.Product;
import com.yon.kafka_product_service.repository.ProductRepository;

import events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventConsumer {
	
	private final EventProducer producer;
    private final ProductRepository repo;
    
	@KafkaListener(topics="order-created",groupId ="real-time-group")
	public void consumeOrder(OrderCreatedEvent event) {
		System.out.println("enter event:::::::::::::::::::"+event);

		Optional<Product> optProd = repo.findById(event.getProductId());
		if(optProd.isPresent()) {
			 Product prod = optProd.get();
			if(prod.getStock() >= event.getQuantity()) {
				prod.setStock(prod.getStock()-event.getQuantity());
				repo.save(prod);
				event.setStatus("Accept");
			}
			else {
				event.setStatus("Out Of Quantity");
			}
	    }else {
	    	event.setStatus("Product Id Not Found");
	    }
		producer.sendResponse(event);
	}
}
