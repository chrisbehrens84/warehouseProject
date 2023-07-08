package com.skillstorm.warehousesproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.skillstorm.warehousesproject.models.Product;
import com.skillstorm.warehousesproject.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
}
