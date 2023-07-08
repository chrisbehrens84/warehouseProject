package com.skillstorm.warehousesproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.skillstorm.warehousesproject.models.Inventory;
import com.skillstorm.warehousesproject.services.InventoryService;

import java.util.List;


@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<Object[]> getAllInventories() {
        return inventoryService.getAllInventories();
    }

    @PostMapping("/add")
    public Inventory addProductToWarehouse(@RequestParam Long warehouseId, @RequestParam Long productId, @RequestParam int quantity) {
        return inventoryService.addProductToWarehouse(warehouseId, productId, quantity);
    }
}