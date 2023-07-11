
package com.skillstorm.warehousesproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skillstorm.warehousesproject.models.Inventory;




public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Query("SELECT i.id, w.id AS warehouseId, w.name AS warehouseName, p.id AS productId, p.name AS productName, i.quantity " +
           "FROM Inventory i JOIN i.warehouse w JOIN i.product p " +
           "ORDER BY w.name ASC")
    List<Object[]> findAllInventoryWithNames();

}


