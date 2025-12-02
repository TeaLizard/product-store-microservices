package com.example.inventory_service.service;

import com.example.inventory_service.entity.Inventory;

import java.util.List;

public interface IInventoryService {
    List<Inventory> getAll();
    Inventory getById(int productId);
    Inventory add(Inventory inventory);
    void update(Inventory inventory);
    void delete(int productId);
}

