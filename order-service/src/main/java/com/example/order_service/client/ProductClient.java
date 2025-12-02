package com.example.order_service.client;

import com.example.shared_contracts.dtos.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products")
    ResponseEntity<List<ProductDTO>> getAll();

    @GetMapping("/products/{id}")
    ResponseEntity<ProductDTO> getById(@PathVariable int id);
}
