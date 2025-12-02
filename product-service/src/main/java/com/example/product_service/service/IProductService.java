package com.example.product_service.service;

import com.example.product_service.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Product getById(int id);
    Product add(Product product);
    void update(Product product);
    void delete(int id);
}
