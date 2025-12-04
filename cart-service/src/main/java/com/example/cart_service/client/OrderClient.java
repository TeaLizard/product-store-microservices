package com.example.cart_service.client;

import com.example.shared_contracts.dtos.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "order-service")
public interface OrderClient {

    @GetMapping("/orders/{id}")
    ResponseEntity<OrderDTO> getById(@PathVariable Integer id);

    @PostMapping("/orders")
    ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO);
}
