package com.example.inventory_service.controller;

import com.example.inventory_service.entity.InventoryDTO;
import com.example.inventory_service.entity.InventoryMapper;
import com.example.inventory_service.service.IInventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final IInventoryService service;
    private final InventoryMapper mapper;
    public InventoryController(IInventoryService service, InventoryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<InventoryDTO>> getAll() {
        var dtos = service.getAll().stream()
                .map(mapper::toDTO)   // convert each entity to DTO
                .collect(Collectors.toList());

        if (dtos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(mapper.toDTO(service.getById(id)));
    }

    @PostMapping("/{id}")
    public ResponseEntity<InventoryDTO> add(@RequestBody InventoryDTO inventoryDTO, @PathVariable String id) {
        var inventory = mapper.toEntity(inventoryDTO);
        return ResponseEntity.ok(mapper.toDTO(service.add(inventory)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> update(@PathVariable int id, @RequestBody InventoryDTO inventoryDTO) {
        var inventory = mapper.toEntity(inventoryDTO);
        inventory.setProductId(id);
        service.update(inventory);
        return ResponseEntity.ok(mapper.toDTO(inventory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<InventoryDTO> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
