package com.skillstorm.warehousesproject.models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "warehouses")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private int warehouseId;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "location")
    private String location;
    
    
    // Many-to-many mapping annotation
    @OneToMany(mappedBy = "warehouse", targetEntity = ProductWarehouse.class)
    private List<ProductWarehouse> productWarehouse;


    public Warehouse() {
    }


    public Warehouse(int warehouseId, String name, String location, List<ProductWarehouse> productWarehouse) {
      this.warehouseId = warehouseId;
      this.name = name;
      this.location = location;
      this.productWarehouse= productWarehouse;
    }


    public int getWarehouseId() {
      return warehouseId;
    }


    public void setWarehouseId(int warehouseId) {
      this.warehouseId = warehouseId;
    }


    public String getName() {
      return name;
    }


    public void setName(String name) {
      this.name = name;
    }


    public String getLocation() {
      return location;
    }


    public void setLocation(String location) {
      this.location = location;
    }


    public List<ProductWarehouse> getProductWarehouse() {
      return productWarehouse;
    }


    public void setProductWarehouse(List<ProductWarehouse> productWarehouse) {
      this.productWarehouse = productWarehouse;
    }
    
    
}
