package com.example.cart_service.client;

import com.example.shared_contracts.dtos.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll();

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable int id);

    @PostMapping
    public ResponseEntity<ProductDTO> add(@RequestBody ProductDTO productDTO);

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable int id, @RequestBody ProductDTO productDTO);

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> delete(@PathVariable int id);
}
