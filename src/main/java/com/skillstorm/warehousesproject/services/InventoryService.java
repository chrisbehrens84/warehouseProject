package com.skillstorm.warehousesproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.warehousesproject.models.Inventory;
import com.skillstorm.warehousesproject.models.Product;
import com.skillstorm.warehousesproject.models.Warehouse;
import com.skillstorm.warehousesproject.repositories.InventoryRepository;
import com.skillstorm.warehousesproject.repositories.ProductRepository;
import com.skillstorm.warehousesproject.repositories.WarehouseRepository;




@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, WarehouseRepository warehouseRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.warehouseRepository = warehouseRepository;
        this.productRepository = productRepository;
    }

 public List<Object[]> getAllInventories() {
    return inventoryRepository.findAllInventoryWithNames();
}

    public Inventory addProductToWarehouse(Long warehouseId, Long productId, int quantity) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new IllegalArgumentException("Warehouse not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Inventory inventory = new Inventory();
        inventory.setWarehouse(warehouse);
        inventory.setProduct(product);
        inventory.setQuantity(quantity);

        return inventoryRepository.save(inventory);
    }
}
