package com.example.order_service.entity;

import jakarta.persistence.*;

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
}
