package com.example.order_service.service;

import com.example.order_service.entity.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getAll();
    Order getById(int id);
    Order add(Order order);
    void update(Order order);
    void delete(int id);
}