package com.skillstorm.warehousesproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.skillstorm.warehousesproject.models.Product;
import com.skillstorm.warehousesproject.models.Warehouse;
import com.skillstorm.warehousesproject.repositories.ProductRepository;



@Service
public class ProductService {


    @Autowired
    ProductRepository productRepository;
    

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) throws NotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
    }

    public Product updateProduct(Long id, Product updatedProduct) throws NotFoundException {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());

        return productRepository.save(existingProduct);
    }

}
