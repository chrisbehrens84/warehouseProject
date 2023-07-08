package com.skillstorm.warehousesproject.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @OneToMany(mappedBy = "warehouse")
    private List<Inventory> inventories;

    public Warehouse() {
    }

    public Warehouse(Long id, String name, String location, List<Inventory> inventories) {
      this.id = id;
      this.name = name;
      this.location = location;
      this.inventories = inventories;
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
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

    public List<Inventory> getInventories() {
      return inventories;
    }

    public void setInventories(List<Inventory> inventories) {
      this.inventories = inventories;
    }

   
}