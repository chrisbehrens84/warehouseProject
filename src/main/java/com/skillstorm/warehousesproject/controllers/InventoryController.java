package com.skillstorm.warehousesproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.*;

import com.skillstorm.warehousesproject.models.Inventory;
import com.skillstorm.warehousesproject.services.InventoryService;

import java.util.List;



@RestController
@RequestMapping("/inventory")
@CrossOrigin("http://127.0.0.1:5500/src/main/resources/static/index.html")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    

    @GetMapping
    public List<Object> getAllInventories() {
        return inventoryService.getAllInventories();
    }

    @PostMapping("/add")
    public Inventory addProductToInventory(@RequestParam Long warehouseId, @RequestParam Long productId, @RequestParam int quantity) {
        return inventoryService.addProductToInventory(warehouseId, productId, quantity);
    }



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
        return inventoryService.updateInventory(id, inventory);
    }



}




