package com.skillstorm.warehousesproject.models;

import javax.persistence.*;

@Entity
@Table(name = "product_warehouse")
public class ProductWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;
    
    @Column(name = "quantity")
    private int quantity;

    

    public ProductWarehouse() {
    }

    

    public ProductWarehouse(int id, Product product, Warehouse warehouse, int quantity) {
      this.id = id;
      this.product = product;
      this.warehouse = warehouse;
      this.quantity = quantity;
    }



    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public Product getProduct() {
      return product;
    }

    public void setProduct(Product product) {
      this.product = product;
    }

    public Warehouse getWarehouse() {
      return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
      this.warehouse = warehouse;
    }

    public int getQuantity() {
      return quantity;
    }

    public void setQuantity(int quantity) {
      this.quantity = quantity;
    }
    
    // Add constructor, getters, setters, and other methods as needed
}
