package com.yon.kafka_order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yon.kafka_order_service.entity.KOrder;

@Repository
public interface KOrderRepository extends JpaRepository<KOrder,Long> {

}
