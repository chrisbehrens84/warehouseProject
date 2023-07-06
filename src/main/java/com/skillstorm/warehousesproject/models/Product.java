package com.skillstorm.warehousesproject.models;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "price")
    private double price;
    
   
    
    // Many-to-many mapping annotation
    @ManyToMany(mappedBy = "products")
    private List<Warehouse> warehouses;



    public Product() {
    }



    public Product(int productId, String name, String description, double price, List<Warehouse> warehouses) {
      this.productId = productId;
      this.name = name;
      this.description = description;
      this.price = price;
      this.warehouses = warehouses;
    }



    public int getProductId() {
      return productId;
    }



    public void setProductId(int productId) {
      this.productId = productId;
    }



    public String getName() {
      return name;
    }



    public void setName(String name) {
      this.name = name;
    }



    public String getDescription() {
      return description;
    }



    public void setDescription(String description) {
      this.description = description;
    }



    public double getPrice() {
      return price;
    }



    public void setPrice(double price) {
      this.price = price;
    }



    public List<Warehouse> getWarehouses() {
      return warehouses;
    }



    public void setWarehouses(List<Warehouse> warehouses) {
      this.warehouses = warehouses;
    }
    
    
    // Add constructor, getters, setters, and other methods as needed

}
