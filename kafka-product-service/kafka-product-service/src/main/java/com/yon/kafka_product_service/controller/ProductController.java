package com.yon.kafka_product_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yon.kafka_product_service.entity.Product;
import com.yon.kafka_product_service.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {

	private final ProductRepository repo;
	public ProductController(ProductRepository repo) {
		this.repo=repo;
	}
	
	@PostMapping
	public ResponseEntity<Product> insert(@RequestBody Product product){
		System.out.println("Enter Repo:::::"+product.getName());
		
		return ResponseEntity.ok(repo.save(product));	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id){
		
		return repo.findById(id).map(ResponseEntity ::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping
	public List<Product> findAll(){
		System.out.println("enter get all:::::");
		return repo.findAll();
	}
}
