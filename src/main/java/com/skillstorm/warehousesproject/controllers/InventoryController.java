package com.skillstorm.warehousesproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skillstorm.warehousesproject.models.Inventory;
import com.skillstorm.warehousesproject.models.Product;
import com.skillstorm.warehousesproject.services.InventoryService;

import java.util.List;

import javax.annotation.Generated;


@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    

    @GetMapping
    public List<Object[]> getAllInventories() {
        return inventoryService.getAllInventories();
    }

    @PostMapping("/add")
    public Inventory addProductToWarehouse(@RequestParam Long warehouseId, @RequestParam Long productId, @RequestParam int quantity) {
        return inventoryService.addProductToWarehouse(warehouseId, productId, quantity);
    }


    // @GetMapping("/{index}")
    // public Object[] getInventory(@PathVariable("index") Long index) throws NotFoundException {
    //     return inventoryService.getInventory(index);
    // }

    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable("id") Long inventoryId) throws NotFoundException {
        inventoryService.deleteInventory(inventoryId);
    }

    @GetMapping("/{id}")
    public Inventory getInventory(@PathVariable("id") Long id) throws NotFoundException {
    return inventoryService.getInventory(id);
    }

    @PutMapping("/{id}")
    public Inventory updateProduct(@PathVariable("id") Long id, @RequestBody Inventory inventory ) throws NotFoundException {
        System.out.println("WTF");
        return inventoryService.updateInventory(id, inventory);
    }



}




