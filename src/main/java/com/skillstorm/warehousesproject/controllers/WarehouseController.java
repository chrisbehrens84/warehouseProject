package com.skillstorm.warehousesproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.warehousesproject.models.Warehouse;
import com.skillstorm.warehousesproject.services.WarehouseService;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {
   @Autowired WarehouseService warehouseService;
  // @Autowired WarehouseService warehouseService;
  // @Autowired ProductWarehouseService productwarehouseService;


  @GetMapping()
  public ResponseEntity<List<Warehouse>> findAllProducts(){
    List<Warehouse> warehouses = warehouseService.findAllWarehouses();

    
    return new ResponseEntity<List<Warehouse>>(warehouses, HttpStatus.OK);
  }

}
