package com.skillstorm.warehousesproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.skillstorm.warehousesproject.models.Warehouse;
import com.skillstorm.warehousesproject.services.WarehouseService;

import java.util.List;


@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public List<Warehouse> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @PostMapping
    public Warehouse createWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.createWarehouse(warehouse);
    }

    @DeleteMapping("/{id}")
    public void deleteWarehouse(@PathVariable("id") Long id) {
        warehouseService.deleteWarehouse(id);
    }
}
