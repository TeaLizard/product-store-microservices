package com.example.order_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class OrderDTO {

    private Integer id;
    private List<ProductDTO> products;
    private String status;
    private String location;

    public OrderDTO() {}
    public OrderDTO(Integer id, List<ProductDTO> products, String status, String location) {
        this.id = id;
        this.products = products;
        this.status = status;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }
    public void setProducts(List<ProductDTO> products) {
        this.products = products;
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
