package com.skillstorm.warehousesproject.models;


import javax.persistence.*;

@Entity
@Table(name = "product_warehouse")
@IdClass(ProductWarehouseId.class)
public class ProductWarehouse {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "id")
    // private int id;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;
    
    @Column(name = "quantity")
    private int quantity;

    

    public ProductWarehouse() {
    }

    

    public ProductWarehouse(Product product, Warehouse warehouse, int quantity) {
      this.product = product;
      this.warehouse = warehouse;
      this.quantity = quantity;
    }



   

    // public Product getProduct() {
    //   return product;
    // }

    public void setProduct(Product product) {
      this.product = product;
    }

    // public Warehouse getWarehouse() {
    //   return warehouse;
    // }

    public void setWarehouse(Warehouse warehouse) {
      this.warehouse = warehouse;
    }

    public int getQuantity() {
      return quantity;
    }

    public void setQuantity(int quantity) {
      this.quantity = quantity;
    }



    // @Override
    // public int hashCode() {
    //   final int prime = 31;
    //   int result = 1;
    //   result = prime * result + ((product == null) ? 0 : product.hashCode());
    //   result = prime * result + ((warehouse == null) ? 0 : warehouse.hashCode());
    //   result = prime * result + quantity;
    //   return result;
    // }



    // @Override
    // public boolean equals(Object obj) {
    //   if (this == obj)
    //     return true;
    //   if (obj == null)
    //     return false;
    //   if (getClass() != obj.getClass())
    //     return false;
    //   ProductWarehouse other = (ProductWarehouse) obj;
    //   if (product == null) {
    //     if (other.product != null)
    //       return false;
    //   } else if (!product.equals(other.product))
    //     return false;
    //   if (warehouse == null) {
    //     if (other.warehouse != null)
    //       return false;
    //   } else if (!warehouse.equals(other.warehouse))
    //     return false;
    //   if (quantity != other.quantity)
    //     return false;
    //   return true;
    // }
    
    // Add constructor, getters, setters, and other methods as needed


}
