package com.skillstorm.warehousesproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.warehousesproject.models.Warehouse;



public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}