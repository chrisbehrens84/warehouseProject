
package com.skillstorm.warehousesproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skillstorm.warehousesproject.models.Inventory;



public interface InventoryRepository extends JpaRepository<Inventory, Long>{

   @Query("SELECT i.id, w.name AS warehouseName, p.name AS productName, i.quantity FROM Inventory i JOIN i.warehouse w JOIN i.product p")
    List<Object[]> findAllInventoryWithNames();
}