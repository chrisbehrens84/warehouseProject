package com.skillstorm.warehousesproject.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @OneToMany(mappedBy = "product")
    private List<Inventory> inventories;

    public Product() {
    }

    public Product(Long id, String name, double price, List<Inventory> inventories) {
      this.id = id;
      this.name = name;
      this.price = price;
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

    public double getPrice() {
      return price;
    }

    public void setPrice(double price) {
      this.price = price;
    }

    public List<Inventory> getInventories() {
      return inventories;
    }

    public void setInventories(List<Inventory> inventories) {
      this.inventories = inventories;
    }

}