package com.skillstorm.warehousesproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.warehousesproject.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Add custom query methods if needed
}
