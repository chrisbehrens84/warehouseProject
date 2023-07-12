package com.skillstorm.warehousesproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.*;

import com.skillstorm.warehousesproject.models.Warehouse;
import com.skillstorm.warehousesproject.services.WarehouseService;

import java.util.List;


@RestController
@RequestMapping("/warehouses")
@CrossOrigin("http://127.0.0.1:5500/")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;
   

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

    @GetMapping("/{id}")
    public Warehouse getWarehouseById(@PathVariable("id") Long id) throws NotFoundException {
    return warehouseService.getWarehouseById(id);
    }

    @PutMapping("/{id}")
    public Warehouse updateWarehouse(@PathVariable("id") Long id, @RequestBody Warehouse warehouse) throws NotFoundException {
        return warehouseService.updateWarehouse(id, warehouse);
    }
}
