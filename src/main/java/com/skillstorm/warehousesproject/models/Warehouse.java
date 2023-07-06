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
    @ManyToMany
    @JoinTable(
        name = "product_warehouse",
        joinColumns = @JoinColumn(name = "warehouse_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;


    public Warehouse() {
    }


    public Warehouse(int warehouseId, String name, String location, List<Product> products) {
      this.warehouseId = warehouseId;
      this.name = name;
      this.location = location;
      this.products = products;
    }
    
    
}
