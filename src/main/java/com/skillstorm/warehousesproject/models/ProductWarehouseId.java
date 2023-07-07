package com.skillstorm.warehousesproject.models;

import java.io.Serializable;
import java.util.Objects;

public class ProductWarehouseId implements Serializable {
    private int product;
    private int warehouse;

    public ProductWarehouseId() {
    }

    public ProductWarehouseId(int product, int warehouse) {
        this.product = product;
        this.warehouse = warehouse;
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductWarehouseId that = (ProductWarehouseId) o;
        return product == that.product && warehouse == that.warehouse;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, warehouse);
    }
}