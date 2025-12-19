package com.yon.kafka_order_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
    private Long id;
    private int quantity;
    private Long productId;
    private String status;
    
}
