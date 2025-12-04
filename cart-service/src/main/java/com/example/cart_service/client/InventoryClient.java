package com.example.cart_service.client;

import com.example.shared_contracts.dtos.InventoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@FeignClient(name = "inventory-service")
@RequestMapping("/inventory")
public interface InventoryClient {

    @GetMapping
    public ResponseEntity<List<InventoryDTO>> getAll();

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> getById(@PathVariable int id);

    @PostMapping()
    public ResponseEntity<InventoryDTO> add(@RequestBody InventoryDTO inventoryDTO);

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> update(@PathVariable int id, @RequestBody InventoryDTO inventoryDTO);

    @DeleteMapping("/{id}")
    public ResponseEntity<InventoryDTO> delete(@PathVariable int id);
}