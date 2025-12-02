package com.example.cart_service.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "cart")
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToMany
    @JoinTable(
            name = "cart_items",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "shoe_id")
    )
    private List<Product> shoes;

    public Cart() {}

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public List<Product> getShoes() {
        return shoes;
    }
    public void setShoes(List<Product> shoes) {
        this.shoes = shoes;
    }
}