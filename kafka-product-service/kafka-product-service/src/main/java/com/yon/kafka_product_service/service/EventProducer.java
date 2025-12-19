package com.yon.kafka_product_service.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventProducer {

 private final KafkaTemplate<String,OrderCreatedEvent> kafkaTemplate;
 
  public void sendResponse(OrderCreatedEvent event) {
	  kafkaTemplate.send("order-response" , event);
  }
	
}
