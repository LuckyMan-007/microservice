package com.yon.kafka_order_service.service;

import java.util.Optional;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.yon.kafka_order_service.entity.KOrder;
import com.yon.kafka_order_service.repository.KOrderRepository;

import events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventConsumer {

	private final KOrderRepository repo;
		@KafkaListener(topics = "order-response",groupId = "real-time-group")
		public void getResponse(OrderCreatedEvent event) {
			Optional<KOrder> opOrder = repo.findById(event.getOrderId());
			if(opOrder.isPresent()) {
			    KOrder order = opOrder.get();
				order.setStatus(event.getStatus());
				repo.save(order);
			}
		}
}
