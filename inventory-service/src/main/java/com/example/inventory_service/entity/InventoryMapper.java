package com.example.inventory_service.entity;

import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public InventoryDTO toDTO(Inventory inventory) {
        if (inventory == null) {
            return null;
        }
        return new InventoryDTO(
                inventory.getProductId(),
                inventory.getQuantity()
        );
    }

    public Inventory toEntity(InventoryDTO dto) {
        if (dto == null) {
            return null;
        }
        Inventory inventory = new Inventory();
        inventory.setProductId(dto.getProductId());
        inventory.setQuantity(dto.getQuantity());
        return inventory;
    }
}

