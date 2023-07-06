package com.skillstorm.warehousesproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.warehousesproject.models.Product;
import com.skillstorm.warehousesproject.services.ProductService;

@RestController
@RequestMapping("/")
public class ProductController {
  @Autowired ProductService productService;


  @GetMapping()
  public ResponseEntity<List<Product>> findAllProducts(){
    List<Product> products = productService.findAllProducts();

    
    return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
  }

}
