package com.example.order_service.controller;

import com.example.order_service.client.ProductClient;
import com.example.shared_contracts.dtos.OrderDTO;
import com.example.order_service.entity.OrderMapper;
import com.example.order_service.service.IOrderService;
import com.example.order_service.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final IOrderService service;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    public OrderController(OrderService service, ProductClient productClient, OrderMapper mapper) {
        this.service = service;
        this.productClient = productClient;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    ResponseEntity<OrderDTO> getById(@PathVariable Integer id) {
        var order = service.getById(id);
        return ResponseEntity.ok(mapper.toDTO(order));
    }

    @PostMapping
    ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        var order = mapper.toEntity(orderDTO);
        order.setId(null);
        var out = service.add(order);
        return ResponseEntity.ok(mapper.toDTO(out));
    }
}
