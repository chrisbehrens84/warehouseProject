package com.skillstorm.warehousesproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.*;


import com.skillstorm.warehousesproject.models.Product;
import com.skillstorm.warehousesproject.services.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin("http://127.0.0.1:5500/src/main/resources/static/index.html")
public class ProductController {



    @Autowired
    ProductService productService;
 

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

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product ) throws NotFoundException {
        return productService.updateProduct(id, product);
    }
}
