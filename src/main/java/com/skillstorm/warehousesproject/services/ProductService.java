package com.skillstorm.warehousesproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.warehousesproject.models.Product;
import com.skillstorm.warehousesproject.repositories.ProductRepository;

@Service
public class ProductService {
  
  @Autowired ProductRepository productRepository;

  public List<Product> findAllProducts(){
    return productRepository.findAll();
  }
}
