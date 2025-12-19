package com.yon.kafka_order_service.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductEvent {
	private Long orderId;
    private Long quantity;
    private Long productId;
}
