package com.example.product_service.client;

import com.example.shared_contracts.dtos.InventoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("inventory-service")
public interface InventoryClient {

    @GetMapping("/inventory")
    public ResponseEntity<List<InventoryDTO>> getAll();

    @GetMapping("/inventory/{id}")
    public ResponseEntity<InventoryDTO> getById(@PathVariable int id);

    @PostMapping("/inventory/{id}")
    public ResponseEntity<InventoryDTO> add(@PathVariable int id, @RequestBody InventoryDTO dto);

    @PutMapping("/inventory/{id}")
    public ResponseEntity<InventoryDTO> update(@PathVariable int id, @RequestBody InventoryDTO inventoryDTO);

    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<InventoryDTO> delete(@PathVariable int id);
}
