package com.example.cart_service.client;

import com.example.shared_contracts.dtos.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "order-service")
@RequestMapping("/orders")
public interface OrderClient {

    @GetMapping("/{id}")
    ResponseEntity<OrderDTO> getById(@PathVariable Integer id);

    @PostMapping
    ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO);
}
