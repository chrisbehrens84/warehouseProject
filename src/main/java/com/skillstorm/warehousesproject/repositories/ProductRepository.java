package com.skillstorm.warehousesproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.warehousesproject.models.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
  
}