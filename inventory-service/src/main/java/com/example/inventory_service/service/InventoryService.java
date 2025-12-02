package com.example.inventory_service.service;

import com.example.inventory_service.entity.Inventory;
import com.example.shared_contracts.dtos.InventoryDTO;
import com.example.inventory_service.exeption.NotFoundException;
import com.example.inventory_service.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService implements IInventoryService {
    private final InventoryRepository repository;
    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Inventory> getAll() {
        return repository.findAll();
    }

    @Override
    public Inventory getById(int productId) {
        return repository.findById(productId).orElseThrow(
                () -> new NotFoundException("Inventory", productId)
        );
    }

    @Override
    public Inventory add(Inventory inventory) {
        return repository.save(inventory);
    }

    @Override
    public void update(Inventory inventory) {
        var old = getById(inventory.getProductId());
        old.setQuantity(inventory.getQuantity());
        repository.save(inventory);
    }

    @Override
    public void delete(int productId) {
        repository.deleteById(productId);
    }
}
