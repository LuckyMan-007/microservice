package com.yon.kafka_order_service.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventProducer {

	private final KafkaTemplate<String,OrderCreatedEvent> kafkaTemplate;
	
	public void sentOrder(OrderCreatedEvent orderEvent){
		kafkaTemplate.send("order-created",orderEvent);
	}
}
