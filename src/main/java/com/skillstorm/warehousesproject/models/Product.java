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
    @OneToMany(mappedBy = "product", targetEntity = ProductWarehouse.class)
    private List<ProductWarehouse> productWarehouse;



    public Product() {
    }



    public Product(int productId, String name, String description, double price, List<ProductWarehouse> productWarehouse) {
      this.productId = productId;
      this.name = name;
      this.description = description;
      this.price = price;
      this.productWarehouse = productWarehouse;
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



    public List<ProductWarehouse> getProductWarehouse() {
      return productWarehouse;
    }



    public void setProductWarehouse(List<ProductWarehouse> productWarehouse) {
      this.productWarehouse = productWarehouse;
    }



   
    
    

}
