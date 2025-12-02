package com.example.order_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity(name = "order")
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "product_id")
    private List<Integer> productIds;

    @NotNull
    @NotBlank
    @Column
    private String status;

    @NotNull
    @NotBlank
    @Column
    private String location;

    public Order() {}

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }
    public void setShoes(List<Integer> productIds) {
        this.productIds = productIds;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}
