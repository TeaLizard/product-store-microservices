package com.example.inventory_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity(name = "inventory")
@Table(name = "inventory")
public class Inventory {
    @Id
    @Column(name = "product_id")
    private Integer productId;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Integer quantity;

    public Inventory() {}

    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productIds) {
        this.productId = productIds;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}