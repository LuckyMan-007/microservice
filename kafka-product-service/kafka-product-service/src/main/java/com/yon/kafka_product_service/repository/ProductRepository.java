package com.yon.kafka_product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yon.kafka_product_service.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
