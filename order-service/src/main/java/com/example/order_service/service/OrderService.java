package com.example.order_service.service;

import com.example.order_service.client.ProductClient;
import com.example.order_service.entity.Order;
import com.example.order_service.entity.OrderDTO;
import com.example.order_service.exeption.NotFoundException;
import com.example.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    private final OrderRepository repository;
    private final ProductClient productClient;
    public OrderService(OrderRepository repository, ProductClient productClient) {
        this.repository = repository;
        this.productClient = productClient;
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
        return repository.save(order);
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
