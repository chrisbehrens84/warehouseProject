package com.skillstorm.warehousesproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.skillstorm.warehousesproject.models.Warehouse;
import com.skillstorm.warehousesproject.repositories.WarehouseRepository;



@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }

    public Warehouse getWarehouseById(Long id) throws NotFoundException {
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
    }

    public Warehouse updateWarehouse(Long id, Warehouse updatedWarehouse) throws NotFoundException {
        Warehouse existingWarehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());

        existingWarehouse.setName(updatedWarehouse.getName());
        existingWarehouse.setLocation(updatedWarehouse.getLocation());

        return warehouseRepository.save(existingWarehouse);
    }


}
