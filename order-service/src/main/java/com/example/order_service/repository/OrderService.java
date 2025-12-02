package com.example.order_service.repository;

import com.example.order_service.entity.Order;
import com.example.order_service.exeption.NotFoundException;
import com.example.order_service.service.IOrderService;

import java.io.Serializable;
import java.util.List;

public class OrderService implements IOrderService {
    private OrderRepository repository;
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Order> getAll() {
        return repository.findAll();
    }

    @Override
    public Order getById(int id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Order", id)
        );
    }

    @Override
    public Order add(Order order) {
        repository.save(order);
        return order;
    }

    @Override
    public void update(Order order) {
        var old = getById(order.getId());
        old.setProductIds(order.getProductIds());
        old.setStatus(order.getStatus());
        old.setLocation(order.getLocation());
        repository.save(old);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
