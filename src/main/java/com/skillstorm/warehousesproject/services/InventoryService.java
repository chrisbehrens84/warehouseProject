package com.skillstorm.warehousesproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.skillstorm.warehousesproject.models.Inventory;
import com.skillstorm.warehousesproject.models.Product;
import com.skillstorm.warehousesproject.models.Warehouse;
import com.skillstorm.warehousesproject.repositories.InventoryRepository;
import com.skillstorm.warehousesproject.repositories.ProductRepository;
import com.skillstorm.warehousesproject.repositories.WarehouseRepository;




@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ProductRepository productRepository;
   

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


    public void deleteInventory(Long id) throws NotFoundException {
        inventoryRepository.deleteById(id);
    }


    public Inventory getInventory(Long id) throws NotFoundException {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
    }
    


    // public Inventory updateInventory(Long id, Inventory updatedInventory) throws NotFoundException {
    // Inventory existingInventory = inventoryRepository.findById(id)
    //         .orElseThrow(() -> new NotFoundException());

    // existingInventory.setWarehouse(updatedInventory.getWarehouse());
    // existingInventory.setProduct(updatedInventory.getProduct());
    // existingInventory.setQuantity(updatedInventory.getQuantity());

    // return inventoryRepository.save(existingInventory);
    public Inventory updateInventory(Long id, Inventory updatedInventory) throws NotFoundException {
        Inventory existingInventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());

                existingInventory.setQuantity(updatedInventory.getQuantity());

        return inventoryRepository.save(existingInventory);
    }

}
