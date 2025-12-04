package com.example.cart_service.client;

import com.example.shared_contracts.dtos.InventoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @GetMapping("/inventory")
    public ResponseEntity<List<InventoryDTO>> getAll();

    @GetMapping("/inventory/{id}")
    public ResponseEntity<InventoryDTO> getById(@PathVariable int id);

    @PostMapping("/inventory")
    public ResponseEntity<InventoryDTO> add(@RequestBody InventoryDTO inventoryDTO);

    @PutMapping("/inventory/{id}")
    public ResponseEntity<InventoryDTO> update(@PathVariable int id, @RequestBody InventoryDTO inventoryDTO);

    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<InventoryDTO> delete(@PathVariable int id);
}