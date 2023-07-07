package com.skillstorm.warehousesproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.warehousesproject.models.Warehouse;
import com.skillstorm.warehousesproject.repositories.WarehouseRepository;

@Service
public class WarehouseService {
  @Autowired WarehouseRepository warehouseRepository;

  public List<Warehouse> findAllWarehouses(){
    return warehouseRepository.findAll();
  }
}
